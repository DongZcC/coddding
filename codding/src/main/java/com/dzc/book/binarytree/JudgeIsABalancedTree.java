package com.dzc.book.binarytree;

/**
 * @author Administrator
 * @date 2020-05-30 16:57
 * <p>
 * 判断是否是一颗平衡二叉树 （要不是一颗空树，要不任何一个节点的左右子树的高度差不超过1）
 */
public class JudgeIsABalancedTree {

    public boolean isBalancedTree(Node head) {
        if (head == null) {
            return true;
        }

        boolean[] res = new boolean[1];
        res[0] = true;
        getHeight(head, 0, res);
        return res[0];
    }

    private int getHeight(Node n, int level, boolean[] res) {
        if (n == null) {
            return level;
        }

        int lh = getHeight(n.left, level + 1, res);
        if (!res[0]) {
            return level;
        }

        int rh = getHeight(n.right, level + 1, res);
        if (!res[0]) {
            return level;
        }

        if (Math.abs(lh - rh) > 1) {
            res[0] = false;
        }
        return Math.max(lh, rh);
    }
}
