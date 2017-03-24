package com.goodtime.base.result;

import com.goodtime.base.constants.AjaxCode;
import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * Result类
 * Created by zhongcy on 16-11-3.
 */
public class Result implements Serializable {

    private static final long serialVersionUID = 8955661953254186351L;


    /**
     * 错误码，默认为0
     */
    int errorCode = AjaxCode.OK;

    /**
     * 错误信息
     */
    String errorMsg = "success";


    public Result() {
    }

    public Result(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

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
        return MoreObjects.toStringHelper(this)
                .add("errorCode", errorCode)
                .add("errorMsg", errorMsg)
                .toString();
    }
}
