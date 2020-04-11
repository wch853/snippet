package com.wch.snippet.invoke;

import java.lang.reflect.Method;

/**
 * 通过反射执行方法
 */
public class InvokeCompute {

    public static void main(String[] args) {
        Compute compute = new Compute();
        Class c = compute.getClass();
        Method addInt;
        Method addString;
        try {
            // 获取方法add(int a, int b);
            addInt = c.getMethod("add", int.class, int.class);
            // 获取方法add(String x, String y);
            addString = c.getMethod("add", String.class, String.class);

            Object obj1 = addInt.invoke(compute, 1, 2);
            System.out.println(obj1);

            Object obj2 = addString.invoke(compute, "Hello ", "World!");
            System.out.println(obj2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
