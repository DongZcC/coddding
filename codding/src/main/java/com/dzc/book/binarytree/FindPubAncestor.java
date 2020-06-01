package com.dzc.book.binarytree;

/**
 * @author Administrator
 * @date 2020-06-01 15:10
 * <p>
 * 在二叉树中找到两个节点的最近公共祖先
 */
public class FindPubAncestor {


    /**
     * 后续遍历
     *
     * @param head
     * @param o1
     * @param o2
     * @return
     */
    public Node findPubAncestor(Node head, Node o1, Node o2) {
        if (head == null || head == o1 || head == o2) {
            return head;
        }

        Node left = findPubAncestor(head.left, o1, o2);
        Node right = findPubAncestor(head.right, o1, o2);

        // 左边子树和右边子树 都各自找到一个
        if (left != null && right != null) {
            return head;
        }

        return left != null ? left : right;
    }

}
