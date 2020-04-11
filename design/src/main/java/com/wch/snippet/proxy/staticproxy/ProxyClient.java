package com.wch.snippet.proxy.staticproxy;

import com.wch.snippet.proxy.subject.RealSubject;
import com.wch.snippet.proxy.subject.Subject;

/**
 * 静态代理调用
 *
 * @author wch
 */
public class ProxyClient {

    public static void main(String[] args) {
        Subject subject = new StaticProxy(new RealSubject());
        subject.request();
    }
}
