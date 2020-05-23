package com.dzc.book.linkedlist;

/**
 * @author Administrator
 * @date 2020-05-23 14:52
 * <p>
 * 实现单个链表的选择排序
 */
public class LinkedListSelectionSort {

    public Node selectionSort(Node head) {
        if (head == null) {
            return null;
        }
        // 排序部分的尾巴
        Node tail = null;
        Node cur = head;
        // 最小节点的前一个节点
        Node smallPre = null;
        // 最小值节点
        //  3 2 1 5 6 8
        // 3 2 5 6 8
        // 1

        // ---

        // 3 5 6 8
        // 1 - 2

        // 5  6 8
        // 1 2 3

        Node small = null;

        while (cur != null) {
            small = cur;
            Node smallPreNode = getSmallPreNode(cur);
            if (smallPreNode != null) {
                small = smallPreNode.next;
                // 删除small节点
                smallPreNode.next = small.next;
            }

            cur = cur == small ? cur.next : cur;
            if (tail == null) {
                head = small;
            } else {
                tail.next = small;
            }
            tail = small;
        }

        return head;
    }

    private Node getSmallPreNode(Node head) {
        Node small = head;
        Node smallPre = null;
        Node pre = head;
        Node cur = head.next;
        while (cur != null) {
            if (cur.value < small.value) {
                small = cur;
                smallPre = pre;
            }
            pre = cur;
            cur = cur.next;
        }
        return smallPre;
    }

    public static void main(String[] args) {
        LinkedListSelectionSort linked = new LinkedListSelectionSort();

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


        Node node = linked.selectionSort(head);

        System.out.println(node);

    }
}
