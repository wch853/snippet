package com.wch.snippet.jdk;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {

    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 2, 3, 4, 5};

        int result = Arrays.stream(arr).map(i -> i * 2).reduce((x, y) -> x > y ? x : y).getAsInt();
        System.out.println(result);


        Integer[] arr2 = new Integer[]{6, 7, 8};
        List<Integer> collect = Stream.of(arr2).collect(Collectors.toList());
    }
}
