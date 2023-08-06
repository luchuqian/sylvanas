package com.sylvanas.leetcode.easy;

import static com.sylvanas.leetcode.easy.IntersectionLinkNode.*;

/**
 * https://leetcode.cn/problems/reverse-linked-list/?envType=study-plan-v2&envId=top-100-liked
 * 反转链表
 */
public class ReverseLinkNode {

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode preNode = head;
        while (head != null) {
            ListNode next = head.next;
            head.next = preNode;
            preNode = head;
            head = next;
        }
        return preNode;
    }


}
