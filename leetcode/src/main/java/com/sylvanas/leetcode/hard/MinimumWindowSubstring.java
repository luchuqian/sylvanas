package com.sylvanas.leetcode.hard;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/minimum-window-substring/?envType=study-plan-v2&envId=top-100-liked
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 */
public class MinimumWindowSubstring {

    public static String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0 || s.length() < t.length()) {
            return "";
        }
        Map<Character, Integer> targetDic = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetDic.put(c, targetDic.getOrDefault(c, 0) + 1);
        }

        int startIndex = 0;
        int minLen = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;

        Map<Character, Integer> windowDic = new HashMap<>();
        int windowDicSize = 0;
        while (right < s.length()) {
            char c = s.charAt(right++);
            if (targetDic.containsKey(c)) {
                windowDic.put(c, windowDic.getOrDefault(c, 0) + 1);
                if (targetDic.get(c).equals(windowDic.get(c))) {
                    windowDicSize++;
                }
            }
            while (windowDicSize == targetDic.size()) {
                if (right - left < minLen) {
                    startIndex = left;
                    minLen = right - left;
                }
                char removeChar = s.charAt(left);
                left++;
                if (targetDic.containsKey(removeChar)) {
                    if (targetDic.get(removeChar).equals(windowDic.get(removeChar))) {
                        windowDicSize--;
                    }
                    windowDic.put(removeChar, windowDic.get(removeChar) - 1);
                }
            }
        }
        return minLen != Integer.MAX_VALUE ? s.substring(startIndex, startIndex + minLen) : "";
    }

    public static void main(String[] args) {
        minWindow("a", "a");
    }

}
