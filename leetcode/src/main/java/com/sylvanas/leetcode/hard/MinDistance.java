package com.sylvanas.leetcode.hard;

import java.util.Arrays;

public class MinDistance {

  private int[][] memo;

  public int minDistanceUsingMemo(String s1, String s2) {
    int firstLength = s1.length();
    int secondLength = s2.length();
    memo = new int[firstLength][secondLength];
    Arrays.fill(memo, -1);
    return dp(s1, firstLength - 1, s2, secondLength - 1);
  }

  /**
   * 自顶向下 先列父问题代数解 递归到子问题解 从最优子结构求解
   */
  public int dp(String s1, int i, String s2, int j) {
    // 两者都为-1时 返回值为0 不影响结果
    if (i == -1) {
      // 因为走完了s1 要让两者相等 就是删除或插入对应的剩余字符串长度 下标换算为操作数就是 数组下标+1(因为数组是从后向前遍历)
      return j + 1;
    }
    if (j == -1) {
      // 因为走完了s2 要让两者相等 就是删除或插入对应的剩余字符串长度 下标换算为操作数就是 数组下标+1(因为数组是从后向前遍历)
      return i + 1;
    }

    if (memo[i][j] != -1) {
      return memo[i][j];
    }

    if (s1.charAt(i) == s2.charAt(j)) {
      memo[i][j] = dp(s1, i - 1, s2, j - 1);
    } else {
      memo[i][j] = min(dp(s1, i, s2, j - 1) + 1, // s1插入字符
          dp(s1, i - 1, s2, j) + 1, // s1删除字符
          dp(s1, i - 1, s2, j - 1) + 1 // s1替换字符
      );
    }
    return memo[i][j];
  }

  /**
   * 自底向上 先求子问题解 逐步累加到最后的父问题
   */
  public int minDistanceUsingDp(String s1, String s2) {
    int m = s1.length(), n = s2.length();
    int[][] dp = new int[m + 1][n + 1];
    // base case
    for (int i = 1; i <= m; i++) {
      dp[i][0] = i;
    }
    for (int j = 1; j <= n; j++) {
      dp[0][j] = j;
    }
    // 自底向上求解
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = min(dp[i - 1][j] + 1,
              dp[i][j - 1] + 1,
              dp[i - 1][j - 1] + 1);
        }
      }
    }
    // 储存着整个 s1 和 s2 的最小编辑距离
    return dp[m][n];
  }

  int min(int a, int b, int c) {
    return Math.min(a, Math.min(b, c));
  }

}
