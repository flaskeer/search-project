package com.cheche.annoation.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by user on 2016/3/30.
 */
@Component
@Aspect
public class RecordTimeAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecordTimeAspect.class);

    @Around("execution(@com.cheche.annoation.RecoreTime * *.*(..))")
    public Object recordTime(final ProceedingJoinPoint joinPoint) throws Throwable {
        long st = System.nanoTime();
        String targetMethodName = joinPoint.getSignature().getName();
        Object o = joinPoint.proceed();
        double cost = (System.nanoTime() - st) / 1000000000;
        LOGGER.debug("method {} cost time {} 's",targetMethodName,cost);
        return o;
    }
}
