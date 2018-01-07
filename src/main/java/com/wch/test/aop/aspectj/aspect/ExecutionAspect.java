package com.wch.test.aop.aspectj.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 切面（使用execution()匹配指定返回值、方法名、参数的方法）
 */
@Aspect
@Component
public class ExecutionAspect {

    /**
     * 匹配指定返回值、方法名、参数的方法
     */
    @Pointcut("execution(public void *..AopProduct*..delete*(Long)) throws java.lang.RuntimeException")
    public void matchExecution() {
    }

    @After("matchExecution()")
    public void after() {
        System.out.println("### after execution.");
    }

    @Pointcut("execution(public String *..AopProduct*..query*(Long))")
    public void matchQueryMethod() {
    }

    /**
     * after returning
     *
     * @param result 获取的指定方法的返回值
     */
    @AfterReturning(value = "matchQueryMethod()", returning = "result")
    public void afterReturning(Object result) {
        System.out.println("### after returning, result: " + result + ".");
    }

    @Pointcut("execution(public boolean *..AopProduct*..update*(Long))")
    public void matchUpdateMethod() {
    }

    /**
     * around
     */
    @Around("matchUpdateMethod()")
    public Object around(ProceedingJoinPoint joinPoint) {
        System.out.println("### before.");
        Object result = null;
        try {
            result = joinPoint.proceed(joinPoint.getArgs());
            System.out.println("### after returning");
        } catch (Throwable e) {
            System.out.println("### after throwing, " + e.getMessage());
        } finally {
            System.out.println("### after");
        }
        return result;
    }
}
