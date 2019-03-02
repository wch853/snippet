package com.wch.snippet.ds.recursive;

public class Sum {

    public static int sum(int arr[]) {
        return sum(arr, 0);
    }

    private static int sum(int arr[], int l) {
        int result = 0;
        if (0 <= l && l <= arr.length -1) {
            result = arr[l] + sum(arr, l + 1);
        } else {
            throw new IllegalArgumentException("invalid index");
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 10;
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        System.out.println(sum(arr));
    }
}
