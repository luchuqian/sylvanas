package com.sylvanas.leetcode.medium;


import java.util.Stack;

/**
 * https://leetcode.cn/problems/daily-temperatures/?envType=study-plan-v2&envId=top-100-liked
 */
public class Temperatures {


    int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        // 这里放元素索引，而不是元素
        Stack<Integer> s = new Stack<>();
        /* 单调栈模板 */
        for (int i = n - 1; i >= 0; i--) {
            while (!s.isEmpty() && temperatures[s.peek()] <= temperatures[i]) {
                s.pop();
            }
            // 得到索引间距
            res[i] = s.isEmpty() ? 0 : (s.peek() - i);
            // 将索引入栈，而不是元素
            s.push(i);
        }
        return res;
    }
}
