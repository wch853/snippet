package com.wch.snippet.algorithm.offer;

/**
 * 给定一个二维数组，其每一行从左到右递增排序，从上到下也是递增排序。给定一个数，判断这个数是否在该二维数组中
 * <p>
 * Input: 7
 * <p>
 * {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}}
 * <p>
 * Output: true
 *
 * @author wch
 */
public class Problem4 {

    /**
     * 从右上角找起，由于每行、每列都是增序排序
     * 如果目标值小于右上角，则当前列所有值都大于目标值，进入前一列查找
     * 如果目标值大于右上角，则目标值大于当前行所有值，进入下一行查找
     */
    private static boolean solution(int[][] nums, int target) {
        if (null == nums || nums.length == 0 || nums[0].length == 0) {
            return false;
        }
        int rows = nums.length;
        int cols = nums[0].length;
        // 从矩阵的右上角找起
        int i = 0, j = cols - 1;
        while (i < rows && j >= 0) {
            int val = nums[i][j];
            if (val == target) {
                // 找到
                return true;
            } else if (val > target) {
                // 进入前一列查找
                j--;
            } else {
                // 进入下一行查找
                i++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };

        System.out.println(solution(nums, 7));
    }
}
