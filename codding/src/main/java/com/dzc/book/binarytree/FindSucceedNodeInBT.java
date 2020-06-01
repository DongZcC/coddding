package com.dzc.book.binarytree;

/**
 * @author Administrator
 * @date 2020-06-01 14:44
 * <p>
 * 在二叉树中找到一个节点的后继节点
 */
public class FindSucceedNodeInBT {

    static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }


    /**
     * 找到给定节点的后继节点
     *
     * @param node if node == node.parent.left
     * @return
     */
    public Node findSucceedNode(Node node) {
        if (node == null) {
            return null;
        }
        // 节点有右孩子
        if (node.right != null) {
            Node cur = node.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur;
        }

        // 如果node是左节点
        if (node.parent.left == node) {
            return node.parent;
        } else {
            return findSucceedNode(node.parent);
        }
    }


}
