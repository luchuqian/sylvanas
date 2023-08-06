package com.sylvanas.leetcode.easy;


import com.sylvanas.leetcode.easy.IntersectionLinkNode.ListNode;

/**
 * https://leetcode.cn/problems/palindrome-linked-list/?envType=study-plan-v2&envId=top-100-liked
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 */
public class PalindromeLinkedList {


    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null && slow != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 奇数链表 慢指针在中间 后移一位
        if (fast != null) {
            slow = slow.next;
        }
        ListNode right = reverse(slow);
        while (right != null) {
            if (right.val != head.val) {
                return false;
            }
            right = right.next;
            head = head.next;
        }
        return true;
    }

    public ListNode reverse(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode preNode = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = preNode;
            preNode = head;
            head = next;
        }
        return preNode;
    }

}
