package com.sylvanas.leetcode.medium;

/**
 * https://leetcode.cn/problems/path-sum-iii/?envType=study-plan-v2&envId=top-100-liked
 */
public class PathNum {

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int cnt = rootSum(root, targetSum);
        cnt += pathSum(root.left, targetSum);
        cnt += pathSum(root.right, targetSum);
        return cnt;
    }

    private int rootSum(TreeNode node, long targetSum) {
        int cnt = 0;
        if (node == null) {
            return 0;
        }
        if (targetSum == node.val) {
            cnt++;
        }

        cnt += rootSum(node.left, targetSum - node.val);
        cnt += rootSum(node.right, targetSum - node.val);

        return cnt;
    }


}
