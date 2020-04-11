package com.wch.snippet.aop.aspectj.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 切面（使用this()/subject()/bean()匹配指定对象）
 */
@Aspect
@Component
public class ObjectAspect {

    /**
     * 匹配对象
     */
    @Pointcut("this(com.wch.snippet.aop.aspectj.service.impl.AopProductServiceImpl)")
    public void matchThis() {
    }

    @Before("matchThis()")
    public void before() {
        System.out.println("### before this.");
    }

    /**
     * 匹配实现指定接口的对象
     */
    @Pointcut("target(com.wch.snippet.aop.aspectj.service.AopProductService)")
    public void matchTarget() {
    }

    @After("matchTarget()")
    public void after() {
        System.out.println("### after subject.");
    }

    /**
     * 匹配以指定字符串为bean名称结尾的bean
     */
    @Pointcut("bean(*aopProductServiceImpl)")
    public void matchBean() {
    }

    @After("matchBean()")
    public void around() {
        System.out.println("### after bean.");
    }
}
