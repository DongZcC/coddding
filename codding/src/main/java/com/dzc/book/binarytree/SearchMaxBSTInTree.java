package com.dzc.book.binarytree;

/**
 * @author Administrator
 * @date 2020-05-29 15:30
 * <p>
 * 找到二叉树中的最大搜索二叉子树
 * <p>
 * 已知其中所有节点的值都不一样， 找到含有节点最多的搜索二叉树
 */
public class SearchMaxBSTInTree {


    public Node getMaxBSTInTree(Node head) {
        if (head == null) {
            return null;
        }
        // record 记录 节点数， 最大值， 最小值，
        int[] record = new int[3];
        return postOrder(head, record);
    }

    private Node postOrder(Node h, int[] record) {
        if (h == null) {
            record[0] = 0;
            record[1] = Integer.MAX_VALUE;
            record[2] = Integer.MIN_VALUE;
            return null;
        }

        Node left = h.left;
        Node right = h.right;
        Node lBST = postOrder(left, record);
        int lSize = record[0];
        int lMin = record[1];
        int lMax = record[2];

        Node rBST = postOrder(right, record);
        int rSize = record[0];
        int rMin = record[1];
        int rMax = record[2];

        record[1] = Math.min(h.data, lMin);
        record[2] = Math.min(h.data, rMax);

        if (left == lBST && right == rBST && lMax < h.data && rMin > h.data) {
            record[0] = lSize + rSize + 1;
            return h;
        }
        record[0] = Math.max(lSize, rSize);
        return lSize > rSize ? lBST : rBST;
    }

}
