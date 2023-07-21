package com.sylvanas.leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/minimum-window-substring/
 */
public class MinWindow {


  public String minWindow(String s, String t) {
    Map<Character, Integer> needs = new HashMap<>();
    Map<Character, Integer> window = new HashMap<>();
    for (Character c : t.toCharArray()) {
      needs.put(c, needs.getOrDefault(c, 0) + 1);
    }
    int start = 0;
    int left = 0;
    int right = 0;
    int minLength = Integer.MAX_VALUE;
    int containSize = 0;
    while (right < s.length()) {
      Character c = s.charAt(right);
      right++;
      if (needs.containsKey(c)) {
        window.put(c, window.getOrDefault(c, 0) + 1);
        if (needs.get(c).equals(window.get(c))) {
          containSize++;
        }
      }
      while (containSize == needs.size()) {
        if (right - left < minLength) {
          start = left;
          minLength = right - left;
        }
        Character d = s.charAt(left++);
        if (needs.containsKey(d)) {
          if (needs.get(d).equals(window.get(d))) {
            containSize--;
          }
          window.put(d, window.get(d) - 1);
        }
      }
    }
    return minLength == Integer.MAX_VALUE ? "" : s.substring(start, start + minLength);
  }
}
