package com.sylvanas.leetcode.medium;

import java.util.*;

/**
 * https://leetcode.cn/problems/zlDJc7/
 * https://leetcode.cn/problems/open-the-lock/
 */
public class OpenLock {

  public int openLock(String[] deadends, String target) {
    return bfs(deadends, target);
  }

  public static String plusOne(String cur, int index) {
    char[] result = cur.toCharArray();
    if (result[index] == '9')
      result[index] = '0';
    else
      result[index] += 1;
    return new String(result);
  }

  public static String subOne(String cur, int index) {
    char[] result = cur.toCharArray();
    if (result[index] == '0')
      result[index] = '9';
    else
      result[index] -= 1;
    return new String(result);
  }

  public int bfs(String[] deadends, String target) {
    Set<String> visitSet = new HashSet<>(Arrays.asList(deadends));
    Queue<String> queue = new LinkedList<>();
    if (visitSet.contains("0000")) {
      return -1;
    }
    queue.offer("0000");
    visitSet.add("0000");
    int tryTimes = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      while (size > 0) {
        String cur = queue.poll();
        if (cur.equals(target)) {
          return tryTimes;
        }
        for (int i = 0; i < 4; i++) {
          String addOne = plusOne(cur, i);
          if (visitSet.add(addOne)) {
            queue.offer(addOne);
          }
          String subOne = subOne(cur, i);
          if (visitSet.add(subOne)) {
            queue.offer(subOne);
          }
        }
        size--;
      }
      tryTimes++;
    }
    return -1;
  }
}
