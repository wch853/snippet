package com.wch.snippet.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 基于CGLIB的动态代理
 *
 * @author wch
 */
public class CglibMethodInterceptor implements MethodInterceptor {

    /**
     * 通过继承来实现代理
     *
     * @param o           目标对象
     * @param method      目标对象方法
     * @param args        方法参数
     * @param methodProxy 方法代理
     * @return 经过额外操作的方法返回值
     * @throws Throwable Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("### before.");
        Object result;
        try {
            result = method.invoke(o, args);
        } catch (Exception e) {
            System.out.println("ex: " + e.getMessage() + ".");
            throw e;
        } finally {
            System.out.println("### after.");
        }
        return result;
    }
}
