package com.dzc.book.linkedlist;

/**
 * @author Administrator
 * @date 2020-05-20 16:58
 * <p>
 * 约瑟夫环问题
 */
public class JosephusRing {

    public Node josephusKill(Node head, int num) {
        if (num <= 0 || head == null || head.next == head) {
            return head;
        }

//        Node cur = head;
//        int index = 0;
//        Node pre = null;
//        while (cur != null) {
//            index++;
//            Node next = cur.next;
//            if (index == num) {
//                // 自杀.
//                if (pre == null) {
//                    return null;
//                } else
//                    pre.next = cur.next;
//
//                index = 0;
//            }
//            if (cur.value == next.value) {
//                cur.next = null;
//                return cur;
//            }
//            pre = cur;
//            cur = next;
//        }
//
//        return head;

        // 找到最后一个节点.
        Node last = head;
        while (last.next != head) {
            last = last.next;
        }

        int count = 0;
        while (head != last) {
            if (++count == num) {
                last.next = head.next;
                count = 0;
            } else {
                last = last.next;
            }
            head = last.next;
        }
        return head;
    }

    public static void main(String[] args) {
        JosephusRing ring = new JosephusRing();


        Node head = new Node(5);
        Node head4 = new Node(4);
        Node head3 = new Node(3);
        Node head2 = new Node(2);
        Node head1 = new Node(1);

        // 5 -> 2 -> 3 -> 1 -> 4
        // 5 -> 3 -> 1 -> 4
        // 5 -> 3 -> 4
        head.next = head2;
        head2.next = head3;
        head3.next = head1;
        head1.next = head4;
        // ring
        head4.next = head;
        Node node = ring.josephusKill(head, 3);

        System.out.println(node);
    }
}
