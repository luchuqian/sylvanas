package com.sylvanas.leetcode.hard;

/**
 * https://leetcode.cn/problems/trapping-rain-water/?envType=study-plan-v2&envId=top-100-liked
 */
public class TrappingRainWater {

    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int result = 0;
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax < rightMax) {
                result += leftMax - height[left];
                left++;
            } else {
                result += rightMax - height[right];
                right--;
            }
        }
        return result;
    }

}
