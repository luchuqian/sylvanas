package com.sylvanas.leetcode.medium;

/**
 * https://leetcode.cn/problems/sort-list/?envType=study-plan-v2&envId=top-100-liked
 */
public class SortLinkNode {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode middle = findMiddle(head);
        ListNode rightHead = middle.next;
        middle.next = null;

        ListNode sortLeft = sortList(head);
        ListNode sortRight = sortList(rightHead);
        return merge(sortLeft, sortRight);
    }

    public ListNode merge(ListNode left, ListNode right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        ListNode sentinelNode = new ListNode(-1);
        ListNode head = sentinelNode;
        while (left != null && right != null) {
            if (left.val < right.val) {
                head.next = left;
                left = left.next;
            } else {
                head.next = right;
                right = right.next;
            }
            head = head.next;
        }
        head.next = left != null ? left : right;
        return sentinelNode.next;
    }


    public ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = slow.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
