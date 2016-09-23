package com.koala.member.api.response;


import com.koala.utils.gateway.annotation.Description;

import java.io.Serializable;

/**
 * 用户登录返回信息
 */
@Description("用户登录信息")
public class CustomerLoginInfo implements Serializable {
    @Description("用户名")
    public String customerName;
    @Description("客户标识")
    public long customerId;
    @Description("token")
    public String token;
    @Description("appid")
    public String appid;
    @Description("token过期时间")
    public long tokenExpire;
    @Description("是否绑定手机号")
    public int flag;
    @Description("绑定手机号码")
    public String mobileNo;

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        mobileNo = mobileNo;
    }

    public String getAppid() {
        return appid;
    }

    public int getFlag() {
        return flag;
    }
    public void setAppid(String appid) {
        this.appid = appid;
    }

    public long getTokenExpire() {
        return tokenExpire;
    }

    public void setTokenExpire(long tokenExpire) {
        this.tokenExpire = tokenExpire;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int isFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
