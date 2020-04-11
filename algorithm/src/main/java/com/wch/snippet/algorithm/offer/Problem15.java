package com.wch.snippet.algorithm.offer;

/**
 * 输入一个整数，输出该数二进制表示中 1 的个数。
 * <p>
 * Input: 12 (0b1100)
 * <p>
 * Output: 2
 *
 * @author wch
 */
public class Problem15 {

    /**
     * n 与 n - 1
     * 当 n 的最后一位是 1，如 1101，n - 1 = 1100，&运算的结果为 1100，消除了最后一个 1
     * 当 n 的最后一位是 0，如 1100，n - 1 = 1011，&运算的结果为 1000，消除了最后一个 1
     * 因此能通过 n & （n - 1） 消除多少次 1，即原二进制数中 1 的个数
     *
     * @param n
     * @return
     */
    public int solution(int n) {
        int count = 0;
        while (0 != n) {
            n &= (n - 1);
            count++;
        }
        return count;
    }
}
