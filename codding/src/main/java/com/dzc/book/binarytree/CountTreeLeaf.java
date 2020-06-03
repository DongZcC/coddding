package com.dzc.book.binarytree;

/**
 * @author Administrator
 * @date 2020-06-03 14:53
 * <p>
 * 统计完全二叉树的节点数
 * <p>
 * 给定一颗完全二叉树的头节点head， 返回这棵树的节点个数
 * <p>
 * 完全二叉树， 从左边开始每个节点都要是满的
 */
public class CountTreeLeaf {


    /**
     * 每层都是满节点的，  就只有最后一层 不是满的；
     *
     * @param head
     * @return
     */
    public int countNode(Node head) {
        if (head == null) {
            return 0;
        }
        return bs(head, 1, mostLeftLevel(head, 1));
    }

    /**
     * 递归找
     *
     * @param node
     * @param l
     * @param h
     * @return
     */
    private int bs(Node node, int l, int h) {
        if (l == h) {
            return 1;
        }

        if (mostLeftLevel(node.right, l + 1) == h) {
            // 右子树的最左边节点的层数 和 h高度相同 ， 说明左边子树是满的
            return (1 << (h - 1)) + bs(node.right, l + 1, h);
        } else {
            // 右子树的最左节点不等于层高， 那么就代表 右面这几层已经满了
            return (1 << (h - l - 1)) + bs(node.left, l + 1, h);
        }
    }


    public int mostLeftLevel(Node node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }
}
