package com.dzc.book.linkedlist;

/**
 * @author Administrator
 * @date 2020-05-19 16:15
 * <p>
 * 反转部分单项链表
 * 给定两个整数 from 和 to 把单向链表上把 from 个节点到 to 个节点这部分反转
 */
public class ReversePartLinkedList {

    public Node reversePart(Node head, int from, int to) {
        if (from > to) {
            return head;
        }

        // 5 -> 2 -> 3 -> 1 -> 4   1 5
        // 5 -> 2 -> 3 -> 1 -> 4   2 3    5 -> 2 <- 3 -> 1 -> 4
        Node cur = head;
        Node prevNode = null;
        int index = 0;

        Node preReverseNode = null;
        Node lastReverseNode = null;

        while (cur != null) {
            index++;
            if (index + 1 == from) {
                preReverseNode = cur;
            } else if (index - 1 == to) {
                lastReverseNode = cur;
            }
            cur = cur.next;
        }


        cur = preReverseNode == null ? head : preReverseNode.next;
        Node node2 = cur.next;
        cur.next = lastReverseNode;
        Node next = null;

        while (node2 != lastReverseNode) {
            next = node2.next;
            node2.next = cur;
            cur = node2;
            node2 = next;
        }

        if (preReverseNode != null) {
            preReverseNode.next = cur;
            return head;
        }
        return cur;
    }


    public static void main(String[] args) {
        ReversePartLinkedList reverse = new ReversePartLinkedList();

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


        Node node = reverse.reversePart(head, 1, 4);

        System.out.println(node);

    }
}
