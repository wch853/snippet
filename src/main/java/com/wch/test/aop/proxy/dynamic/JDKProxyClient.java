package com.wch.test.aop.proxy.dynamic;

import com.wch.test.aop.proxy.pattern.RealSubject;
import com.wch.test.aop.proxy.pattern.Subject;

import java.lang.reflect.Proxy;

public class JDKProxyClient {

    public static void main(String[] args) {
        Subject subject = (Subject) Proxy.newProxyInstance(JDKProxyClient.class.getClassLoader(),
                new Class[]{Subject.class}, new JDKProxySubject(new RealSubject()));
        subject.request();
    }
}
