package com.sylvanas.leetcode.easy;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/maximum-depth-of-binary-tree/?envType=study-plan-v2&envId=top-100-liked
 */
public class DeepOfTree {

    public int maxDepthByLevel(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            depth++;
        }
        return depth;
    }

    private int maxDeep = Integer.MIN_VALUE;

    public int maxDepthByTraverse(TreeNode root) {
        if (root == null) {
            return 0;
        }
        traverse(0, root);
        return maxDeep;
    }

    public void traverse(int count, TreeNode node) {
        if (node == null) {
            maxDeep = Math.max(count, maxDeep);
            return;
        }
        count++;
        traverse(count, node.left);
        traverse(count, node.right);
    }


}
