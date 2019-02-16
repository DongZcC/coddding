package com.dzc.learn.letcode;


public class RemoveDuplicatesfromSortedListII {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;

        while (head != null) {
            while (head.next != null && head.next.val == head.val) {
                head = head.next;
            }
            if (pre.next == head) {
                pre = pre.next;
            } else {
                pre.next = head.next;
            }
            head = head.next;
        }
        return dummy.next;
    }
}
