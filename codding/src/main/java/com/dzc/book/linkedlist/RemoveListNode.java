package com.dzc.book.linkedlist;

/**
 * @author Administrator
 * @date 2020-05-19 11:06
 * <p>
 * 给定链表的头节点head , 实现删除链表中间节点的函数
 */
public class RemoveListNode {

    public Node removeMidNode(Node head) {
        Node h = head;
        int count = 0;
        while (h != null) {
            count++;
            h = h.next;
        }

        int mid = count / 2;

        if (mid > 0) {
            Node t = head;
            int index = 0;
            while (t != null) {
                index++;
                if (index == mid) {
                    Node removeNode = t.next;
                    t.next = removeNode.next;
                }
                t = t.next;
            }
            return head;
        }
        return null;
    }

    public static void main(String[] args) {

        RemoveListNode remove = new RemoveListNode();
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

        Node node = remove.removeMidNode(head);

        System.out.println(node);
    }
}
