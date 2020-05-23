package com.dzc.book.linkedlist;

import java.util.LinkedList;

/**
 * @author Administrator
 * @date 2020-05-23 13:43
 * <p>
 * 转换二叉搜索树为双向链表
 */
public class ConvertBstToLinkedList {


    static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }


    static class DoubleNode {
        public int value;
        public DoubleNode pre;
        public DoubleNode next;

        public DoubleNode(int data) {
            this.value = data;
        }
    }


    /**
     * 把原本的搜索二叉树转换成一个有序的双向链表
     *
     * @param root
     * @return
     */
    public DoubleNode convertBSTtoLinkedList(Node root) {
        if (root == null) {
            return null;
        }
        // 中序遍历一颗二叉树， 把遍历的节点放到一个队列中.
        // 顺序出队列，链接成一个双向链表（有序）
        LinkedList<DoubleNode> queue = new LinkedList<>();
        preOrder(root, queue);

        // 队列中的头节点现出来.
        DoubleNode head = queue.pollFirst();
        DoubleNode cur = head;
        while (!queue.isEmpty()) {
            queue.peekFirst().pre = cur;
            cur.next = queue.peekFirst();
            cur = queue.pollFirst();
        }
        return head;
    }


    public Node convertBSTtoLinkedList2(Node head) {
        if (head == null) {
            return null;
        }

        Node last = process(head);
        head = last.right;
        last.right = null;
        return head;
    }

    /**
     * 递归，返回一个双向链表
     *
     * @param node
     * @return
     */
    private Node process(Node node) {
        if (node == null) {
            return null;
        }
        Node leftE = process(node.left); // 左边结束
        Node rightE = process(node.right); // 右边结束
        Node leftS = leftE == null ? null : leftE.right;
        Node rightS = rightE == null ? null : rightE.right;

        if (leftE != null && rightE != null) {
            leftE.right = node;
            node.left = leftE;
            rightE.left = node;
            node.right = rightS;
            rightS.left = node;
            rightE.right = leftS;
            return rightE;
        } else if (leftE != null) {
            leftE.right = node;
            node.left = leftE;
            node.right = leftS;
            return node;
        } else if (rightE != null) {
            node.right = rightS;
            rightS.left = node;
            rightE.right = node;
            return rightE;
        } else {
            node.right = node;
            return node;
        }
    }


    private void preOrder(Node root, LinkedList<DoubleNode> queue) {
        if (root == null) {
            return;
        }
        preOrder(root.left, queue);
        queue.add(new DoubleNode(root.value));
        preOrder(root.right, queue);
    }


    public static void main(String[] args) {
        ConvertBstToLinkedList convert = new ConvertBstToLinkedList();

        Node root = new Node(6);
        Node node4 = new Node(4);
        Node node7 = new Node(7);
        Node node2 = new Node(2);
        Node node5 = new Node(5);
        Node node1 = new Node(1);
        Node node3 = new Node(3);

        root.left = node4;
        root.right = node7;
        node4.left = node2;
        node4.right = node5;
        node2.left = node1;
        node2.right = node3;

        convert.convertBSTtoLinkedList2(root);
    }
}
