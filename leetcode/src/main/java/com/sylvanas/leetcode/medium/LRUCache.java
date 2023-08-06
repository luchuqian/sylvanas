package com.sylvanas.leetcode.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://leetcode.cn/problems/lru-cache/?envType=study-plan-v2&envId=top-100-liked
 */
public class LRUCache {
    private int max;
    private AtomicInteger size;
    private Map<Integer, LRUNode> cache;
    private LRUNode head;
    private LRUNode tail;


    public LRUCache(int capacity) {
        max = capacity;
        size = new AtomicInteger(0);
        cache = new HashMap<>();
        head = new LRUNode();
        tail = new LRUNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        LRUNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (max == 0) {
            return;
        }
        LRUNode node = cache.get(key);
        if (node == null) {
            if (size.get() == max) {
                LRUNode tailNode = popTail();
                if (tailNode != null) {
                    cache.remove(tailNode.key);
                    size.decrementAndGet();
                }
            }
            LRUNode newNode = new LRUNode();
            newNode.key = key;
            newNode.val = value;
            addFirst(newNode);
            cache.put(key, newNode);
            size.incrementAndGet();
        } else {
            node.val = value;
            moveToHead(node);
        }

    }

    public void addFirst(LRUNode node) {
        if (node == null) {
            return;
        }
        LRUNode curHead = head.next;
        curHead.prev = node;
        node.next = curHead;
        head.next = node;
        node.prev = head;
    }


    public void moveToHead(LRUNode node) {
        if (node == null) {
            return;
        }
        removeNode(node);
        addFirst(node);
    }

    public LRUNode popTail() {
        if (tail.prev == head) {
            return null;
        }
        LRUNode tailNode = tail.prev;
        removeNode(tailNode);
        return tailNode;
    }

    public void removeNode(LRUNode node) {
        if (node == null || node.equals(head) || node.equals(tail)) {
            return;
        }
        LRUNode prevNode = node.prev;
        LRUNode nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }


    static class LRUNode {
        public Integer key;
        public Integer val;
        public LRUNode prev;
        public LRUNode next;

        public LRUNode() {
        }


    }

}
