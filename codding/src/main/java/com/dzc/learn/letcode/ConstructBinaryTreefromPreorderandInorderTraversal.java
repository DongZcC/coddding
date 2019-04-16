package com.dzc.learn.letcode;

import java.util.Arrays;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * <p>
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * <p>
 * For example, given
 * <p>
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 */
public class ConstructBinaryTreefromPreorderandInorderTraversal {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        int rootValue = preorder[0];
        TreeNode root = new TreeNode(rootValue);
        int index = getRootIndex(inorder, rootValue);
        int[] leftInorder = Arrays.copyOfRange(inorder, 0, index);
        int[] rightInorder = Arrays.copyOfRange(inorder, index + 1, inorder.length);
        int[] leftPreorder = Arrays.copyOfRange(preorder, 1, 1 + leftInorder.length);
        int[] rightPreorder = Arrays.copyOfRange(preorder, 1 + leftInorder.length, preorder.length);
        root.left = buildTree(leftPreorder, leftInorder);
        root.right = buildTree(rightPreorder, rightInorder);
        return root;
    }

    private int getRootIndex(int[] inorder, int rootValue) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootValue)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        ConstructBinaryTreefromPreorderandInorderTraversal c = new ConstructBinaryTreefromPreorderandInorderTraversal();
        c.buildTree(preorder, inorder);
    }
}
