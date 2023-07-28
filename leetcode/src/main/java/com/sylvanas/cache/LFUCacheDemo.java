package com.sylvanas.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * LFU - least frequently used 最近最少使用
 * 双hash表+双向链表
 * 其中一个hash表用来记录访问次数对应链表
 * 实际上就是在LRU的基础上增加了一个次数记录的机制
 */
public class LFUCacheDemo {

    public static void main(String[] args) {
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
        lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        lfu.get(1);      // 返回 1
        // cache=[1,2], cnt(2)=1, cnt(1)=2
        lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
        // cache=[3,1], cnt(3)=1, cnt(1)=2
        lfu.get(2);      // 返回 -1（未找到）
        lfu.get(3);      // 返回 3
        // cache=[3,1], cnt(3)=2, cnt(1)=2
        lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
        // cache=[4,3], cnt(4)=1, cnt(3)=2w
        lfu.get(1);      // 返回 -1（未找到）
        lfu.get(3);      // 返回 3
        // cache=[3,4], cnt(4)=1, cnt(3)=3
        lfu.get(4);      // 返回 4
        // cache=[3,4], cnt(4)=2, cnt(3)=3
    }



    static class LFUCache {
        private Map<Integer, LFUNode> cacheMap;
        private Map<Integer, LFUNodeList> freqMap;
        private int max;
        private AtomicInteger size;
        private AtomicInteger minFreq;

        public LFUCache(int max) {
            this.max = max;
            this.cacheMap = new ConcurrentHashMap<>();
            this.freqMap = new ConcurrentHashMap<>();
            this.size = new AtomicInteger(0);
            this.minFreq = new AtomicInteger(0);
        }

        public int get(Integer key) {
            LFUNode node = cacheMap.get(key);
            if (node == null) {
                return -1;
            }
            freqInc(node);
            return node.val;
        }


        public void put(Integer key, Integer val) {
            if (max == 0) {
                return;
            }
            LFUNode node = cacheMap.get(key);
            if (node == null) {
                if (size.get() == max) {
                    LFUNodeList lfuNodeList = freqMap.get(minFreq.get());
                    LFUNode expireNode = lfuNodeList.popTail();
                    cacheMap.remove(expireNode.key);
                    size.decrementAndGet();
                }
                LFUNode newNode = new LFUNode(key, val);
                LFUNodeList freqList = freqMap.getOrDefault(newNode.freq.get(), new LFUNodeList());
                freqList.addFirst(newNode);
                freqMap.put(newNode.freq.get(), freqList);
                cacheMap.put(key, newNode);
                size.incrementAndGet();
                minFreq.set(1);
            } else {
                node.val = val;
                freqInc(node);
            }
        }


        private void freqInc(LFUNode node) {
            LFUNodeList freqList = freqMap.get(node.freq.get());
            freqList.removeNode(node);
            if (node.freq.get() == minFreq.get() && freqList.head.next == freqList.tail) {
                minFreq.incrementAndGet();
            }
            ;
            LFUNodeList newFreqList = freqMap.getOrDefault(node.freq.incrementAndGet(), new LFUNodeList());
            newFreqList.addFirst(node);
            freqMap.put(node.freq.get(), newFreqList);
        }

    }

    static class LFUNode {
        public Integer key;
        public Integer val;
        public AtomicInteger freq;
        public LFUNode prev;
        public LFUNode next;

        public LFUNode() {
            this.freq = new AtomicInteger(1);
        }

        public LFUNode(Integer key, Integer val) {
            this.key = key;
            this.val = val;
            this.freq = new AtomicInteger(1);
        }


    }

    static class LFUNodeList {
        LFUNode head;
        LFUNode tail;

        public LFUNodeList() {
            head = new LFUNode();
            tail = new LFUNode();
            head.next = tail;
            tail.prev = head;

        }

        public void addFirst(LFUNode node) {
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            node.prev = head;
        }


        public void moveToHead(LFUNode node) {
            if (head != node && tail != node) {
                removeNode(node);
                addFirst(node);
            }
        }

        public void removeNode(LFUNode node) {
            if (head != node && tail != node) {
                LFUNode prevNode = node.prev;
                LFUNode nextNode = node.next;
                prevNode.next = nextNode;
                nextNode.prev = prevNode;
            }
        }

        public LFUNode popTail() {
            LFUNode tailNode = tail.prev;
            tailNode.prev.next = tail;
            tail.prev = tailNode.prev;
            return tailNode;
        }
    }


}
