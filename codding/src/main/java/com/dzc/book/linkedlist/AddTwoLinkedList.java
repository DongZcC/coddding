package com.dzc.book.linkedlist;

import java.util.Stack;

/**
 * @author Administrator
 * @date 2020-05-21 14:08
 * <p>
 * 两个单链表生成相加链表
 */
public class AddTwoLinkedList {

    public Node addTwoLinkedList1(Node head1, Node head2) {
        // 直接搞到两个整数
        Node cur = head1;
        Stack<Integer> stack1 = new Stack<>();
        while (cur != null) {
            stack1.push(cur.value);
            cur = cur.next;
        }

        cur = head2;
        Stack<Integer> stack2 = new Stack<>();
        while (cur != null) {
            stack2.push(cur.value);
            cur = cur.next;
        }

        // 标识是否有进位
        boolean carry = false;
        Node lastNode = null;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            int total = stack1.pop() + stack2.pop();
            if (carry) {
                total++;
                carry = false;
            }
            if (total >= 10) {
                carry = true;
                total -= 10;
            }

            Node newNode = new Node(total);
            if (lastNode == null) {
                lastNode = newNode;
            } else {
                newNode.next = lastNode;
                lastNode = newNode;
            }
        }

        while (!stack1.isEmpty()) {
            int val = stack1.pop();
            if (carry) {
                val++;
                carry = false;
            }

            if (val >= 10) {
                val -= 10;
                carry = true;
            }

            Node newNode = new Node(val);
            if (lastNode == null) {
                lastNode = newNode;
            } else {
                newNode.next = lastNode;
                lastNode = newNode;
            }
        }

        while (!stack2.isEmpty()) {
            int val = stack2.pop();
            if (carry) {
                val++;
                carry = false;
            }

            if (val >= 10) {
                val -= 10;
                carry = true;
            }

            Node newNode = new Node(val);
            if (lastNode == null) {
                lastNode = newNode;
            } else {
                newNode.next = lastNode;
                lastNode = newNode;
            }
        }

        if (carry) {
            Node newNode = new Node(1);
            newNode.next = lastNode;
            lastNode = newNode;
        }

        return lastNode;
    }


    public Node addTwoLinkedList2(Node head1, Node head2) {
        head1 = reverseList(head1);
        head2 = reverseList(head2);

        return null;
    }

    private Node reverseList(Node head1) {
        return null;
    }

    public static void main(String[] args) {
        AddTwoLinkedList add = new AddTwoLinkedList();

        Node h1 = new Node(9);
        Node h2 = new Node(7);
        Node h3 = new Node(6);

        h1.next = h2;
        h2.next = h3;


        Node l1 = new Node(5);
        Node l2 = new Node(6);
        l1.next = l2;
        Node node = add.addTwoLinkedList1(h1, l1);
        System.out.println(node);
    }
}
