package com.wch.snippet.annotation.analysis;

import java.lang.reflect.Method;

public class AnalysisAnnotation {

    @Description(desc = "do1 method", author = "wch")
    public void do1() {
        System.out.println("do something...");
    }

    public static void main(String[] args) {
        try {
            Class c = Class.forName("com.wch.snippet.annotation.analysis.AnalysisAnnotation");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("do1");
            boolean isExistAnnotation = method.isAnnotationPresent(Description.class);
            if (isExistAnnotation) {
                Description description = method.getAnnotation(Description.class);
                System.out.println(description.desc());
            }
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
