package com.sylvanas.leetcode.hard;


import java.util.PriorityQueue;

/**
 * https://leetcode.cn/problems/median-of-two-sorted-arrays/solutions/258842/xun-zhao-liang-ge-you-xu-shu-zu-de-zhong-wei-s-114/?envType=study-plan-v2&envId=top-100-liked
 */
public class FindOrderArrMidNum {
  public PriorityQueue<Integer> asdHeap = new PriorityQueue<>();
  public PriorityQueue<Integer> descHeap = new PriorityQueue<>((a, b) -> b - a);


  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    for (int n : nums1) {
      addHeap(n);
    }
    for (int n : nums2) {
      addHeap(n);
    }
    if (asdHeap.size() == descHeap.size()) {
      return (asdHeap.peek() + descHeap.peek()) / 2.0d;
    } else if (asdHeap.size() > descHeap.size()) {
      return asdHeap.peek();
    } else {
      return descHeap.size();
    }
  }


  private void addHeap(int num) {
    if (asdHeap.size() >= descHeap.size()) {
      asdHeap.offer(num);
      descHeap.offer(asdHeap.poll());
    } else {
      descHeap.offer(num);
      asdHeap.offer(descHeap.poll());
    }
  }


  public static void main(String[] args) {
    new FindOrderArrMidNum().findMedianSortedArrays(new int[]{1}, new int[]{2, 3});
  }


}
