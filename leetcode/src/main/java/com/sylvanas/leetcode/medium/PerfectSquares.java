package com.sylvanas.leetcode.medium;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/perfect-squares/?envType=study-plan-v2&envId=top-100-liked
 */
public class PerfectSquares {


    public int numSquares(int n) {
        // 定义：和为 i 的平方数的最小数量是 dp[i]
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        // base case
        dp[0] = 0;
        // 状态转移方程
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                // i - j * j 只要再加一个平方数 j * j 即可凑出 i
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

}
