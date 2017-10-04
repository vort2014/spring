package io.github.vort2014.spring.aspect;

import io.github.vort2014.spring.service.AspectService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created on 24.05.2017.
 */
@Aspect
@Configurable   // spring container configures this created object outside container as bean
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    // we made aspect as Spring Bean so we can inject anything
    @Autowired
    private AspectService aspectService;

    @Around("call(@LogMethod * *.*(..))")
    public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.nanoTime();
        Object o = proceedingJoinPoint.proceed();
        long time = System.nanoTime() - start;
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String fullMethodName = method.getDeclaringClass().getName() + "." + method.getName();
        aspectService.log(fullMethodName, time, TimeUnit.NANOSECONDS);
        return o;
    }
}
