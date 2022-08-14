package com.sylvanas.tree.iterator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostOrderTraversal {

  public static void postOrderTraversal(TreeNode node, List<Integer> resultList) {
    if (node == null) {
      return;
    }
    postOrderTraversal(node.left, resultList);
    postOrderTraversal(node.right, resultList);
    resultList.add(node.val);
  }


  public static void postOrderTraversalByRecursion(TreeNode node, List<Integer> resultList) {
    Stack<TreeNode> stack = new Stack<>();
    stack.push(node);
    stack.push(node);
    while (!stack.isEmpty()) {
      TreeNode topNode = stack.pop();
      if (!stack.isEmpty() && topNode == stack.peek()) {
        if (topNode.right != null) {
          stack.push(topNode.right);
          stack.push(topNode.right);
        }
        if (topNode.left != null) {
          stack.push(topNode.left);
          stack.push(topNode.left);
        }
      } else {
        resultList.add(topNode.val);
      }
    }
  }

  public static void main(String[] args) {
    TreeNode node = new TreeNode(new TreeNode(new TreeNode(null, null, 50), new TreeNode(null, null, 23), 20),
        new TreeNode(new TreeNode(null, null, 11), new TreeNode(null, null, 23), 77),
        100);
    List<Integer> resultList = new ArrayList<>();
    postOrderTraversalByRecursion(node, resultList);
    resultList.forEach(System.out::println);
  }


}
