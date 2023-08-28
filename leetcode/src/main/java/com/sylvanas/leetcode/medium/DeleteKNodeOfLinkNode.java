package com.sylvanas.leetcode.medium;

import com.sylvanas.leetcode.medium.SumTwoLinkNode.ListNode;

/**
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/?envType=study-plan-v2&envId=top-100-liked
 */
public class DeleteKNodeOfLinkNode {

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        ListNode preNode = new ListNode(-1);
        preNode.next = head;
        ListNode nodeBeforeDel = findFromEndK(preNode, n + 1);
        // 当preNode走到空时 此时正好head处于被删除节点的前一个
        nodeBeforeDel.next = nodeBeforeDel.next.next;
        return preNode.next;
    }

    public static ListNode findFromEndK(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        ListNode h1 = head;
        for (int i = 0; i < k; i++) {
            h1 = h1.next;
        }
        ListNode result = head;
        // 遍历完后正好走了k步 假设链表长度为n 则链表剩余n-k个长度
        while (h1 != null) {
            h1 = h1.next;
            result = result.next;
        }
        return result;
    }


    public static ListNode removeNthFromEndByReverse(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        ListNode curHead = reverse(head);
        ListNode preNode = null;
        ListNode reverseHead = curHead;
        while (n > 0 && curHead != null) {
            n--;
            if (n == 0) {
                if (preNode == null) {
                    ListNode next = curHead.next;
                    curHead.next = null;
                    return reverse(next);
                } else {
                    preNode.next = curHead.next;
                    return reverse(reverseHead);
                }
            }
            preNode = curHead;
            curHead = curHead.next;
        }
        return head;
    }


    public static ListNode reverse(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode preNode = null;
        while (head != null) {
            ListNode nextNode = head.next;
            head.next = preNode;
            preNode = head;
            head = nextNode;
        }
        return preNode;
    }

    public static void main(String[] args) {
        System.out.println(removeNthFromEnd(
                new ListNode(1,
                        new ListNode(2,
                                new ListNode(3, null))),
                3));
    }

}
