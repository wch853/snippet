package com.wch.snippet.proxy.cglib;

import com.wch.snippet.proxy.subject.RealSubject;
import net.sf.cglib.proxy.Enhancer;

/**
 * CGLIB 动态代理生成代理对象
 *
 * @author wch
 */
public class CglibProxyClient {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        // 设置代理增强逻辑
        enhancer.setCallback(new CglibMethodInterceptor());
        // 设置目标对象类型
        enhancer.setSuperclass(RealSubject.class);
        // 创建代理对象
        RealSubject subject = (RealSubject) enhancer.create();
        subject.request();
    }
}
