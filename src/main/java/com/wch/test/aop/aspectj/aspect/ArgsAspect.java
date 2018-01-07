package com.wch.test.aop.aspectj.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 切面（使用args()匹配含有指定参数的方法）
 */
@Aspect
@Component
public class ArgsAspect {

    /**
     * 匹配含有指定参数的方法
     */
    @Pointcut("args(Long, ..) && within(com.wch.test.aop.aspectj.service..*)")
    public void matchArgs() {
    }

    @Before("matchArgs()")
    public void before() {
        System.out.println("### before args.");
    }
}
