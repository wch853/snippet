package com.wch.snippet.proxy.staticproxy;

import com.wch.snippet.proxy.subject.RealSubject;
import com.wch.snippet.proxy.subject.Subject;

/**
 * 静态代理对象
 *
 * @author wch
 */
public class StaticProxy implements Subject {

    /**
     * 将目标对象委托给代理对象
     */
    private RealSubject realSubject;

    public StaticProxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    /**
     * 代理类实现相同方法来对目标对象进行增强
     */
    @Override
    public void request() {
        System.out.println("### before.");
        try {
            realSubject.request();
        } catch (Exception e) {
            System.out.println("ex: " + e.getMessage() + ".");
            throw e;
        } finally {
            System.out.println("### after.");
        }
    }
}
