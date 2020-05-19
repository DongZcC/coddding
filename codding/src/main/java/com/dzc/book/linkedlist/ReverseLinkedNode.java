package com.dzc.book.linkedlist;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Administrator
 * @date 2020-05-19 14:53
 * <p>
 * 反转单向链表 / 双向链表
 */
public class ReverseLinkedNode {


    public Node reverseList(Node head) {
        // node1 -> node2 -> node3 -> node4
        Node cur = head;
        Node preNode = null;

        while (cur != null) {
            Node next = cur.next;
            cur.next = preNode;
            preNode = cur;
            cur = next;
        }
        return preNode;
    }


    public DoubleNode reverseDoubleNodeList(DoubleNode head) {
        DoubleNode cur = head;
        DoubleNode preNode = null;
        while (cur != null) {
            DoubleNode next = cur.next;
            cur.next = preNode;
            cur.prev = next;
            preNode = cur;
            cur = next;
        }
        return preNode;
    }

    public static void main(String[] args) {
        ReverseLinkedNode reverseLinkedNode = new ReverseLinkedNode();

        Node head = new Node(5);
        Node head4 = new Node(4);
        Node head3 = new Node(3);
        Node head2 = new Node(2);
        Node head1 = new Node(1);

        // 5 -> 2 -> 3 -> 1 -> 4
        head.next = head2;
        head2.next = head3;
        head3.next = head1;
        head1.next = head4;

        Node node = reverseLinkedNode.reverseList(head);

        System.out.println(node);


        DoubleNode dh = new DoubleNode(5);
        DoubleNode dh2 = new DoubleNode(2);
        DoubleNode dh3 = new DoubleNode(3);
        DoubleNode dh1 = new DoubleNode(1);
        DoubleNode dh4 = new DoubleNode(4);

        dh.next = dh2;
        dh2.prev = dh;
        dh2.next = dh3;
        dh3.prev = dh2;
        dh3.next = dh1;
        dh1.prev = dh3;
        dh1.next = dh4;
        dh4.prev = dh1;

        DoubleNode dNode = reverseLinkedNode.reverseDoubleNodeList(dh);

        System.out.println(dNode);
    }
}
