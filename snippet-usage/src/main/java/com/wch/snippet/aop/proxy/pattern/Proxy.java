package com.wch.snippet.aop.proxy.pattern;

/**
 * 代理对象
 */
public class Proxy implements Subject {

    /**
     * 将目标对象委托给代理对象
     */
    private RealSubject realSubject;

    public Proxy(RealSubject realSubject) {
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
