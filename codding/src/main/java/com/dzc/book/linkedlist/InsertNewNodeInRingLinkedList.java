package com.dzc.book.linkedlist;

/**
 * Created on 2020/5/24.
 *
 * @author _Shrimp
 * <p>
 * 向有序的环形链表中插入新节点.
 */
public class InsertNewNodeInRingLinkedList {


    public static void main(String[] args) {
        InsertNewNodeInRingLinkedList insert = new InsertNewNodeInRingLinkedList();


        Node head = new Node(5);
        Node head3 = new Node(3);
        Node head1 = new Node(1);


        head1.next = head3;
        head3.next = head;
        head.next = head1;

        Node node = insert.insertNewNode(head1, 6);

        System.out.println(node);

    }

    public Node insertNewNode(Node head, int num) {
        if (head == null) {
            return new Node(num);
        }

        // node 不为空.
        // 在有序的环形链表中找到插入位置.
        Node pre = head;
        Node cur = head.next;
        Node newNode = new Node(num);


        // 1 - 2 - 4
        while (cur != head) {
            if (pre.value <= num && cur.value >= num) {
                break;
            }
            pre = cur;
            cur = cur.next;
        }

        // 走到这里的情况 要不就是找到了合适的位置
        // 要不就是插头尾节点.

        pre.next = newNode;
        newNode.next = cur;

        return head.value < num ? head : newNode;
    }
}
