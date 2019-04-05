package com.dzc.learn.letcode;


/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * <p>
 * Recover the tree without changing its structure.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,3,null,null,2]
 * <p>
 * 1
 * /
 * 3
 * \
 * 2
 * <p>
 * Output: [3,1,null,null,2]
 * <p>
 * 3
 * /
 * 1
 * \
 * 2
 * Example 2:
 * <p>
 * Input: [3,1,4,null,null,2]
 * <p>
 *  3
 * / \
 * 1   4
 *    /
 *   2
 * <p>
 * 1 , 3 , 2 , 4
 * Output: [2,1,4,null,null,3]
 * <p>
 * 2
 * / \
 * 1   4
 * /
 * 3
 * <p>
 * 二叉搜索树，中序遍历的话 值是递增的关系
 * 那这样的话只要找到两个顺序不对的记住； 交换一下value 就可以了?
 */
public class RecoverBinarySearchTree {


    private TreeNode first;

    private TreeNode second;

    private TreeNode prev;

    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }
        helper(root);

        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void helper(TreeNode root) {
        if (root == null) return;
        helper(root.left);
        if (prev != null && prev.val >= root.val) {
            if (first == null) {
                first = prev;
            }
            second = root;
        }
        prev = root;
        helper(root.right);
    }

}
