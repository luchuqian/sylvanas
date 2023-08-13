package com.sylvanas.leetcode.hard;

import com.sylvanas.tree.iterator.TreeNode;

/**
 * https://leetcode.cn/problems/binary-tree-maximum-path-sum/solutions/297005/er-cha-shu-zhong-de-zui-da-lu-jing-he-by-leetcode-/?envType=study-plan-v2&envId=top-100-liked
 */
public class MaxPathSum {
    private int maxPath = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxPath;
    }


    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftMax = Math.max(dfs(node.left), 0);
        int rightMax = Math.max(dfs(node.right), 0);

        int curMax = node.val + leftMax + rightMax;

        maxPath = Math.max(maxPath, curMax);

        return node.val + Math.max(leftMax, rightMax);

    }


}
