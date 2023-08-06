package com.sylvanas.leetcode.medium;

import java.util.*;

/**
 * https://leetcode.cn/problems/find-all-anagrams-in-a-string/?envType=study-plan-v2&envId=top-100-liked
 * <p>
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * <p>
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 */
public class FindAllAnagramInString {

    public static List<Integer> findAnagrams(String s, String p) {
        if (s == null || s.length() == 0 || s.length() < p.length()) {
            return Collections.emptyList();
        }
        List<Integer> resultList = new ArrayList<>();
        char[] targetChars = p.toCharArray();
        Arrays.sort(targetChars);
        String target = String.valueOf(targetChars);
        int start = 0;
        while (start <= s.length() - p.length()) {
            String subStr = s.substring(start, start + p.length());
            if (isEquals(subStr, target)) {
                resultList.add(start);
            }
            start++;
        }

        return resultList;
    }

    public static boolean isEquals(String subString, String target) {
        char[] subChars = subString.toCharArray();
        Arrays.sort(subChars);
        return target.equals(String.valueOf(subChars));
    }

    public static void main(String[] args) {
        findAnagrams("cbaebabacd", "abc");
    }


}
