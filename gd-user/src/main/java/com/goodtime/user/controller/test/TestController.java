package com.goodtime.user.controller.test;

import com.github.api.entity.UserTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试类
 * Created by zhongcy on 2016/6/14.
 */
@Controller()
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    /**
     * 测试javaBean中的日期如何转换
     */
    @RequestMapping("/testDate")
    public void testDate(UserTask userTask) {
        logger.error(userTask.toString());
    }

    public void aopTest(){
        System.out.println("aopTest");
    }

    public static void testStatic(){
        System.out.println("static");
    }
}
