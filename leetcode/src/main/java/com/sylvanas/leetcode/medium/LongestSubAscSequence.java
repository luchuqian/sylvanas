package com.sylvanas.leetcode.medium;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/longest-increasing-subsequence/
 */
public class LongestSubAscSequence {

  public int lengthOfLIS(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int[] dp = new int[nums.length];
    Arrays.fill(dp, 1);
    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
    }
    return Arrays.stream(dp)
        .max()
        .getAsInt();
  }


  public int lengthOfLISByBinarySearch(int[] nums) {
    int[] top = new int[nums.length];
    // 堆数初始化为 0
    int heapSize = 0;
    for (int i = 0; i < nums.length; i++) {
      // heapTop(小顶堆)
      int heapTop = nums[i];

      int left = 0, right = heapSize;
      while (left < right) {
        int mid = (left + right) / 2;
        if (top[mid] > heapTop) {
          right = mid;
        } else if (top[mid] < heapTop) {
          left = mid + 1;
        } else {
          right = mid;
        }
      }
      // 没找到合适的堆，新建一堆
      if (left == heapSize) heapSize++;
      // 把这张牌放到堆顶(会覆盖原有的堆顶)
      top[left] = heapTop;
    }
    // 牌堆数就是 LIS 长度
    return heapSize;
  }


  public static void main(String[] args) {
    new LongestSubAscSequence().lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3});
  }


}
