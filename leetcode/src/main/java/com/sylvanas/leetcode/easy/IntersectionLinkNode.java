package com.sylvanas.leetcode.easy;

import com.sylvanas.leetcode.medium.TwoSum;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.cn/problems/intersection-of-two-linked-lists/?envType=study-plan-v2&envId=top-100-liked
 * <p>
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 * <p>
 * 图示两个链表在节点 c1 开始相交：
 */
public class IntersectionLinkNode {


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null | headB == null) {
            return null;
        }
        Set<ListNode> noedSet = new HashSet<>();
        while (headA != null) {
            noedSet.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (noedSet.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
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

}
