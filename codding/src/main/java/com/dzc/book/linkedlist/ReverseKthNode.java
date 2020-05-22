package com.dzc.book.linkedlist;

import java.util.Stack;

/**
 * @author Administrator
 * @date 2020-05-22 16:28
 * <p>
 * <p>
 * 将单链表的每k个节点之间逆序
 * <p>
 * 这种逆序的问题，要好好想想 stack 结构， stack 结构有的时候可以很有用
 */
public class ReverseKthNode {

    public Node reverseKthNode(Node head, int k) {
        // 如果倒叙的节点少于2个;不用调整直接返回
        if (k <= 2) {
            return head;
        }

        // 1 - 2 - 3 - 4 - 5 - 6  | k = 3
        Stack<Node> stack = new Stack<>();
        Node pre = null;
        Node next = null;
        Node cur = head;
        Node newHead = head;

        while (cur != null) {
            next = cur.next;
            stack.push(cur);
            if (stack.size() == k) {
                pre = resign(stack, pre, next);
                newHead = newHead == head ? cur : newHead;
            }
            cur = next;
        }
        return newHead;
    }


    public Node reverseKthNode2(Node head, int k) {
        // 如果倒叙的节点少于2个;不用调整直接返回
        if (k <= 2) {
            return head;
        }

        Node cur = head;
        Node start = null;
        Node pre = null;
        Node next = null;
        int count = 1;
        while (cur != null) {
            next = cur.next;
            if (count == k) {
                start = pre == null ? head : pre.next;
                head = pre == null ? cur : head;
                resign2(pre, start, cur, next);
                pre = start;
                count = 0;
            }
            count++;
            cur = next;
        }
        return head;
    }

    private void resign2(Node left, Node start, Node end, Node right) {
        Node pre = start;
        Node cur = start.next;
        Node next = null;
        while (cur != right) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        if (left != null) {
            left.next = end;
        }
        start.next = right;
    }

    private Node resign(Stack<Node> stack, Node left, Node right) {
        Node cur = stack.pop();
        if (left != null) {
            // 3 -> 6
            left.next = cur;
        }
        while (!stack.isEmpty()) {
            Node next = stack.pop();
            cur.next = next;
            cur = next;
        }
        cur.next = right;
        return cur;
    }


    public static void main(String[] args) {
        ReverseKthNode reverse = new ReverseKthNode();

        Node head = new Node(5);
        Node head4 = new Node(4);
        Node head3 = new Node(3);
        Node head2 = new Node(2);
        Node head1 = new Node(1);

        // 5 -> 2 -> 3 -> 1 -> 4
        // 3 - 2 - 5 - 1 - 4
        head.next = head2;
        head2.next = head3;
        head3.next = head1;
        head1.next = head4;

        Node node = reverse.reverseKthNode(head, 3);

        System.out.println(node);

    }

}
