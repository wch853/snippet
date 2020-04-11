package com.wch.snippet.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 *
 * @author wch
 */
public class InsertSort {

    private static void sort(int[] nums) {
        if (null == nums || nums.length == 0) {
            return;
        }

        // 从索引为1的元素开始，往前搜索大于当前元素的最小元素，在目标元素前插入当前元素
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (current < nums[j]) {
                    // 比当前元素大的元素都向后移动
                    nums[j + 1] = nums[j];
                } else {
                    // 比已经按规则排序的左侧元素更大，无需继续比较
                    break;
                }
            }
            // 找到当前元素需要移动的位置
            nums[j + 1] = current;
        }
    }

    public static void main(String[] args) {
        int[] nums = {5, 4, 3, 2, 1};
        sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
