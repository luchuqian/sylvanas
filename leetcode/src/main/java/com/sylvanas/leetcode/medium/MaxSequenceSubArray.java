package com.sylvanas.leetcode.medium;


/**
 * https://leetcode.cn/problems/maximum-subarray/?envType=study-plan-v2&envId=top-100-liked
 */
public class MaxSequenceSubArray {

    public int maxSubArray(int[] nums) {
        int ans = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }

}
