package com.koala.member.ws;

import com.alibaba.dubbo.rpc.RpcContext;
import com.google.common.base.Strings;
import com.koala.base.entities.member.KlUser;
import com.koala.base.logics.member.KlUserLogic;
import com.koala.member.api.MemberService;
import com.koala.member.api.errors.MemberErrorCodes;
import com.koala.member.api.response.UserInfo;
import com.koala.member.common.captcha.RandomValidateCode;
import com.koala.member.common.captcha.RandomValidateCodeResult;
import com.koala.utils.common.lang.DateUtils;
import com.koala.utils.config.MessageSender;
import com.koala.utils.config.Queue;
import com.koala.utils.config.annotation.EnableJMSSender;
import com.koala.utils.config.handler.RedisHandler;
import com.koala.utils.gateway.annotation.ApiParameter;
import com.koala.utils.gateway.define.ExtendParameter;
import com.koala.utils.gateway.entity.ServiceException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class MemberWs implements MemberService {

    @Resource
    private KlUserLogic userLogic;
    //消息推送
    @Resource
    MessageSender messageSender;

    @Resource
    RedisHandler redisHandler;

    @Override
    public UserInfo getUserById(@ApiParameter(required = true, name = "id", desc="用户编号")long id) {
        KlUser klUser = userLogic.findById(id);
        redisHandler.set("K-BAICAI" + id, klUser.getUpdateDate().toString(), 20);
        if (klUser == null)return null;
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
}
