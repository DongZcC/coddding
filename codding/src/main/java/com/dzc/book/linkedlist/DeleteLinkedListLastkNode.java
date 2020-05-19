package com.dzc.book.linkedlist;

import java.util.LinkedList;

/**
 * @author Administrator
 * @date 2020-05-19 10:03
 * 删除单链表和双链表中倒数第k个节点
 */
public class DeleteLinkedListLastkNode {

    public Node removeLastKthNode(Node head, int k) {
        if (k == 0 || k < 0) {
            return head;
        }

        Node h = head;
        int total = 0;
        while (h != null) {
            total++;
            h = h.next;
        }

        // 链表总共有 5个节点. 删除倒数第4个.
        int removeIndex = total - k;
        if (removeIndex < 0) {
            return head;
        } else if (removeIndex == 0) {
            return head.next;
        } else {
            int index = 0;
            Node t = head;
            while (t != null) {
                index++;
                if (index == removeIndex) {
                    Node removeNode = t.next;
                    t.next = removeNode.next;
                    break;
                }
                t = t.next;
            }

            return head;
        }
    }


    public static void main(String[] args) {
        DeleteLinkedListLastkNode remove = new DeleteLinkedListLastkNode();
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

        Node node = remove.removeLastKthNode(head, 5);
        System.out.println(node);
    }
}

class Node {
    public int value;
    public Node next;

    public Node(int data) {
        this.value = data;
    }
}

class DoubleNode {
    public int value;
    public DoubleNode prev;
    public DoubleNode next;

    public DoubleNode(int data) {
        this.value = data;
    }
}
