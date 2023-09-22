package com.sylvanas.leetcode.hard;


import java.util.Arrays;

/**
 * https://leetcode.cn/problems/russian-doll-envelopes/
 */
public class MaxEnvelops {

  public int maxEnvelopes(int[][] envelopes) {
    if (envelopes == null || envelopes.length == 0) {
      return 0;
    }
    Arrays.sort(envelopes, (a, b) ->
        a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

    int[] dp = new int[envelopes.length];

    for (int i = 0; i < envelopes.length; i++) {
      // 取高度
      dp[i] = envelopes[i][1];
    }
    return lengthOfLISByBinarySearch(dp);
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

}
