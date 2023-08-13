package com.sylvanas.leetcode.medium;

public class ExceptSelfArray {

    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int left = 1;
        for (int i = 0; i < res.length; i++) {
            // 此时数组存储的是除去当前元素左边的元素乘积
            res[i] = left;
            left = left * nums[i];
        }
        int right = 1;
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i]; // 此时数组等于左边的 * 该数右边的。
        }
        return res;
    }

}
