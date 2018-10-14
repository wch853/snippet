package com.wch.snippet.jmx;

/**
 * 定义MBean接口
 */
public interface HelloMBean {

    String getName();

    void setName(String name);

    int getAge();

    void setAge(int age);

    void helloWorld();

    void helloWorld(String str);
}