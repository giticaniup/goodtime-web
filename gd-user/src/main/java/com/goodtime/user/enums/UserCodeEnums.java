package com.goodtime.user.enums;

/**
 * 用户模块返回码
 * Created by zhongcy on 2016/6/15.
 */
public enum UserCodeEnums {


    SUCCESS(0, "success"),

    SERVER_BUSY(-1, "server busy"),

    SYSTEM_ERROR(-2, "system error"),

    PARAM_ILLEGAL(1000,"param illegal"),

    USER_NOTLOGIN(1001, "user not login in"),

    ADDUSER_ERROR(1101,"add user error"),

    ;
    int errorCode;
    String errorMsg;

    UserCodeEnums(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
