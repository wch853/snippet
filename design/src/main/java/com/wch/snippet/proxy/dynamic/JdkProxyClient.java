package com.wch.snippet.proxy.dynamic;

import com.wch.snippet.proxy.subject.RealSubject;
import com.wch.snippet.proxy.subject.Subject;

import java.lang.reflect.Proxy;

/**
 * @author wch
 */
public class JdkProxyClient {

    public static void main(String[] args) {
        Subject subject = (Subject) Proxy.newProxyInstance(RealSubject.class.getClassLoader(),
                new Class[]{Subject.class}, new JdkProxySubject(new RealSubject()));
        subject.request();
    }

}
