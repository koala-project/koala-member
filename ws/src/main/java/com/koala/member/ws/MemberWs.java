package com.koala.member.ws;

import com.alibaba.dubbo.rpc.RpcContext;
import com.google.common.base.Strings;
import com.koala.base.entities.member.KlUser;
import com.koala.base.logics.member.KlUserLogic;
import com.koala.member.api.MemberService;
import com.koala.member.api.errors.MemberErrorCodes;
import com.koala.member.api.response.CustomerLoginInfo;
import com.koala.member.api.response.UserInfo;
import com.koala.member.common.captcha.RandomValidateCode;
import com.koala.member.common.captcha.RandomValidateCodeResult;
import com.koala.member.service.MemberBusiness;
import com.koala.utils.common.lang.DateUtils;
import com.koala.utils.common.lang.GsonUtils;
import com.koala.utils.config.MessageSender;
import com.koala.utils.config.Queue;
import com.koala.utils.config.annotation.EnableJMSSender;
import com.koala.utils.config.handler.RedisHandler;
import com.koala.utils.gateway.annotation.ApiParameter;
import com.koala.utils.gateway.define.CommonParameter;
import com.koala.utils.gateway.define.ExtendParameter;
import com.koala.utils.gateway.define.SecurityType;
import com.koala.utils.gateway.entity.CallerInfo;
import com.koala.utils.gateway.entity.ServiceException;
import com.koala.utils.gateway.util.AESTokenHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MemberWs implements MemberService {
    private static final Logger logger = LoggerFactory.getLogger(MemberWs.class);

    @Resource
    private KlUserLogic userLogic;
    //消息推送
    @Resource
    MessageSender messageSender;

    @Resource
    RedisHandler redisHandler;

    @Autowired
    private MemberBusiness memberBusiness;

    //aesKey
    @Value("${token.aesKey}")
    private String aesKey;
    //有效期
    @Value("${token.expire}")
    private Long expire;

    private final String KEY_PREFIX = "user:";
    private final Integer KEY_EXPIRE = new Integer(60);

    @Override
    public UserInfo info() throws ServiceException  {
        String userid = RpcContext.getContext().getAttachment(CommonParameter.userId);  //获取用户id
        if (Strings.isNullOrEmpty(userid)) throw new ServiceException(MemberErrorCodes.errorCodes_MEMBER_USER_NOT_EXIST);
        KlUser klUser = userLogic.findById(Long.parseLong(userid));
        if (klUser == null) throw new ServiceException(MemberErrorCodes.errorCodes_MEMBER_USER_NOT_EXIST);
        UserInfo userInfo = new UserInfo();
        userInfo.setRealName(klUser.getRealName());
        userInfo.setPassword(klUser.getPassword());
        userInfo.setUserName(klUser.getUserName());
        userInfo.setCreateId(klUser.getCreateId());
        userInfo.setUpdateId(klUser.getUpdateId());
        userInfo.setUpdateDate(klUser.getUpdateDate().getTime());
        userInfo.setCreateDate(klUser.getCreateDate().getTime());
        return userInfo;
    }

    @Override
    public boolean register(@ApiParameter(required = true, name = "userNAME", desc="用户名")String userName,
                         @ApiParameter(required = true, name = "password", desc="密码")String password,
                         @ApiParameter(required = true, name = "vCode", desc="验证码")String vCode) {
        //验证userName格式
        //校验验证码是否正确
        //校验手机号是否已经注册
        //注册（赠送优惠券）
        //登录
        KlUser user = new KlUser();
        user.setUserName(userName);
        user.setPassword(password);
        user.setRealName(userName);
        user.setDesc("注册时间" + DateUtils.getDateTime());
        user.setCreateId(1L);
        user.setCreateDate(DateUtils.currentTime());
        user.setUpdateId(1L);
        user.setUpdateDate(DateUtils.currentTime());
        userLogic.insert(user);
        Map<String, Object> map = new HashMap<>();
        map.put("userName",userName);
        map.put("password",password);
        map.put("date",DateUtils.getDateTime());
        messageSender.sendMessage(map);
        return true;
    }

    @Override
    public String getImageCode()
            throws ServiceException {

        String identity = RpcContext.getContext().getAttachment(ExtendParameter.idfa);

        if (Strings.isNullOrEmpty(identity)) {
            return null;
        }

        try {
            RandomValidateCodeResult result = new RandomValidateCode().getRandomCodeResult(identity);
            if (!result.getIsSuccess()) {
                throw new ServiceException(MemberErrorCodes.errorCodes_MEMBER_GET_VALIDATE_CODE_ERROR);
            }
            //redis 缓存 60s
            redisHandler.set(result.getValidateCodeKey(), result.getValidateCode(), 60);
            return result.getBase64String();
        } catch (Exception ex) {
            throw new ServiceException(MemberErrorCodes.errorCodes_MEMBER_GET_VALIDATE_CODE_ERROR);
        }
    }

    @Override
    public boolean verifyImageCode(
            @ApiParameter(required = true, name = "vCode", desc = "图片验证码") String vCode)
            throws ServiceException {

        String identity = RpcContext.getContext().getAttachment(ExtendParameter.idfa);
        if (Strings.isNullOrEmpty(identity)) {
            return false;
        }
        if (Strings.isNullOrEmpty(vCode)) {
            return false;
        }
        try {
            //图片验证码
            String vCodeKey = RandomValidateCode.getCodeKey(identity);
            String code = redisHandler.get(vCodeKey, "0000");
            if (vCode.equalsIgnoreCase(code)){
                redisHandler.del(vCodeKey);
                return true;
            }
            return false;
        } catch (Exception ex) {
            throw new ServiceException(MemberErrorCodes.errorCodes_MEMBER_VALIDATE_CODE_Verify_ERROR);
        }
    }

    /**
     * 登录
     *
     * @param userName 登录名
     * @param password 登录密码
     * @return
     */
    @Override
    public CustomerLoginInfo login(
            @ApiParameter(required = true, name = "userName", desc = "登录名") String userName,
            @ApiParameter(required = true, name = "password", desc = "登录密码") String password,
            @ApiParameter(required = false, name = "wxOpenId", desc = "微信用户标识") String wxOpenId)
            throws ServiceException {
        //应用编号
        String appid = RpcContext.getContext().getAttachment(CommonParameter.applicationId);
        long deviceNemebr = getDeviceNumber();
        CustomerLoginInfo info = new CustomerLoginInfo();
        KlUser user = memberBusiness.getKlUserByLoginName(userName);
        if (user != null){
            if (user.getPassword().equals(password)){
                long tokenExpire = System.currentTimeMillis() + expire;
                String token = getToken(deviceNemebr, user.getId(), appid, tokenExpire);
                info.setFlag(1);  //已绑定
                info.setMobileNo("18888888888");
                info.setCustomerId(user.getId());
                info.setCustomerName(user.getUserName());
                info.setToken(token);
                info.setAppid(appid);
                info.setTokenExpire(tokenExpire);
                redisHandler.set(KEY_PREFIX.concat(token), GsonUtils.toJson(info), KEY_EXPIRE);
            } else {
              throw new ServiceException(MemberErrorCodes.errorCodes_MEMBER_PASSWORD_ERROR);
            }
        }else {
            throw new ServiceException(MemberErrorCodes.errorCodes_MEMBER_USER_NOT_EXIST);
        }
        return info;
    }

    private long getDeviceNumber() {
        SimpleDateFormat dataFomat = new SimpleDateFormat("yyyyMMddHHmmss");
        long deviceNumber = Long.parseLong(dataFomat.format(new Date()));

        return deviceNumber;
    }

    /**
     * 生成token
     * @param deviceNumber
     * @param customerId
     * @param appid
     * @param tokenExpire
     * @return
     */
    private String getToken(long deviceNumber, long customerId, String appid, Long tokenExpire) {
        //授权级别，设值为 SecurityType.UserLogin
        SecurityType securityLevel = SecurityType.UserLogin;
        //Token 超时时间
        Long expire2 = tokenExpire;
        //用户ID，系统中的用户 ID，包括 Customer Id、Operator Id等
        CallerInfo caller = new CallerInfo();
        caller.appid = Integer.parseInt(appid);
        caller.securityLevel = securityLevel.authorize(0);
        caller.expire = expire2;
        caller.deviceId = deviceNumber;   //设备ID，自行生成，生成后与用户 ID 绑定，调用接口时网关会校验
        caller.uid = customerId;

        logger.debug("appid=" + appid + "securityLevel=" + securityLevel + "expire=" + expire2 + "deviceId=" + deviceNumber + "uid=" + customerId);

        String token = AESTokenHelper.getTokenHelper(aesKey).generateStringUserToken(caller);
        return token;
    }

}
