package com.goodtime.user.controller;

import com.github.common.exception.BizException;
import com.goodtime.base.constants.AjaxCode;
import com.goodtime.base.result.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 基本Controller的父类
 * Created by zhongcy on 2016/9/1.
 */
public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected static final Result SUCCESS = new Result();

    protected static final Result SYSTEM_ERROR = new Result(AjaxCode.SYSTEM_ERROR, "系统错误");

    /**
     * 异常通用处理
     *
     * @param e 异常
     */
    @ExceptionHandler
    @ResponseBody
    public Result exception(Exception e) {
        if (e instanceof BizException) {
            BizException biz = (BizException) e;
            return new Result(biz.getErrorCode(), biz.getErrorDescription());
        }
        if (e instanceof IllegalArgumentException) {
            return new Result(AjaxCode.PARAM_ERROR, e.getLocalizedMessage());
        }
        if (e instanceof AuthenticationException) {
            // 身份验证失败
            return new Result(AjaxCode.NO_AUTHORITY, e.getLocalizedMessage());
        }
        if (e instanceof UnauthenticatedException) {
            return new Result(AjaxCode.USER_NOT_LOGIN, "用户未登录");
        } else if (e instanceof UnauthorizedException) {
            return new Result(AjaxCode.NO_AUTHORITY, "当前用户无权限");
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