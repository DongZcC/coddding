package com.dzc.learn.letcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *  3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its level order traversal as:
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 * <p>
 * <p>
 * 二叉树的层级遍历问题； 算是比较基础的一个问题了
 */
public class BinaryTreeLevelOrderTraversal {


    // 使用队列的方式;
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;


        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode t = queue.poll();
                subList.add(t.val);
                if (t.left != null)
                    queue.offer(t.left);

                if (t.right != null)
                    queue.offer(t.right);
            }
            res.add(subList);
        }
        return res;
    }


    // 这种做法是使用递归；并利用index.
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, root, 0);
        return res;
    }

    private void helper(List<List<Integer>> res, TreeNode node, int level) {
        if (node == null)
            return;

        if (res.size() == level) {
            List<Integer> subList = new ArrayList<>();
            res.add(subList);
        }

        res.get(level).add(node.val);
        helper(res, node.left, level + 1);
        helper(res, node.right, level + 1);
    }
}
