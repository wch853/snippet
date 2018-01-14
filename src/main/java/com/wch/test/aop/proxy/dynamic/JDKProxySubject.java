package com.wch.test.aop.proxy.dynamic;

import com.wch.test.aop.proxy.pattern.RealSubject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理类，相当于AOP的aspect
 */
public class JDKProxySubject implements InvocationHandler {

    private RealSubject realSubject;

    public JDKProxySubject(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    /**
     * 基于反射的动态代理，针对有接口的类的接口方法进行动态代理
     *
     * @param proxy  方法反射的代理对象
     * @param method 对象方法
     * @param args   方法参数
     * @return 经过额外操作的方法返回值
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
