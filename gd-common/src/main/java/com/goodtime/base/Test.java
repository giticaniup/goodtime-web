package com.goodtime.base;

import com.goodtime.base.aspect.LogAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhongcy on 2016/5/10.
 */
public class Test {

    private final static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/spring-common" +
                ".xml");
        LogAspect logAspect = (LogAspect) applicationContext.getBean("logAspect");
        System.out.println(logAspect);
        System.out.println(System.getProperty("user.dir"));
    }
}
