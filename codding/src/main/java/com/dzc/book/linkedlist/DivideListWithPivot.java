package com.dzc.book.linkedlist;

/**
 * @author Administrator
 * @date 2020-05-20 19:42
 * <p>
 * <p>
 * 根据一个给定的值把链表分为三个部分
 * <p>
 * 左边是小于值的， 中间是等于这个值的 ， 右边是大于这个值的
 */
public class DivideListWithPivot {

    public Node listPartition(Node head, int pivot) {
        int count = 0;
        Node cur = head;
        while (cur != null) {
            count++;
            cur = cur.next;
        }

        Node[] nodes = new Node[count];

        cur = head;
        count = 0;

        while (cur != null) {
            nodes[count++] = cur;
            cur = cur.next;
        }

        // 进行排序
        arrPartition(nodes, pivot);

        for (int i = 1; i != nodes.length; i++) {
            nodes[i - 1].next = nodes[i];
        }

        return nodes[0];
    }

    private void arrPartition(Node[] nodes, int pivot) {
        int small = -1;
        int big = nodes.length;
        int index = 0;
        while (index != big) {
            if (nodes[index].value < pivot) {
                swap(nodes, ++small, index++);
            } else if (nodes[index].value == pivot) {
                index++;
            } else {
                swap(nodes, --big, index);
            }
        }
    }

    private void swap(Node[] nodes, int a, int b) {
        Node tmp = nodes[a];
        nodes[a] = nodes[b];
        nodes[b] = tmp;
    }


    public static Node listPartition2(Node head, int pivot) {
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node bH = null;
        Node bT = null;

        Node next = null;
        // 所有的节点分进三个链表中
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.value == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if (bH == null) {
                    bH = head;
                    bT = head;
                } else {
                    bT.next = head;
                    bT = head;
                }
            }
            head = next;
        }

        if (sT != null) {
            sT.next = eH;
            eT = eT == null ? sT : eT;
        }

        if (eT != null) {
            eT.next = bH;
        }

        return sH != null ? sH : eH != null ? eH : bH;
    }


    public static void main(String[] args) {


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


        DivideListWithPivot pivot = new DivideListWithPivot();
        pivot.listPartition(head, 3);

    }

}
