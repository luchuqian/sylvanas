package com.sylvanas.tree.iterator;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelTraversal {

    public static List<List<Integer>> levelOrderTraversal(TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        List<List<Integer>> resultList = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelList = new ArrayList<>();
            while (size-- > 0) {
                TreeNode currentNode = queue.poll();
                if (currentNode != null) {
                    levelList.add(currentNode.val);
                    queue.offer(currentNode.left);
                    queue.offer(currentNode.right);
                }
            }
            if (!CollectionUtils.isEmpty(levelList)) {
                resultList.add(levelList);
            }
        }
        return resultList;
    }


    public static void main(String[] args) {
        TreeNode node = new TreeNode(new TreeNode(new TreeNode(null, null, 50), new TreeNode(null, null, 23), 20),
                new TreeNode(new TreeNode(null, null, 11), new TreeNode(null, null, 23), 77),
                100);
        List<List<Integer>> resultList = levelOrderTraversal(node);
        resultList.forEach(list -> {
            list.forEach(System.out::println);
        });
    }

}
