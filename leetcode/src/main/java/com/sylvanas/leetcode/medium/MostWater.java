package com.sylvanas.leetcode.medium;

/**
 * https://leetcode.cn/problems/container-with-most-water/?envType=study-plan-v2&envId=top-100-liked
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * <p>
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 返回容器可以储存的最大水量。
 */
public class MostWater {

    public static int maxArea(int[] height) {
        if (height == null || height.length < 1) {
            return 0;
        }
        int right = height.length - 1;
        int max = 0;
        while (right > 0) {
            int left = 0;
            while (left < right) {
                max = Math.max(max, Math.min(height[left], height[right]) * (right - left));
                left++;
            }
            int preRight = right;
            right--;
            while (right > 0 && height[right] < height[preRight]) {
                right--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});
    }

}
