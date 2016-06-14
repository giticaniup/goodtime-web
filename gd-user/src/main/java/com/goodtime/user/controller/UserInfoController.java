package com.goodtime.user.controller;

import com.github.api.entity.User;
import com.github.api.service.UserInfoService;
import com.kode.api.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * 用户控制Controller类
 * Created by zhongcy on 2016/5/11.
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {

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

    @RequestMapping("userRegister")
    public String register() {
        return "userRegister";
    }
}
