package com.wch.origin.dubbo.service.impl;


import com.wch.origin.dubbo.service.HelloService;

public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hello! " + name;
    }
}
