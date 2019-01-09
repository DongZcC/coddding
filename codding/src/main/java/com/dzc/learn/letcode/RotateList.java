package com.dzc.learn.letcode;


import org.junit.Test;

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

/**
 * Input: dummy -> 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL
 * rotate 2 steps to the right: 4->5->1->2->3->NULL
 */
public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        if (head == null || head.next == null)
            return head;
        int len = 0;
        while (head.next != null) {
            head = head.next;
            len++;
        }
        len++;
        k %= len;
        head = dummy.next;
        while (k > 0) {
            while (head.next.next != null) {
                head = head.next;
            }
            head.next.next = dummy.next;
            dummy.next = head.next;
            head.next = null;
            head = dummy.next;
            k--;
        }
        return dummy.next;
    }


    public ListNode rotateRight2(ListNode head, int k) {
        if (head == null)
            return null;

        ListNode tail = head;
        int len = 1;
        while (tail.next != null) {
            tail = tail.next;
            len ++;
        }
        k %= len;
        tail.next = head;  // cycle

        for (int i = 0; i < len - k; i++) {
            tail = tail.next;
        }

        ListNode newHead = tail.next;
        tail.next = null;
        return newHead;
    }

    @Test
    public void testRotate() {
        ListNode head = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        ListNode head5 = new ListNode(5);

        head.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;

        RotateList rotateList = new RotateList();
        rotateList.rotateRight(head, 2);

    }

}
