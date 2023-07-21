package com.sylvanas.leetcode.medium;

/**
 * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
 * <p>
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 2：
 * <p>
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 示例 3：
 * <p>
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TwoSum {

  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    if (l1 == null) {
      return l2;
    }
    if (l2 == null) {
      return l1;
    }
    ListNode result = new ListNode(0);
    ListNode head = result;
    int carry = 0;
    while (l1 != null || l2 != null) {
      int value1 = l1 != null ? l1.val : 0;
      int value2 = l2 != null ? l2.val : 0;

      int sum = value1 + value2 + carry;
      carry = 0;
      if (sum >= 10) {
        carry = sum / 10;
        sum = sum % 10;
      }
      result.val = sum;


      l1 = l1 != null ? l1.next : null;
      l2 = l2 != null ? l2.next : null;

      if (l1 != null || l2 != null) {
        result.next = new ListNode(0);
        result = result.next;
      } else if (carry > 0) {
        result.next = new ListNode(1);
      }
    }
    return head;
  }

  static class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }

    @Override
    public String toString() {
      return this.val + "" + (next != null ? next.toString() : "");
    }
  }

  public static void main(String[] args) {
    System.out.println(Long.MAX_VALUE);


    ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
    ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
    System.out.println(addTwoNumbers(l1, l2));
  }

}
