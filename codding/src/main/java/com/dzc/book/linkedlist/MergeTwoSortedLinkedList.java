package com.dzc.book.linkedlist;

/**
 * Created on 2020/5/24.
 *
 * @author _Shrimp
 * <p>
 * 合并两个有序的链表， 并返回新的链表头
 * <p>
 * 返回的链表仍然有序
 */
public class MergeTwoSortedLinkedList {


    public static void main(String[] args) {
        MergeTwoSortedLinkedList merge = new MergeTwoSortedLinkedList();


        Node head0 = new Node(0);
        Node head2 = new Node(2);
        Node head3 = new Node(3);
        Node head7 = new Node(7);


        head0.next = head2;
        head2.next = head3;
        head3.next = head7;

        Node head1 = new Node(1);
        Node head33 = new Node(3);
        Node head5 = new Node(5);
        Node head77 = new Node(7);
        Node head9 = new Node(9);

        head1.next = head33;
        head33.next = head5;
        head5.next = head77;
        head77.next = head9;

        Node node = merge.mergeTwoLinkedList(head0, head1);

        System.out.println(node);

    }

    public Node mergeTwoLinkedList(Node head1, Node head2) {

        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }
        // 定头节点
        Node head = head1.value < head2.value ? head1 : head2;

        Node cur1 = head == head1 ? head1 : head2;
        Node cur2 = head == head1 ? head2 : head1;

        Node pre = null;
        Node next = null;

        // 0 - 2 - 3 - 7
        // 1 - 3 - 5 - 7 - 9
        while (cur1 != null && cur2 != null) {
            if (cur1.value < cur2.value) {
                pre = cur1;
                cur1 = cur1.next;
            } else {
                next = cur2.next;
                pre.next = cur2;
                cur2.next = cur1;
                pre = cur2;
                cur2 = next;
            }
        }

        pre.next = cur1 == null ? cur2 : cur1;
        return head;
    }

}
