package com.sylvanas.leetcode.hard;

import java.util.*;

/**
 * https://leetcode.cn/problems/sliding-window-maximum/?envType=study-plan-v2&envId=top-100-liked
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回 滑动窗口中的最大值 。
 */
public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        Deque<Integer> maxDeque = new LinkedList<>();
        int[] result = new int[nums.length - k + 1];
        int resultIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                push(maxDeque, nums[i]);
            } else {
                // 到这里形成第一个窗口 先进单调队列
                push(maxDeque, nums[i]);
                result[resultIndex++] = maxDeque.getFirst();
                pop(maxDeque, nums[i - k + 1]);
            }

        }
        return result;
    }


    /**
     * 保持队列单调
     * 记录从第一个窗口往后的单调值
     *
     * @param deque
     * @param ele
     */
    public void push(Deque<Integer> deque, int ele) {
        while (!deque.isEmpty() && deque.peekLast() < ele) {
            deque.pollLast();
        }
        deque.addLast(ele);
    }


    public void pop(Deque<Integer> deque, int ele) {
        if (deque.getFirst() == ele) {
            deque.pollFirst();
        }
    }


}
