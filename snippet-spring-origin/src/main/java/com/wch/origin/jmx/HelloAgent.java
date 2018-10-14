package com.wch.origin.jmx;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class HelloAgent {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring/spring-jmx.xml");
        context.start();

        System.in.read();
    }
}
