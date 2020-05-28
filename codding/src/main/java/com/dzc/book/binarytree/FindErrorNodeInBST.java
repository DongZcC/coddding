package com.dzc.book.binarytree;

import java.util.Stack;

/**
 * @author Administrator
 * @date 2020-05-28 18:39
 * 在二叉搜索树中找到一个
 */
public class FindErrorNodeInBST {

    public Node[] getTwoErrorNode(Node head) {
        Node[] errors = new Node[2];
        if (head == null) {
            return errors;
        }

        Stack<Node> stack = new Stack<>();
        Node pre = null;
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (pre != null && pre.data > head.data) {
                    errors[0] = errors[0] == null ? pre : errors[0];
                    errors[1] = head;
                }
                pre = head;
                head = head.right;
            }
        }
        return errors;
    }
}
