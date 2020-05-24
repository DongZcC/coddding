package com.dzc.book.linkedlist;

/**
 * Created on 2020/5/24.
 *
 * @author _Shrimp
 * <p>
 * 重新构建链表， 把链表分成左右两半 ；重新构建链表
 */
public class RebuildLinkedInTwoPartWayList {


    public static void main(String[] args) {
        RebuildLinkedInTwoPartWayList rebuild = new RebuildLinkedInTwoPartWayList();


        Node head0 = new Node(0);
        Node head2 = new Node(2);
        Node head3 = new Node(3);
        Node head7 = new Node(7);
        Node head9 = new Node(9);
        Node head10 = new Node(10);


        head0.next = head2;
        head2.next = head3;
        head3.next = head7;
        head7.next = head9;
        head9.next = head10;


        Node node = rebuild.rebuild(head0);

        System.out.println(node);

    }

    public Node rebuild(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        // 1 - 2 - 3 - 4 - 5 - 6
        Node fast = head.next;
        Node slow = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node mid = slow.next;
        slow = head;


        while (mid != null) {
            Node slowNext = slow.next;
            Node midNext = mid.next;
            slow.next = mid;
            mid.next = slowNext;

            slow = slowNext;
            mid = midNext;
        }

        return head;
    }
}
