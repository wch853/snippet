package com.wch.test.aop.aspectj.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 切面（使用within()匹配指定包/类型）
 */
@Aspect
@Component
public class WithinAspect {

    /**
     * 匹配类型
     */
    @Pointcut("within(com.wch.test.aop.aspectj.service.impl.AopProductServiceImpl)")
    public void matchType() {
    }

    @Before("matchType()")
    public void before() {
        System.out.println("### before type.");
    }

    /**
     * 匹配包（通过..扩展匹配子包）
     */
    @Pointcut("within(com.wch.test.aop.aspectj.service..*)")
    public void matchPackage() {
    }

    @After("matchPackage()")
    public void after() {
        System.out.println("### after package.");
    }
}
