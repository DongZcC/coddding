package com.dzc.book.binarytree;

/**
 * @author Administrator
 * @date 2020-06-02 15:34
 * <p>
 * 二叉树节点间的最大距离
 * <p>
 * 从二叉树的节点A触犯，可以向上或者向下走，沿途节点只能经过一次，到达节点B的时候 路径上的节点叫做A，B的距离
 * <p>
 * 一个以h为头的树， 最大距离只有可能以下三种情况
 * 1、 h的左子树的最大距离
 * 2、 h右子树的最大距离
 * 3、 h左子树离 h.left 最远的距离 + 1 + h右子树上离 h.right 最远的距离
 */
public class FindMaxDistance {

    public int findMaxDistance(Node head) {
        int[] record = new int[1];
        return preOrder(head, record);
    }

    private int preOrder(Node head, int[] record) {
        if (head == null) {
            record[0] = 0;
            return 0;
        }

        int lMax = preOrder(head.left, record);
        int maxFromLeft = record[0];

        int rMax = preOrder(head.right, record);
        int maxFromRight = record[0];

        int curNodeMax = maxFromLeft + maxFromRight + 1;
        record[0] = Math.max(maxFromLeft, maxFromRight) + 1;
        return Math.max(Math.max(lMax, rMax), curNodeMax);
    }
}
