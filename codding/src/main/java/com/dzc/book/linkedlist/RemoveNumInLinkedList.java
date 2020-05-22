package com.dzc.book.linkedlist;

import java.util.Stack;

/**
 * @author Administrator
 * @date 2020-05-22 18:35
 * <p>
 * 在单链表中删除值位num的节点
 */
public class RemoveNumInLinkedList {

    public Node removeNumNode(Node head, int num) {
        if (head == null) {
            return head;
        }

        Node cur = head;
        Node pre = null;

        Node newHead = head;
        while (cur != null) {
            if (cur.value != num) {
                newHead = cur;
                break;
            }
            cur = cur.next;
        }
        cur = newHead.next;
        pre = newHead;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
                pre = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return newHead;
    }


    /**
     *  用一个栈把不等于节点的数全部存起来
     *
     * @param head
     * @param num
     * @return
     */
    public Node removeNumNode2(Node head, int num) {
        if (head == null) {
            return head;
        }

        Stack<Node> stack = new Stack<>();
        Node cur = head;
        Node next;
        while (cur != null) {
            next = cur.next;
            if (cur.value != num) {
                cur.next = null;
                stack.push(cur);
            }
            cur = next;
        }

        // 1 -> 2 -> 3
        // 3  2
        Node pre = null;
        // 有更好的解法
/*        while (!stack.isEmpty()) {
            cur = stack.pop();
            if (pre != null) {
                cur.next = pre;
                pre = cur;
            } else {
                pre = cur;
            }
        }*/
        while (!stack.isEmpty()) {
            stack.peek().next = cur;
            cur = stack.pop();
        }
        return cur;
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
        head4.next = new Node(5);

        RemoveNumInLinkedList removeNumInLinkedList = new RemoveNumInLinkedList();
        Node node = removeNumInLinkedList.removeNumNode2(head, 5);

        System.out.println(node);

    }
}
