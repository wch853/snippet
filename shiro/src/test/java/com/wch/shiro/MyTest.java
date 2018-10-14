package com.wch.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;

public class MyTest {

    public static void main(String[] args) {
        SimpleHash md5 = new SimpleHash("md5", "123456", "!@#", 1);
        System.out.println(md5);
    }
}
