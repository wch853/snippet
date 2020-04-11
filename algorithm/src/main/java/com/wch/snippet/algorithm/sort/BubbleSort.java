package com.wch.snippet.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author wch
 */
public class BubbleSort {

    private static void sort(int[] nums) {
        if (null == nums || nums.length == 0) {
            return;
        }
        // n 个元素，通过 n - 1 轮冒泡排序
        for (int i = 0; i < nums.length - 1; i++) {

            // 判断该轮是否有交换，如果某一轮没有交换，说明剩下的元素已经按规则排序
            boolean change = false;

            // 每轮确定顶层某个元素位置，减少一个元素的排序
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j);
                    change = true;
                }
            }

            if (!change) {
                break;
            }
        }
    }

    /**
     * 交换元素
     *
     * @param nums
     * @param j
     */
    private static void swap(int[] nums, int j) {
        int temp = nums[j];
        nums[j] = nums[j + 1];
        nums[j + 1] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {5, 4, 3, 2, 1};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
