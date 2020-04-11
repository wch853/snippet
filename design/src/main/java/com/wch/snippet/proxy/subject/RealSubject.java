package com.wch.snippet.proxy.subject;

/**
 * 目标对象实现
 *
 * @author wch
 */
public class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("real subject execute request.");
    }
}
