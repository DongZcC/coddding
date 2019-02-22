package com.dzc.learn.letcode;

public class PartitionList {

    public ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode min = null;
        if (head != null) {
            // 如果头结点就是比x大的值, 则需要重置x到dummy节点
            if (head.val >= x) {
                min = dummy;
                head = dummy;
            } else {
                while (head != null) {
                    if (head.next != null && head.next.val >= x) {
                        min = head;
                        break;
                    }
                    head = head.next;
                }
            }
        }
        // 如果整个链表中都比x 小，则直接返回
        if (min == null) {
            return dummy.next;
        }
        // 从 min开始向后执行
        while (head != null) {
            if (head.next != null && head.next.val < x) {
                ListNode minNext = min.next;
                min.next = head.next;
                head.next = head.next.next;
                min.next.next = minNext;
                min = min.next;
            } else
                head = head.next;
        }
        return dummy.next;
    }


    public ListNode partition2(ListNode head, int x) {
        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);
        ListNode smallHead = dummy1;
        ListNode bigHead = dummy2;
        while (head != null) {
            if (head.val < x) {
                smallHead.next = head;
                smallHead = smallHead.next;
            } else {
                bigHead.next = head;
                bigHead = bigHead.next;
            }
            head = head.next;
        }

        smallHead.next = dummy2.next;
        bigHead.next = null;
        return dummy1.next;
    }

    public static void main(String[] args) {
        // 1->4->3->2->5->2
        // [3,1,4,2,5,2]
        //  0 -> 3 -> 1 -> 4 -> 2 -> 5 -> 2
        ListNode head = new ListNode(1);
        ListNode head1 = new ListNode(4);
        ListNode head2 = new ListNode(3);
        ListNode head3 = new ListNode(2);
        ListNode head4 = new ListNode(5);
        ListNode head5 = new ListNode(2);

        head2.next = head;
        head.next = head1;
        head1.next = head3;
        head3.next = head4;
        head4.next = head5;

        PartitionList pl = new PartitionList();
        pl.partition(head2, 3);
    }

}
