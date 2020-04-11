package com.wch.snippet.algorithm.offer;

/**
 * 地上有一个 m 行和 n 列的方格。一个机器人从坐标 (0, 0) 的格子开始移动。
 * 每一次只能向左右上下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于 k 的格子。该机器人能够进入多少个格子？
 * <p>
 * Input：
 * k = 18
 * <p>
 * Output:
 * 机器人能够进入方格 (35, 37)，因为 3 + 5 + 3 + 7 = 18
 * 但是，它不能进入方格 (35, 38)，因为 3 + 5 + 3 + 8 = 19
 *
 * @author wch
 */
public class Problem13 {

    /**
     * 坐标移动
     */
    private static int[][] move = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    /**
     * 可移动坐标数量
     */
    private int count = 0;

    private int threshold, rows, cols;

    private int movingCount(int threshold, int rows, int cols) {
        boolean[][] marked = new boolean[rows][cols];
        this.threshold = threshold;
        this.rows = rows;
        this.cols = cols;

        search(0, 0, marked);
        return count;
    }

    private void search(int r, int c, boolean[][] marked) {
        if (r < 0 || c < 0 || r >= rows || c >= cols) {
            // 搜索越界
            return;
        }

        if (marked[r][c] || add(r) + add(c) > threshold) {
            // 已被标记过或超出限制
            return;
        }

        count++;
        marked[r][c] = true;
        for (int[] way : move) {
            search(r + way[0], c + way[1], marked);
        }
    }

    private int add(int x) {
        int sum = 0;
        while (x > 0) {
            sum += x % 10;
            x = x / 10;
        }
        return sum;
    }
}
