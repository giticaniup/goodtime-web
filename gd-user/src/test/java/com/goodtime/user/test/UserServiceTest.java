package com.goodtime.user.test;

import com.github.api.entity.User;
import com.github.api.service.UserInfoService;
import com.goodtime.base.aspect.LogAspect;
import com.goodtime.user.controller.UserInfoController;
import com.goodtime.user.controller.test.TestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-user.xml")
public class UserServiceTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LogAspect logAspect;

    @Autowired
    private UserInfoService userService;

    @Autowired
    private UserInfoController userInfoController;

    @Test
    public void testUserTest() throws Exception {
        User user = userService.userTest();
        System.out.println(user);
    }
    @Test
    public void testAop(){
        logger.info("logger aop");
//        TestController testController1 = new TestController();
//        testController1.aopTest();

//        testController.aopTest();

        userInfoController.register();
    }

    @Test
    public void testStatic(){
        TestController.testStatic();
    }
}
