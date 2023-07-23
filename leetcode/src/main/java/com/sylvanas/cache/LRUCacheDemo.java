package com.sylvanas.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * LRU: (Least Recently Used，最近最久未使用) 缓存机制
 * hash表+双向链表
 * hash表用来快速获取k-v映射
 * hash表的值也是链表结构 但是仅有单节点 过期策略以头尾哨兵所包含的决定
 */
public class LRUCacheDemo {


    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2, 1); // 缓存是 {1=1}
        lruCache.put(2, 2); // 缓存是 {1=1, 2=2}
        lruCache.get(2);    // 返回 1
        lruCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lruCache.get(2);    // 返回 -1 (未找到)
        lruCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lruCache.get(1);    // 返回 -1 (未找到)
        lruCache.get(3);    // 返回 3
        lruCache.get(4);    // 返回 4
    }


    static class LRUCache {
        private Map<Integer, LRUNode> cacheMap;
        public LRUNode head;
        public LRUNode tail;
        public int max;
        public AtomicInteger size;


        public LRUCache(int max) {
            cacheMap = new ConcurrentHashMap<>();
            this.max = max;
            this.size = new AtomicInteger(0);
            head = new LRUNode();
            tail = new LRUNode();
            head.next = tail;
            tail.prev = head;
        }

        public LRUNode get(Integer key) {
            LRUNode node = cacheMap.get(key);
            if (node == null) {
                return null;
            }
            moveToHead(node);
            return node;
        }

        public void put(Integer key, Integer val) {
            if (max == 0) {
                return;
            }
            LRUNode node = cacheMap.get(key);
            if (node == null) {
                if (this.size.get() == max) {
                    LRUNode expireNode = popTail();
                    cacheMap.remove(expireNode.key);
                    size.decrementAndGet();
                }
                LRUNode newNode = new LRUNode(key, val);
                cacheMap.put(key, newNode);
                addFirst(newNode);
                size.incrementAndGet();
            } else {
                node.val = val;
                moveToHead(node);
            }
        }

        /**
         * put new ele should call this method
         *
         * @param node
         */
        public void addFirst(LRUNode node) {
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            node.prev = head;
        }

        public void moveToHead(LRUNode node) {
            if (head != node && node != tail) {
                LRUNode prevNode = node.prev;
                LRUNode nextNode = node.next;
                prevNode.next = nextNode;
                nextNode.prev = prevNode;
                addFirst(node);
            }
        }

        public LRUNode popTail() {
            LRUNode expireNode = tail.prev;
            expireNode.prev.next = tail;
            tail.prev = expireNode.prev;
            return expireNode;
        }


    }

    static class LRUNode {
        public Integer key;
        public Integer val;
        public LRUNode prev;
        public LRUNode next;

        public LRUNode() {
        }

        public LRUNode(Integer key, Integer val) {
            this.key = key;
            this.val = val;
        }
    }
}
