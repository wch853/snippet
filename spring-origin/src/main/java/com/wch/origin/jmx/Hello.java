package com.wch.origin.jmx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource(objectName = "com.wch.base.jmx:name=Hello")
public class Hello {

    private static final Logger LOGGER = LoggerFactory.getLogger(Hello.class);

    private String name;

    private int age;

    @ManagedOperation
    public String getName() {
        LOGGER.info("get name: {}", name);
        return name;
    }

    @ManagedAttribute
    public void setName(String name) {
        LOGGER.info("set name: {}", name);
        this.name = name;
    }

    @ManagedOperation
    public int getAge() {
        LOGGER.info("age: {}", age);
        return age;
    }

    @ManagedAttribute
    public void setAge(int age) {
        LOGGER.info("set age: {}", age);
        this.age = age;
    }

    @ManagedOperation
    public void helloWorld() {
        LOGGER.info("say hello world!");
    }

    @ManagedOperation
    public void helloWorld(String str) {
        LOGGER.info("hello world, {}", str);
    }
}
