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

  public static void main(String[] args) {
    SingleLinkListNode node = new SingleLinkListNode(
        new SingleLinkListNode(
            new SingleLinkListNode(
                new SingleLinkListNode(
                    new SingleLinkListNode(
                        new SingleLinkListNode(null, 5),
                        4),
                    3),
                2)
            , 1),
        0);
    System.out.println(swapPairs(node));
  }

}

