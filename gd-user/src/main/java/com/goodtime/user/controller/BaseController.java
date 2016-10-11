package com.goodtime.user.controller;

import com.github.api.code.AjaxCode;
import com.goodtime.base.exception.BizException;
import com.goodtime.base.result.BaseResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * 基本Controller的父类
 * Created by zhongcy on 2016/9/1.
 */
public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected static final BaseResult SUCCESS = new BaseResult();

    protected static final BaseResult SYSTEM_ERROR = new BaseResult(AjaxCode.SYSTEM_ERROR, "系统错误");

    /**
     * 异常通用处理
     *
     * @param request 请求.
     * @param e       异常
     */
    @ExceptionHandler
    @ResponseBody
    public BaseResult exception(HttpServletRequest request, Exception e) {
        if (e instanceof BizException) {
            BizException biz = (BizException) e;
            return new BaseResult(biz.getErrorCode(), biz.getErrorDescription());
        }
        if (e instanceof IllegalArgumentException) {
            return new BaseResult(AjaxCode.PARAM_ERROR, e.getLocalizedMessage());
        }
        return SYSTEM_ERROR;
    }

    /**
     * 参数校验方法
     */
    protected void checkParamNotBlank(Object obj, String message) {
        if (null == obj) {
            throw new BizException(AjaxCode.PARAM_ERROR, message);
        }

        if (obj instanceof String && StringUtils.isBlank((String) obj)) {
            throw new BizException(AjaxCode.PARAM_ERROR, message);
        }
    }
}