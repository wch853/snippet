package com.wch.snippet.algorithm.sort;

import java.util.Arrays;

/**
 * @author wch
 */
public class QuickSort {

    private static void sort(int[] nums) {
        if (null == nums || nums.length == 0) {
            return;
        }

        quickSort(nums, 0, nums.length - 1);
    }

    private static void quickSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        // i指向第一个不小于当前分界点的索引
        int i = l;
        // 分界点元素，每次取最后一个元素
        int pivot = nums[r];
        // j指向扫描的元素的索引
        for (int j = l; j < r; j++) {
            if (nums[j] < pivot) {
                // 与i位置元素交换，并且i向右移动一位
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
        }
        // 分界点元素位置确定，两边分别进行排序
        nums[r] = nums[i];
        nums[i] = pivot;
        quickSort(nums, l, i - 1);
        quickSort(nums, i + 1, r);
    }

    public static void main(String[] args) {
        int[] nums = {5, 4, 3, 2, 1};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
