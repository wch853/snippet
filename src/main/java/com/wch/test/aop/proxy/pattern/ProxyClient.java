package com.wch.test.aop.proxy.pattern;

public class ProxyClient {

    public static void main(String[] args) {
        Subject subject = new Proxy(new RealSubject());
        subject.request();
    }
}
