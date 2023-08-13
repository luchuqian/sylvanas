package com.sylvanas.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/?envType=study-plan-v2&envId=top-100-liked
 */
public class BuildTreeByInorderAndPreorderArrays {

    private Map<Integer, Integer> valToIndexMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            valToIndexMap.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }


    public TreeNode build(int[] preorder, int preStart, int preEnd,
                          int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        int rootVal = preorder[preStart];

        int index = valToIndexMap.get(rootVal);

        TreeNode root = new TreeNode(rootVal);
        int leftSize = index - inStart;

        root.left = build(preorder, preStart + 1, preStart + leftSize, inorder, inStart, index - 1);
        root.right = build(preorder, preStart + leftSize + 1, preEnd, inorder, index + 1, inEnd);
        return root;

    }

}
