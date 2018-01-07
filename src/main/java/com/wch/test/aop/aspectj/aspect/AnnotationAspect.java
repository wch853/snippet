package com.wch.test.aop.aspectj.aspect;

import com.wch.test.aop.aspectj.AuthUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 切面（使用注解匹配）
 */
@Aspect
@Component
public class AnnotationAspect {

    @Resource
    private AuthUtil authUtil;

    /**
     * 匹配有指定注解的方法
     */
    @Pointcut("@annotation(com.wch.test.aop.aspectj.AdminOnly)")
    public void adminOnly() {
    }

    @Before("adminOnly()")
    public void before() {
        authUtil.checkAccess();
    }

    /**
     * 匹配有指定注解的类，且注解的RetentionPolicy为CLASS
     */
    @Pointcut("@within(com.wch.test.annotation.custom.Beta)")
    public void within() {
    }

    /**
     * 匹配有指定注解的类，且注解的RetentionPolicy为RUNTIME
     */
    @Pointcut("@target(org.springframework.jmx.export.annotation.ManagedResource)")
    public void target() {
    }

    /**
     * 匹配传入参数含指定注解的方法
     */
    @Pointcut("@args(org.springframework.stereotype.Repository)")
    public void args() {
    }
}
