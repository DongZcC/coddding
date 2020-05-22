package com.dzc.book.linkedlist;

import java.util.HashSet;

/**
 * @author Administrator
 * @date 2020-05-22 17:22
 * <p>
 * 删除无序单链表中值重复出现的节点
 */
public class RemoveLinkedListRepNode {

    public Node removeRepeNode(Node head) {
        if (head == null) {
            return head;
        }
        HashSet<Integer> value = new HashSet<>();
        Node cur = head;
        Node pre = null;
        Node next = null;

        // 1 - 2 - 1 -> 3 -> 4
        while (cur != null) {
            next = cur.next;
            if (value.contains(cur.value)) {
                pre.next = next;
                pre = next;
                cur = next;
                continue;
            }
            value.add(cur.value);
            pre = cur;
            cur = next;
        }
        return head;
    }



    public Node removeRepeNode2(Node head) {
        if (head == null) {
            return head;
        }
        HashSet<Integer> value = new HashSet<>();
        Node cur = head.next;
        Node pre = head;
        while (cur != null) {
            if (value.contains(cur.value)) {
                pre = cur.next;
            } else {
                value.add(cur.value);
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
    public static void main(String[] args) {
        RemoveLinkedListRepNode remove = new RemoveLinkedListRepNode();

        Node head = new Node(5);
        Node head4 = new Node(4);
        Node head3 = new Node(3);
        Node head2 = new Node(2);
        Node head1 = new Node(1);

        // 5 -> 2 -> 3 -> 1 -> 4
        head.next = head2;
        head2.next = head3;
        head3.next = new Node(2);
        head3.next = head1;
        head1.next = new Node(5);
        head1.next = head4;

        Node node = remove.removeRepeNode2(head);

        System.out.println(node);

    }

}
