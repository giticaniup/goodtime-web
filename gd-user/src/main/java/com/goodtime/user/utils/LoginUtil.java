package com.goodtime.user.utils;

import javax.servlet.http.HttpSession;

/**
 * 登录Util
 * Created by zhongcy on 2016/7/11.
 */
public class LoginUtil {

    /**
     * 判断当前用户是否登录
     *
     * @param session session
     * @return boolean
     */
    public boolean checkIsLogin(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        return userId != null;
    }
}
