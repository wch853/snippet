package com.wch.snippet.algorithm.offer;

/**
 * 给定一个 double 类型的浮点数 base 和 int 类型的整数 exponent，求 base 的 exponent 次方。
 * <p>
 * Input: base = 2, exponent = -3
 * <p>
 * Output: 0.125
 *
 * @author wch
 */
public class Problem16 {

    /**
     * 当 exponent 为偶数，结果 = base^(n/2) * base^(n/2)
     * 当 exponent 为奇数，结果 = base^((n-1)/2) * base^((n-1)/2) * base
     *
     * @param base
     * @param exponent
     * @return
     */
    private static double pow(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        if (exponent == 1) {
            return base;
        }
        // 判断指数是否为负数
        boolean isNegative = false;
        if (exponent < 0) {
            isNegative = true;
            exponent = -exponent;
        }

        double result;
        if ((exponent & 1) == 1) {
            // 奇数
            double powValue = pow(base, (exponent - 1) / 2);
            result = powValue * powValue * base;
        } else {
            double powValue = pow(base, exponent / 2);
            result = powValue * powValue;
        }
        return isNegative ? 1 / result : result;
    }

    public static void main(String[] args) {
        System.out.println(pow(2, 3));
    }
}
