package com.sylvanas.leetcode.medium;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/house-robber/?envType=study-plan-v2&envId=top-100-liked
 */
public class HouseRobber {

    // 备忘录
    private int[] memo;

    // 主函数
    public int rob(int[] nums) {
        // 初始化备忘录
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        // 强盗从第 0 间房子开始抢劫
        return dp(nums, 0);
    }

    // 返回 dp[start..] 能抢到的最大值
    private int dp(int[] nums, int start) {
        if (start >= nums.length) {
            return 0;
        }
        // 避免重复计算
        if (memo[start] != -1) {
            return memo[start];
        }

        int res = Math.max(
                dp(nums, start + 1),
                nums[start] + dp(nums, start + 2));
        // 记入备忘录
        memo[start] = res;
        return res;
    }


    public int rob2(int[] nums) {
        int currMax = 0;
        int preMax = 0;
        for (int num : nums) {
            int temp = currMax;
            currMax = Math.max(preMax + num, currMax);
            preMax = temp;
        }
        return currMax;
    }
}
