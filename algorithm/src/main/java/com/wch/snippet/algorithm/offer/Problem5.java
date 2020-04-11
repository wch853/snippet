package com.wch.snippet.algorithm.offer;

/**
 * 将一个字符串中的空格替换成 "%20"
 * <p>
 * Input: "A B"
 * <p>
 * Output: "A%20B"
 *
 * @author wch
 */
public class Problem5 {

    /**
     * 给定一个字符串数组，往后有足够的内存空间用于移动
     * 如果从前往后替换，则对于n个空格的替换，其后的n个字符都需要移动，复杂度为 O(n2)
     * 因此选择先获取空格个数，计算出移动后末尾字符对应的位置，从后往前移动，复杂度为 O(n)
     */
    private static void solution(char[] s, int originSize) {
        int spaceCount = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == ' ') {
                spaceCount++;
            }
        }
        // 末尾指针
        int tail = 2 * spaceCount + originSize - 1;
        // 原始末尾指针
        int origin = originSize - 1;
        while (origin >= 0) {
            if (s[origin] == ' ') {
                // 原来为空格，替换为 %20
                s[tail] = '0';
                s[--tail] = '2';
                s[--tail] = '%';
            } else {
                s[tail] = s[origin];
                tail--;
                origin--;
            }
        }
    }

    public static void main(String[] args) {
        char[] s = new char[5];
        String origin = "A B";
        for (int i = 0; i < origin.length(); i++) {
            s[i] = origin.charAt(i);
        }
        solution(s, 3);
        System.out.println(s);
    }
}
