package com.goodtime.user.model;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * 登录用户
 * Created by zhongcy on 16-11-20.
 */
public class LoginUser implements Serializable {

    private static final long serialVersionUID = -5355641515905843524L;

    /**
     * 用户名
     */
    private String userId;

    /**
     * 密码
     */
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("userId", userId)
                .add("password", password)
                .toString();
    }
}
