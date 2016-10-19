package com.goodtime.user.controller;

import com.github.api.code.AjaxCode;
import com.github.api.entity.User;
import com.github.api.result.Result;
import com.github.api.service.UserInfoService;
import com.goodtime.user.utils.UserConstants;
import com.kode.api.DemoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 用户控制Controller类
 * Created by zhongcy on 2016/5/11.
 */
@RestController
@RequestMapping(value = "/user", produces = "application/json;charset=UTF-8")
public class UserInfoController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private DemoService demoService;

    /**
     * 测试缓存
     */
    @RequestMapping("/test")
    public void test() {
        logger.info("this is controller");
        demoService.say();
        userInfoService.selectById(1);
        userInfoService.selectById(1);
        User user = new User();
        user.setUserId(1);
        user.setAge(22);
        user.setBirthday(new Date());
        user.setGender(0);
        user.setName("lbq");
        user.setPassword("11");
        user.setUserName("lbq");
        userInfoService.updateUser(user);
        logger.info(userInfoService.selectById(1).toString());
    }

    @RequestMapping("/loginPage")
    public String register() {
        return "userLogin";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Result userLogin(HttpSession session, String userId, String password) {
        logger.debug("test");
        Pattern pattern = Pattern.compile("[0-9]+");
        if (!pattern.matcher(userId).matches()) {
            return new Result(AjaxCode.PARAM_ERROR, "请输入正确的用户名");
        }
        if (StringUtils.isEmpty(password)) {
            return new Result(AjaxCode.PARAM_ERROR, "密码不能为空");
        }
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            subject.login(new UsernamePasswordToken(userId, password));
        }
        session.setAttribute(UserConstants.CURRENT_USER, Integer.valueOf(userId));
        return SUCCESS;
    }
}
