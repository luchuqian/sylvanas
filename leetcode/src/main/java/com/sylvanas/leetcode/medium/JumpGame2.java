package com.sylvanas.leetcode.medium;


/**
 * https://leetcode.cn/problems/jump-game-ii/?envType=study-plan-v2&envId=top-100-liked
 */
public class JumpGame2 {

    public int jump(int[] nums) {
        int n = nums.length;
        int end = 0, farthest = 0;
        int jumps = 0;
        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(nums[i] + i, farthest);
            if (end == i) {
                jumps++;
                end = farthest;
            }
        }
        return jumps;
    }
}
