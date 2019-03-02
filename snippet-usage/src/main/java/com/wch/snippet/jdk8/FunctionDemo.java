package com.wch.snippet.jdk8;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionDemo {

    public static void interfaceMethod(Function<Integer, String> function, int var) {
        String value = function.apply(var);
        System.out.println(value);
    }

    public static void main(String[] args) {
        Predicate<Integer> predicate = i -> i > 0;
        System.out.println(predicate.test(10));

        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("consume message");

        Function<Integer, String> function = i -> String.valueOf(i * 10);
        interfaceMethod(function.andThen(s -> "multiple 10 : " + s), 10);
    }
}
