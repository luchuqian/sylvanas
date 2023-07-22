package com.sylvanas.link.algorithm;

public class ReverseSingleLinkList {

    public static SingleLinkListNode reverse(SingleLinkListNode node) {
        SingleLinkListNode preNode = null;
        while (node != null) {
            SingleLinkListNode next = node.next;
            node.next = preNode;
            preNode = node;
            node = next;
        }
        return preNode;
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
        System.out.println(reverse(node));
    }

}
