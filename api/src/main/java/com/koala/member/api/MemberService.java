package com.koala.member.api;

import com.koala.member.api.response.UserInfo;

/**
 * @Author Liuyf
 * @Date 2016-08-09
 * @Time 13:50
 * @Description
 */
public interface MemberService {

    /**
     * 新增用户信息
     * @param userInfo
     */
    public void saveUser(UserInfo userInfo);
}
