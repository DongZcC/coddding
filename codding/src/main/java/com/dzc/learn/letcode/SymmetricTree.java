package com.dzc.learn.letcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1
 * /  \
 * 2    3
 * / \  / \
 * 3   n 2  n
 */
public class SymmetricTree {

    // [1,2,3,3,null,2,null]

    public boolean isSymmetric2(TreeNode root) {
        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;

        if (t1 == null || t2 == null) return false;

        return (t1.val == t2.val) &&
                isMirror(t1.left, t2.right)
                && isMirror(t1.right, t2.left);
    }


    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        dfsleft(root.left, left);
        dfsright(root.right, right);

        if (left.size() != right.size()) {
            return false;
        }

        for (int i = 0; i < left.size(); i++) {
            int l = left.get(i);
            int r = right.get(i);
            if (l != r) {
                return false;
            }
        }

        return true;
    }

    private void dfsleft(TreeNode node, List<Integer> list) {
        if (node == null) {
            list.add(0);
            return;
        }

        list.add(node.val);
        dfsleft(node.left, list);
        dfsleft(node.right, list);
    }

    private void dfsright(TreeNode node, List<Integer> list) {
        if (node == null) {
            list.add(0);
            return;
        }

        list.add(node.val);
        dfsright(node.right, list);
        dfsright(node.left, list);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(2);
        TreeNode n4 = new TreeNode(3);
        TreeNode n5 = new TreeNode(3);

        root.left = n2;
        root.right = n4;
        n2.left = n5;
        n4.left = n3;

        SymmetricTree st = new SymmetricTree();
        st.isSymmetric(root);
    }
}
