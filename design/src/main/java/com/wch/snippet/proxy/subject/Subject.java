package com.wch.snippet.proxy.subject;

/**
 * 代理模式，代理对象和目标对象都需要实现的接口
 *
 * @author wch
 */
public interface Subject {

    /**
     * 目标方法
     */
    void request();
}
