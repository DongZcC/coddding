package com.dzc.book.binarytree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Administrator
 * @date 2020-05-28 16:14
 * <p>
 * 二叉树的按层打印与ZigZag打印
 */
public class PrintBT {

    /**
     * 二叉树 按层打印
     *
     * @param head
     */
    public void printLevel(Node head) {
        if (head == null) {
            return;
        }
        // 按层打印，  这个用队列来搞
        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);


        int level = 1;
        Node last = head;
        Node nLast = null;
        System.out.print("Level" + (level++) + ":");
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.print(cur.data + " ");

            if (cur.left != null) {
                queue.offer(cur.left);
                nLast = cur.left;
            }

            if (cur.right != null) {
                queue.offer(cur.right);
                nLast = cur.right;
            }

            if (cur == last && !queue.isEmpty()) {
                System.out.print("\nLevel" + (level++) + ":");
                last = nLast;
            }
        }
    }


    /**
     * ZigZag 打印二叉树
     *
     * @param head
     */
    public void printZigZag(Node head) {
        if (head == null) {
            return;
        }
        // 按层打印，  这个用队列来搞
        Deque<Node> dq = new LinkedList<>();
        dq.offerFirst(head);


        int level = 1;
        Node last = head;
        Node nLast = null;
        boolean lr = true;
        System.out.print("Level " + (level++) + " from left to right :");
        while (!dq.isEmpty()) {
            Node cur;
            if (lr) {
                cur = dq.pollFirst();
                System.out.print(cur.data + " ");

                if (cur.left != null) {
                    nLast = nLast == null ? cur.left : nLast;
                    dq.offerLast(cur.left);
                }

                if (cur.right != null) {
                    nLast = nLast == null ? cur.right : nLast;
                    dq.offerLast(cur.right);
                }
            } else {
                cur = dq.pollLast();
                System.out.print(cur.data + " ");
                if (cur.right != null) {
                    nLast = nLast == null ? cur.right : nLast;
                    dq.offerFirst(cur.right);
                }

                if (cur.left != null) {
                    nLast = nLast == null ? cur.left : nLast;
                    dq.offerFirst(cur.left);
                }
            }

            if (last == cur && !dq.isEmpty()) {
                System.out.print("\nLevel " + level + (lr ? " from right to left " : " from left to right ") + ":");
                lr = !lr;
                last = nLast;
                nLast = null;
            }
//            Node cur;
//            if (level % 2 == 0) {
//                cur = queue.pollLast();
//            } else {
//                cur = queue.pollFirst();
//            }
//            System.out.print(cur.data + " ");
//
//            if (cur.left != null) {
//                queue.offer(cur.left);
//                nLast = cur.left;
//            }
//
//            if (cur.right != null) {
//                queue.offer(cur.right);
//                nLast = cur.right;
//            }
//
//            if (cur == last && !queue.isEmpty()) {
//                System.out.print("\nLevel " + level +  ((level % 2 == 0) ? " from right to left " : " from left to right ") + ":");
//                level++;
//                last = nLast;
//            }
        }
    }

    public static void main(String[] args) {
        PrintBT print = new PrintBT();


        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        node4.left = node2;
        node4.right = node6;
        node2.left = node1;
        node2.right = node3;
        node6.left = node5;
        node6.right = node7;
        print.printZigZag(node4);
    }
}
