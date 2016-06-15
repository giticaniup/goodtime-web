package com.goodtime.user.controller;

import com.github.api.entity.User;
import com.github.api.service.UserInfoService;
import com.goodtime.base.result.BaseResult;
import com.goodtime.user.enums.UserCodeEnums;
import com.goodtime.user.results.UserResult;
import com.kode.api.DemoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.regex.Pattern;

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

    @RequestMapping("/loginPage")
    public String register() {
        return "userLogin";
    }

    @RequestMapping("/userLogin")
    @ResponseBody
    public BaseResult userLogin(HttpSession session, String userId, String password){
        Pattern pattern = Pattern.compile("[0-9]+");
        if(!pattern.matcher(userId).matches()) {
            return new UserResult(UserCodeEnums.USER_NOTLOGIN);
        }
        if(StringUtils.isEmpty(password)){
            return new UserResult(UserCodeEnums.USER_NOTLOGIN);
        }
        User user = userInfoService.loginIn(Integer.valueOf(userId),password);
        if(user != null){
            session.setAttribute("userId",user.getUserId());
            return new BaseResult();
        }
        return new UserResult(UserCodeEnums.USER_NOTLOGIN);
    }
}
