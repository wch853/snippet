package com.wch.snippet.proxy.dynamic;

import com.wch.snippet.proxy.subject.RealSubject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理类，相当于AOP的aspect
 *
 * @author wch
 */
public class JdkProxySubject implements InvocationHandler {

    private RealSubject realSubject;

    public JdkProxySubject(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    /**
     * 基于反射的动态代理，针对目标方法进行动态代理
     *
     * @param proxy  方法反射的代理对象
     * @param method 目标方法
     * @param args   目标方法参数
     * @return 增强的返回值
     * @throws Throwable Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("### before.");
        Object result;
        try {
            result = method.invoke(realSubject, args);
        } catch (Exception e) {
            System.out.println("ex: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("### after.");
        }
        return result;
    }
}
