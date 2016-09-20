package com.koala.member.api;

import com.koala.member.api.errors.MemberErrorCodes;
import com.koala.member.api.response.UserInfo;
import com.koala.utils.gateway.annotation.ApiGroup;
import com.koala.utils.gateway.annotation.ApiParameter;
import com.koala.utils.gateway.annotation.DesignedErrorCode;
import com.koala.utils.gateway.annotation.HttpApi;
import com.koala.utils.gateway.define.SecurityType;

/**
 * @Author Liuyf
 * @Date 2016-08-09
 * @Time 13:50
 * @Description
 */
@ApiGroup(minCode = 40000, maxCode = 41000, name = "member", owner = "liuyf", codeDefine = MemberErrorCodes.class)
public interface MemberService {

    @HttpApi(name = "member.get", desc = "获取用户信息", security = SecurityType.None, owner = "owner")
    @DesignedErrorCode({
            MemberErrorCodes.MEMBER_USER_NOT_EXIST
    })
    public UserInfo getUserById(@ApiParameter(required = true, name = "id", desc="用户编号")long id);

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

}
