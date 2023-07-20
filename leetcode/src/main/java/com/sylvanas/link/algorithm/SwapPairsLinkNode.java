package com.sylvanas.link.algorithm;

public class SwapPairsLinkNode {

  public static SingleLinkListNode swapPairs(SingleLinkListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    SingleLinkListNode first = head;
    SingleLinkListNode second = head.next;
    first.next = swapPairs(second.next);
    second.next = first;
    return second;

  }


  public static SingleLinkListNode swapTwo(SingleLinkListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    SingleLinkListNode first = head;
    SingleLinkListNode second = head.next;
    first.next = swapTwo(second.next);
    second.next = first;
    return second;
  }


  /**
   * n个节点一组反转链表
   *
   */
  public static SingleLinkListNode swanN(SingleLinkListNode head, SingleLinkListNode preNode, int n) {
    if (head == null || head.next == null) {
      return head;
    }
    int num = n;

    SingleLinkListNode first = head;
    while (n > 0) {
      SingleLinkListNode next = head.next;
      head.next = preNode;
      preNode = head;
      head = next;
      n--;
    }
    first.next = swanN(head, preNode, num);
    return preNode;
  }

  public static void main(String[] args) {
    SingleLinkListNode node = new SingleLinkListNode(
        new SingleLinkListNode(
            new SingleLinkListNode(
                new SingleLinkListNode(
                    new SingleLinkListNode(
                        new SingleLinkListNode(new SingleLinkListNode(null, 6),
                            5),
                        4),
                    3),
                2)
            , 1),
        0);
    System.out.println(swanN(node, null, 3));
  }

}

