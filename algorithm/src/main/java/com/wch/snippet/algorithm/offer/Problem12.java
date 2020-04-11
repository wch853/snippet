package com.wch.snippet.algorithm.offer;

/**
 * 判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向上下左右移动一个格子。
 * 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
 * <p>
 * Input:
 * a    b   t   g
 * c    f   c   s
 * j    d   e   h
 * <p>
 * bfce
 * <p>
 * Output: true
 *
 * @author wch
 */
public class Problem12 {

    /**
     * 坐标移动
     */
    private static int[][] move = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    /**
     * 矩阵的行和列数
     */
    private static int rows, cols;

    /**
     * 回溯法
     * 在当前搜索过程结束后需要进行回溯，并清除当前搜索过程中设置的状态
     */
    private static boolean solution(char[] matrix, char[] str, int rows, int cols) {
        int i = 0;
        // 构建矩阵
        char[][] origin = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                origin[r][c] = matrix[i++];
            }
        }

        Problem12.rows = rows;
        Problem12.cols = cols;

        // 矩阵中的每个字符均尝试作为首个字符开始搜索
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // 标记指定坐标的字符是否已经使用
                boolean[][] marked = new boolean[rows][cols];
                boolean found = search(origin, str, 0, r, c, marked);
                if (found) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean search(char[][] origin, char[] str, int pathLen, int r, int c, boolean[][] marked) {
        if (pathLen == str.length) {
            // 搜索结束
            return true;
        }

        if (r < 0 || c < 0 || r >= rows || c >= cols) {
            // 搜索越界
            return false;
        }

        if (marked[r][c]) {
            // 此坐标已被标记
            return false;
        }

        if (origin[r][c] == str[pathLen]) {
            // 当前坐标满足，确认四周的坐标是否满足
            marked[r][c] = true;
            for (int[] way : move) {
                if (search(origin, str, pathLen + 1, r + way[0], c + way[1], marked)) {
                    // 向下搜索能一直搜索到
                    return true;
                }
            }
            // 四周不满足，则当前坐标标记无效，返回上一个坐标搜索相邻的其它坐标是否满足条件
            marked[r][c] = false;
        }
        return false;
    }
}
