package com.koala.member.api.response;

import com.koala.utils.gateway.annotation.Description;

import java.io.Serializable;

/**
 * @Author Liuyf
 * @Date 2016-08-09
 * @Time 13:57
 * @Description
 */
@Description("用户信息")
public class UserInfo implements Serializable {

    @Description("用户名称")
    public String userName;
    @Description("用户密码")
    public String password;
    @Description("真实姓名")
    public String realName;
    @Description("描述")
    public String desc;
    @Description("创建人编号")
    public long createId;
    @Description("创建时间")
    public long createDate;
    @Description("修改人编号")
    public long updateId;
    @Description("修改时间")
    public long updateDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getCreateId() {
        return createId;
    }

    public void setCreateId(long createId) {
        this.createId = createId;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(long updateId) {
        this.updateId = updateId;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }
}
