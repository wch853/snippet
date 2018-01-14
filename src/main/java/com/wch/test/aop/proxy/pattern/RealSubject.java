package com.wch.test.aop.proxy.pattern;

/**
 * 目标对象
 */
public class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("real subject execute request.");
    }
}
