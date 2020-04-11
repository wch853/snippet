package com.wch.snippet.algorithm.offer;

/**
 * @author wch
 */
public class Problem14 {

    private int dynamic(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        } else if (n == 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        }

        int[] multi = new int[n + 1];
        // 1、2、3作为被切割成为的部分，其乘积可以取自身
        multi[1] = 1;
        multi[2] = 2;
        multi[3] = 3;

        for (int i = 4; i <= n; i++) {
            int max = 0;
            for (int j = 1; j <= i / 2; j++) {
                int value = multi[j] * multi[i - j];
                if (value > max) {
                    max = value;
                }
            }
            multi[i] = max;
        }

        return multi[n];
    }

    public static void main(String[] args) {
        System.out.println(new Problem14().dynamic(10));
    }
}
