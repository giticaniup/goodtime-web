package com.goodtime.base.code;

/**
 * 返回页面的请求错误信息，一般 非0表示有异常.
 */
public interface AjaxCode {

    /**
     * 请求处理成功.
     */
    int OK = 0;

    /**
     * 用户未登录.
     */
    int USER_NOT_LOGIN = 401;

    /*
     * 权限不足,403 表示登录有问题,或者非网站管理员.
     */
    int NO_AUTHORITY = 403;

    /**
     * 参数校验错误,400 一般表示参数有问题.
     */
    int PARAM_ERROR = 400;

    /**
     * 系统错误.
     */
    int SYSTEM_ERROR = 500;

    /**
     * 服务不可用或系统繁忙
     */
    int SERVICE_UNAVAILABLE = 503;
}