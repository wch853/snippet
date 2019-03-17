package com.wch.origin.dubbo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import javax.annotation.Resource;

@Test
@ContextConfiguration({"classpath:spring/spring-dubbo.xml"})
public class HelloServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloServiceTest.class);

    @Resource
    private HelloService helloServiceImpl;

    public void testSayHello() {
        String back = helloServiceImpl.sayHello("wch");
        LOGGER.info("back: {}", back);
    }
}
