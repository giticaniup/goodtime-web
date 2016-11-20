package com.goodtime.base.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

/**
 * aop
 * Created by zhongcy on 2016/6/20.
 */
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    public Object around(ProceedingJoinPoint point) throws Throwable {

        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();

        StopWatch totalStopWatch = new StopWatch();
        totalStopWatch.start();
        Object result = null;
        try {
            result = point.proceed();
            return result;
        } catch (Throwable e) {
            logger.error("classname:{}, methodName:{}, args:{},info={}", className, methodName, point.getArgs(), e);
            throw e;
        } finally {
            totalStopWatch.stop();
            logger.info("classname:{}, methodName:{}, args:{},result:{}, timeCost:{} ",
                    className, methodName, point.getArgs(), result, totalStopWatch.getTotalTimeMillis());
        }
    }
}