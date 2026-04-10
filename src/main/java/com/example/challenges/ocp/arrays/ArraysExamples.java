package com.example.challenges.ocp.arrays;

import java.util.Arrays;

public class ArraysExamples {
    public static void main(String[] args) {
        int[] nums = {1, 23, 12, 54};

        System.out.println(nums.length);
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));

        System.out.println(Arrays.binarySearch(nums, 12));

        System.out.println(Arrays.compare(new int[]{1, 23, 12, 54}, new int[]{1, 23, 12, 54})); // 0
        System.out.println(Arrays.compare(new int[]{1, 2, 12, 54}, new int[]{1, 23, 12, 54})); // -1
        System.out.println(Arrays.compare(new int[]{7, 2, 12, 54}, new int[]{1, 23, 12, 54})); // 1

        System.out.println(Arrays.mismatch(new int[]{1, 23, 12, 54}, new int[]{1, 23, 12, 54})); // -1
        System.out.println(Arrays.mismatch(new int[]{1, 2, 12, 54}, new int[]{1, 23, 12, 54})); // 1
        System.out.println(Arrays.mismatch(new int[]{7, 2, 12, 54}, new int[]{1, 23, 12, 54})); // 0
        System.out.println(Arrays.mismatch(new int[]{1, 23, 12, 54}, new int[]{1, 23, 12, 54, 11})); // 4
    }
}
