package com.sylvanas.leetcode.medium;

import java.util.*;

/**
 * https://leetcode.cn/problems/binary-tree-right-side-view/?envType=study-plan-v2&envId=top-100-liked
 */
public class RightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Deque<TreeNode> deque = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            while (size > 0) {

                TreeNode curNode = deque.poll();
                if (curNode.left != null) {
                    deque.offer(curNode.left);
                }
                if (curNode.right != null) {
                    deque.offer(curNode.right);
                }
                if (size == 1) {
                    result.add(curNode.val);
                }
                size--;
            }
        }
        return result;
    }

    private List<Integer> res = new ArrayList<>();

    public List<Integer> rightSideViewInDfs(TreeNode root) {
        dfs(root, 0); // 从根节点开始访问，根节点深度是0
        return res;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        // 先访问 当前节点，再递归地访问 右子树 和 左子树。
        // 如果当前节点所在深度还没有出现在res里，说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入res中。 前提是右子树先遍历
        if (depth == res.size()) {
            res.add(root.val);
        }
        depth++;
        dfs(root.right, depth);
        dfs(root.left, depth);
    }


}
