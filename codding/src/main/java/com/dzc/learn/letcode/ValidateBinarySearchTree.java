package com.dzc.learn.letcode;


/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * Assume a BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * Example 1:
 * <p>
 * Input:
 * 2
 * / \
 * 1   3
 * Output: true
 * Example 2:
 * <p>
 * 5
 * / \
 * 1   4
 * / \
 * 3   6
 * Output: false
 * Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
 * is 5 but its right child's value is 4.
 */
public class ValidateBinarySearchTree {

    // [2,1,3]
    public boolean isValidBST(TreeNode root) {
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }


    private boolean helper(TreeNode node, long min, long max) {
        if (node != null) {
            return (node.val < max && node.val > min
                    && helper(node.left, min, node.val)
                    && helper(node.right, node.val, max));
        }
        return true;
    }

    public static void main(String[] args) {
        TreeNode t2 = new TreeNode(2147483647);
        TreeNode t1 = new TreeNode(1);
        TreeNode t3 = new TreeNode(3);
        TreeNode t5 = new TreeNode(5);
        TreeNode t4 = new TreeNode(4);
        TreeNode t6 = new TreeNode(6);

//        t2.left = t1;
//        t2.right = t3;
        ValidateBinarySearchTree v = new ValidateBinarySearchTree();
        System.out.println(v.isValidBST(t2));
    }
}
