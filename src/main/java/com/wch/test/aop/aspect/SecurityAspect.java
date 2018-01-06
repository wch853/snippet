package com.wch.test.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 切面
 */
@Aspect
@Component
public class SecurityAspect {

    @Resource
    private AuthService authService;

    /**
     * 切入点
     */
    @Pointcut("@annotation(com.wch.test.aop.aspect.AdminOnly)")
    public void adminOnly() {
    }

    @Before("adminOnly()")
    public void check() {
        authService.checkAccess();
    }
}
