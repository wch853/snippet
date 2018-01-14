package com.wch.test.aop.proxy.cglib;

import com.wch.test.aop.proxy.pattern.RealSubject;
import com.wch.test.aop.proxy.pattern.Subject;
import org.springframework.cglib.proxy.Enhancer;

public class CglibProxyClient {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RealSubject.class);
        enhancer.setCallback(new CglibMethodInterceptor());
        Subject subject = (Subject) enhancer.create();
        subject.request();
    }
}
