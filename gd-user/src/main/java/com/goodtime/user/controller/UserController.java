package com.goodtime.user.controller;

import com.github.api.entity.User;
import com.github.api.service.UserInfoService;
import com.goodtime.base.code.AjaxCode;
import com.goodtime.base.result.Result;
import com.goodtime.user.model.LoginUser;
import com.goodtime.user.utils.UserConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

/**
 * 用户控制Controller类
 * Created by zhongcy on 2016/5/11.
 */
@RestController
@RequestMapping(value = "/goodtime", produces = "application/json;charset=UTF-8")
public class UserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result userLogin(@RequestBody LoginUser user, HttpSession session) {
        Pattern pattern = Pattern.compile("[0-9]+");
        if (StringUtils.isBlank(user.getUserId()) || !pattern.matcher(user.getUserId()).matches()) {
            return new Result(AjaxCode.PARAM_ERROR, "请输入正确的用户名");
        }
        if (StringUtils.isEmpty(user.getUserId())) {
            return new Result(AjaxCode.PARAM_ERROR, "密码不能为空");
        }
        User authentication = userInfoService.loginIn(Integer.valueOf(user.getUserId()), user.getPassword());
        if (authentication == null) {
            throw new AuthenticationException("用户名或密码错误");
        }
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            subject.login(new UsernamePasswordToken(user.getUserId(), user.getPassword()));
        }
        session.setAttribute(UserConstants.CURRENT_USER, Integer.valueOf(user.getUserId()));
        return SUCCESS;
    }

    @RequestMapping(value = "/loginState", method = RequestMethod.GET)
    public Result getLoginState() {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return SUCCESS;
        }
        return new Result(AjaxCode.USER_NOT_LOGIN, "用户未登录");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
            return SUCCESS;
        }
        return new Result(AjaxCode.USER_NOT_LOGIN, "用户未登录");
    }
}
