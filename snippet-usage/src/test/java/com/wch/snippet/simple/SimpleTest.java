package com.wch.snippet.simple;


import org.testng.annotations.Test;

import java.io.IOException;

@Test
public class SimpleTest {

    public void test1() throws IOException {
        int[] nums = {1, 2, 3, 4};

        findUnsortedSubarray(nums);
    }

    public int findUnsortedSubarray(int[] nums) {
        // 左右第一个拐点
        int left = 0, right = 0;
        boolean hasLeft = false;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i+1]) {
                left = i;
                hasLeft = true;
                break;
            }
        }

        if (!hasLeft) {
            return 0;
        }

        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] < nums[i-1]) {
                right = i;
                break;
            }
        }

        int min = Integer.MAX_VALUE, max = 0;
        for (int i = left; i <= right; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }

            if (nums[i] > max) {
                max = nums[i];
            }
        }

        for (int i = 0; i < left; i++) {
            if (nums[i] > min) {
                left = i;
                break;
            }
        }

        for (int i = nums.length - 1; i > right; i--) {
            if (nums[i] < max) {
                right = i;
                break;
            }
        }

        return right - left + 1;
    }
}
