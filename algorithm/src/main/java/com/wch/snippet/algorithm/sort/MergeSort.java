package com.wch.snippet.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @author wch
 */
public class MergeSort {

    private static void sort(int[] nums) {
        if (null == nums || nums.length == 0) {
            return;
        }

        mergeSort(nums, 0, nums.length - 1);
    }

    private static void mergeSort(int[] nums, int l, int r) {
        if (l < r) {
            int mid = l + (r - l) / 2;
            // 一直拆分直到一个区间中只有一个元素
            mergeSort(nums, l, mid);
            mergeSort(nums, mid + 1, r);
            // 对两个有序区间进行合并
            merge(nums, l, r, mid);
        }
    }

    private static void merge(int[] nums, int l, int r, int mid) {
        int i = l, j = mid + 1;
        int[] temp = new int[r - l + 1];
        int index = 0;
        while (i <= mid && j <= r) {
            // 分别从两个有序区间的最小元素开始比较，放入临时数组
            if (nums[i] < nums[j]) {
                temp[index++] = nums[i++];
            } else {
                temp[index++] = nums[j++];
            }
        }

        // 区间中剩余的元素有序放入临时数组
        if (i <= mid) {
            for (; i <= mid; i++) {
                temp[index++] = nums[i];
            }
        }
        if (j <= r) {
            for (; j <= r; j++) {
                temp[index++] = nums[j];
            }
        }

        // 将临时数组中的有序元素拷贝回原数组
        for (int pos = r; pos >= l; pos--) {
            nums[pos] = temp[--index];
        }
    }

    public static void main(String[] args) {
        int[] nums = {5, 4, 3, 2, 1};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
