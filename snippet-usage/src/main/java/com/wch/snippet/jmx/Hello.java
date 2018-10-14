package com.wch.snippet.jmx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MBean实现
 */
public class Hello implements HelloMBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(Hello.class);

    private String name;

    private int age;

    @Override
    public String getName() {
        LOGGER.info("get name: {}", name);
        return name;
    }

    @Override
    public void setName(String name) {
        LOGGER.info("set name: {}", name);
        this.name = name;
    }

    @Override
    public int getAge() {
        LOGGER.info("age: {}", age);
        return age;
    }

    @Override
    public void setAge(int age) {
        LOGGER.info("set age: {}", age);
        this.age = age;
    }

    @Override
    public void helloWorld() {
        LOGGER.info("say hello world!");
    }

    @Override
    public void helloWorld(String str) {
        LOGGER.info("hello world, {}", str);
    }
}
