package com.sylvanas.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.cn/problems/find-all-anagrams-in-a-string/
 */
public class FindAnagrams {

  public List<Integer> findAnagrams(String s, String p) {
    Map<Character, Integer> needs = new HashMap<>();
    for (Character c : p.toCharArray()) {
      needs.put(c, needs.getOrDefault(c, 0) + 1);
    }
    Map<Character, Integer> window = new HashMap<>();
    int left = 0;
    int right = 0;
    int valid = 0;
    List<Integer> resultList = new ArrayList<>();
    while (right < s.length()) {
      Character c = s.charAt(right++);
      if (needs.containsKey(c)) {
        window.put(c, window.getOrDefault(c, 0) + 1);
        if (needs.get(c).equals(window.get(c))) {
          valid++;
        }
      }
      while (valid == needs.size()) {
        Character d = s.charAt(left);
        if (right - left == p.length()) {
          resultList.add(left);
        }
        left++;
        if (needs.containsKey(d)) {
          if (needs.get(d).equals(window.get(d))) {
            valid--;
          }
          window.put(d, window.getOrDefault(d, 0) - 1);
        }
      }
    }
    return resultList;
  }


}
