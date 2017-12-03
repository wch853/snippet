package com.wch.test.invoke;

public class Compute {

    public void add(int a, int b) {
        System.out.println(a + b);
    }

    public String add(String x, String y) {
        return x.concat(y);
    }
}
