package com.wch.origin.jmx;

public interface HelloMBean {

    String getName();

    void setName(String name);

    int getAge();

    void setAge(int age);

    void helloWorld();

    void helloWorld(String str);
}
