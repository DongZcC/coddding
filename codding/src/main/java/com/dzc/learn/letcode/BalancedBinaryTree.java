package com.dzc.learn.letcode;

public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        return helper(root);
    }

    private boolean helper(TreeNode root) {
        int leftHight = getHight(root.left);
        int rightHithg = getHight(root.right);
        return false;
    }

    private int getHight(TreeNode node) {
        return 0;
    }


}
