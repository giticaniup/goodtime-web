package com.goodtime.base.exception;

import com.google.common.base.MoreObjects;

/**
 * 业务异常
 * Created by zhongcy on 2016/9/1.
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = -7949170734224838097L;
    /**
     * 异常码
     */
    private int errorCode;

    /**
     * 异常消息(英文)
     */
    private String errorMessage;

    /**
     * 异常消息(中文)
     */
    private String errorDescription;

    public BizException() {
    }

    public BizException(int errorCode, String errorMessage, String errorDescription) {
        super(errorCode + " : " + errorMessage + " : " + errorDescription);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorDescription = errorDescription;
    }

    public BizException(int errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("errorCode", errorCode)
                .add("errorMessage", errorMessage)
                .add("errorDescription", errorDescription)
                .toString();
    }
}
