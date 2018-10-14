package com.wch.snippet.aop.aspectj.aspect;

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
    @Pointcut("args(Long, ..) && within(com.wch.snippet.aop.aspectj.service..*)")
    public void matchArgs() {
    }

    @Before("matchArgs()")
    public void before() {
        System.out.println("### before args.");
    }

    /**
     * 通过args()获取参数，与before结合可用于检验参数
     *
     * @param id id
     */
    @Before("matchArgs() && args(id)")
    public void getArgsBefore(Long id) {
        System.out.println("### before get args, id = " + id + ".");
    }
}
