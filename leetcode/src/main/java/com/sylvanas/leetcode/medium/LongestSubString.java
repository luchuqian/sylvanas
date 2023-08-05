package com.sylvanas.leetcode.medium;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/?envType=study-plan-v2&envId=top-100-liked
 * <p>
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class LongestSubString {

    /**
     * 利用双端队列
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringByDeque(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int max = 0;
        Deque<Character> deque = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (deque.contains(c)) {
                max = Math.max(deque.size(), max);
                while (deque.peekFirst() != c) {
                    deque.pollFirst();
                }
                // 把重复元素移除
                if (deque.size() > 0) {
                    deque.pollFirst();
                }
            }
            deque.add(c);
        }
        return Math.max(max, deque.size());
    }


}
