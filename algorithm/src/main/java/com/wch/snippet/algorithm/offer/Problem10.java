package com.wch.snippet.algorithm.offer;

/**
 * 求斐波那契数列的第 n 项。
 *
 * f(0) = 0
 * f(1) = 1
 * f(n) = f(n-1) + f(n-2)
 *
 * @author wch
 */
public class Problem10 {

    private int solution(int n) {
        if (n <= 1) {
            return n;
        }

        int x = 0, y = 1, fib = 0;
        for (int i = 1; i < n; i++) {
            fib = x + y;
            x = y;
            y = fib;
        }
        return fib;
    }

}
