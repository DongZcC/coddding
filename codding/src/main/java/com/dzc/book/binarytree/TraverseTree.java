package com.dzc.book.binarytree;

import java.util.Stack;

/**
 * @author Administrator
 * @date 2020-05-25 14:20
 * <p>
 * 递归的方式 和 非递归的方式 实现遍历
 */
public class TraverseTree {

    public void preOrder(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.data);
        preOrder(head.left);
        preOrder(head.right);
    }

    public void preOrder2(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.data);

            if (head.right != null) {
                stack.push(head.right);
            }

            if (head.left != null) {
                stack.push(head.left);
            }
        }

    }


    public void inOrder(Node head) {
        if (head == null) {
            return;
        }
        preOrder(head.left);
        System.out.println(head.data);
        preOrder(head.right);
    }

    public void inOrder2(Node head) {
        if (head == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                System.out.println(head.data);
                head = head.right;
            }
        }
    }


    public void postOrder(Node head) {
        if (head == null) {
            return;
        }
        preOrder(head.left);
        preOrder(head.right);
        System.out.println(head.data);
    }

    public void postOrder2(Node head) {
        if (head == null) {
            return;
        }

        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();

        s1.push(head);

        while (!s1.empty()) {
            Node cur = s1.pop();
            s2.push(cur);

            if (cur.left != null) {
                s1.push(cur);
            }

            if (cur.right != null) {
                s1.push(cur.right);
            }
        }


        while (!s2.isEmpty()) {
            System.out.println(s2.pop().data);
        }
    }
}
