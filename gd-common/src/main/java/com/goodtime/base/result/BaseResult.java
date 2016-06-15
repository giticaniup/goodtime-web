package com.goodtime.base.result;

import java.io.Serializable;

/**
 * 返回结果
 * Created by zhongcy on 2016/6/15.
 */
public class BaseResult implements Serializable{
    private static final long serialVersionUID = 8236675276125449619L;

    /**
     * 返回码，0表示成功
     */
    private int errorCode = 0;

    /**
     * 返回结果信息
     */
    private String errorMsg;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return com.google.common.base.MoreObjects.toStringHelper(this)
                .add("errorCode", errorCode)
                .add("errorMsg", errorMsg)
                .toString();
    }

    public BaseResult() {
    }

    public BaseResult(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

}
