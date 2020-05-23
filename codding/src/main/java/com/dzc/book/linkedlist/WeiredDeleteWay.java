package com.dzc.book.linkedlist;

/**
 * @author Administrator
 * @date 2020-05-23 16:02
 * <p>
 * 一种怪异的删除链表的方式， 不给 链表的头节点 只给一个node 如何在链表中把这个节点删除
 */
public class WeiredDeleteWay {


    // 1 - 2 - 3  //  删除 2
    // 直接把2节点变换为下一个节点的值，然后 删除下一个节点
    public void wiredDeleteWay(Node node) {
        if (node == null) {
            return;
        }
        Node next = node.next;
        if (next != null) {
            node.value = next.value;
            node.next = next.next;
        }
    }

    public static void main(String[] args) {
        WeiredDeleteWay weired = new WeiredDeleteWay();

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

        weired.wiredDeleteWay(head4);

        System.out.println(head);
    }

}
