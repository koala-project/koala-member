package com.koala.member.api;

import com.koala.member.api.errors.MemberErrorCodes;
import com.koala.member.api.response.CustomerLoginInfo;
import com.koala.member.api.response.UserInfo;
import com.koala.member.api.response.UserRoleInfo;
import com.koala.utils.gateway.annotation.ApiGroup;
import com.koala.utils.gateway.annotation.ApiParameter;
import com.koala.utils.gateway.annotation.DesignedErrorCode;
import com.koala.utils.gateway.annotation.HttpApi;
import com.koala.utils.gateway.define.SecurityType;
import com.koala.utils.gateway.entity.ServiceException;

/**
 * @Author Liuyf
 * @Date 2016-08-09
 * @Time 13:50
 * @Description
 */
@ApiGroup(minCode = 40000, maxCode = 41000, name = "member", owner = "liuyf", codeDefine = MemberErrorCodes.class)
public interface MemberService {

    @HttpApi(name = "member.info", desc = "获取用户信息", security = SecurityType.UserLogin, owner = "owner")
    @DesignedErrorCode({
            MemberErrorCodes.MEMBER_USER_NOT_EXIST
    })
    public UserInfo info() throws ServiceException ;



    @HttpApi(name = "member.register", desc = "用户注册", security = SecurityType.None, owner = "owner")
    @DesignedErrorCode({
            MemberErrorCodes.MEMBER_MOBILE_FORMAT_ERROR,
            MemberErrorCodes.MEMBER_MOBILENO_HAVE_BEEN_OCCUPIED,
            MemberErrorCodes.MEMBER_VERIFY_CODE_EXPIRED,
            MemberErrorCodes.MEMBER_VERIFY_CODE_ERROR,
            MemberErrorCodes.MEMBER_REGISTER_ERROR
    })
    public boolean register(@ApiParameter(required = true, name = "userNAME", desc="用户名")String userName,
                         @ApiParameter(required = true, name = "password", desc="密码")String password,
                         @ApiParameter(required = true, name = "vCode", desc="验证码")String vCode);



    @HttpApi(name = "common.imageCode.get", desc = "获取图片验证码", security = SecurityType.None, owner = "owner")
    @DesignedErrorCode(MemberErrorCodes.MEMBER_GET_VALIDATE_CODE_ERROR)
    public String getImageCode() throws ServiceException;


    @HttpApi(name = "common.imageCode.verify", desc = "图片验证码,验证是否通过", security = SecurityType.None, owner = "owner")
    @DesignedErrorCode(MemberErrorCodes.MEMBER_VERIFY_CODE_ERROR)
    public boolean verifyImageCode(
            @ApiParameter(required = true, name = "vCode", desc = "图片验证码") String vCode)
            throws ServiceException;


    /**
     * 登录
     * @param userName
     * @param password
     * @return
     * @throws ServiceException
     */
    @HttpApi(name = "member.login", desc = "登录", security = SecurityType.None, owner = "owner")
    @DesignedErrorCode({
            MemberErrorCodes.MEMBER_PASSWORD_ERROR,
            MemberErrorCodes.MEMBER_USERNAME_NOT_EXIST,
            MemberErrorCodes.MEMBER_LOGIN_FAILED
    })
    public CustomerLoginInfo login(
            @ApiParameter(required = true, name = "userName", desc = "登录名") String userName,
            @ApiParameter(required = true, name = "password", desc = "登录密码") String password,
            @ApiParameter(required = false, name = "wxOpenId", desc = "微信用户标识") String wxOpenId)
            throws ServiceException;

    @HttpApi(name = "member.get", desc = "获取某个用户信息", security = SecurityType.None, owner = "owner")
    @DesignedErrorCode({
            MemberErrorCodes.MEMBER_USER_NOT_EXIST
    })
    public UserRoleInfo searchUser(@ApiParameter(required = true, name = "id", desc = "用户ID")long id);
}
