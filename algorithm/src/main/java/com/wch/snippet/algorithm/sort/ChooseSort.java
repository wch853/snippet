package com.wch.snippet.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序
 *
 * @author wch
 */
public class ChooseSort {

    private static void sort(int[] nums) {
        if (null == nums || nums.length == 0) {
            return;
        }

        for (int i = 0; i < nums.length - 1; i++) {
            // 从索引为 i 的元素开始向后所有元素的最小值
            int min = Integer.MAX_VALUE;
            // 从索引为 i 的元素开始向后所有元素的最小值对应的索引
            int minIdx = -1;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] < min) {
                    min = nums[j];
                    minIdx = j;
                }
            }
            // 索引 i 位置的元素与最小元素交换位置
            nums[minIdx] = nums[i];
            nums[i] = min;
        }
    }

    public static void main(String[] args) {
        int[] nums = {5, 4, 3, 2, 1};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
