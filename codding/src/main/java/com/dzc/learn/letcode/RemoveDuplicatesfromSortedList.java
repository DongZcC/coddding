package com.dzc.learn.letcode;

public class RemoveDuplicatesfromSortedList {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        while (head != null) {
            while (head.next != null && head.next.val == head.val) {
                head = head.next;
            }
            pre.next = head;
            pre = pre.next;
            head = head.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(1);
        ListNode a3 = new ListNode(1);
        ListNode a4 = new ListNode(1);

        head.next = a1;
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;

        RemoveDuplicatesfromSortedList r = new RemoveDuplicatesfromSortedList();
        r.deleteDuplicates(head);
    }
}
