package com.sylvanas.leetcode.medium;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.TreeMap;

/**
 * https://leetcode.cn/problems/implement-trie-prefix-tree/?envType=study-plan-v2&envId=top-100-liked
 */
@Data
public class TrieTree {
    private TrieNode root;
    private int size;

    public TrieTree() {
        this.root = new TrieNode();
        this.size = 0;
    }

    public void insert(String word) {
        if (StringUtils.isEmpty(word)) {
            return;
        }
        TrieNode cur = this.root;
        for (Character c : word.toCharArray()) {
            TreeMap<Character, TrieNode> curCharToTrieNodeMap = cur.getNext();
            TrieNode characterNode = curCharToTrieNodeMap.get(c);
            if (characterNode == null) {
                characterNode = new TrieNode();
                curCharToTrieNodeMap.put(c, characterNode);
            }
            cur = characterNode;
        }
        if (cur.isNotWord()) {
            cur.setWord(true);
            this.size++;
        }
    }


    public boolean search(String word) {
        if (StringUtils.isEmpty(word)) {
            return false;
        }
        TrieNode cur = this.root;
        for (Character c : word.toCharArray()) {
            TreeMap<Character, TrieNode> curCharToTrieNodeMap = cur.getNext();
            TrieNode characterNode = curCharToTrieNodeMap.get(c);
            if (characterNode == null) {
                return false;
            } else {
                cur = characterNode;
            }
        }
        return cur.isWord();
    }

    public boolean startsWith(String prefix) {
        if (StringUtils.isEmpty(prefix)) {
            return false;
        }
        TrieNode cur = this.root;
        for (Character c : prefix.toCharArray()) {
            TreeMap<Character, TrieNode> curCharToTrieNodeMap = cur.getNext();
            TrieNode characterNode = curCharToTrieNodeMap.get(c);
            if (characterNode == null) {
                return false;
            } else {
                cur = characterNode;
            }
        }
        return true;
    }

}


@Data
class TrieNode {
    private TreeMap<Character, TrieNode> next;
    private boolean word;

    public TrieNode() {
        this.word = false;
        this.next = new TreeMap<>();
    }


    public boolean isWord() {
        return word;
    }

    public boolean isNotWord() {
        return !isWord();
    }

    public void setWord(boolean word) {
        this.word = word;
    }

    public TreeMap<Character, TrieNode> getNext() {
        return next;
    }

    public void setNext(TreeMap<Character, TrieNode> next) {
        this.next = next;
    }
}
