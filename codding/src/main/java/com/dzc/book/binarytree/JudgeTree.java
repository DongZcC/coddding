package com.dzc.book.binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created on 2020/5/31.
 *
 * @author _Shrimp
 * <p>
 * 判断一颗树是否为搜索二叉树和完全二叉树
 */
public class JudgeTree {

    public boolean isBST(Node head) {
        // 判断一颗树是否为二叉搜索树
        // 写个先序遍历看看是否递增， 不递增直接返回false

        Stack<Node> stack = new Stack<>();
        Node prev = null;
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (prev != null && prev.data > head.data) {
                    return false;
                }
                prev = head;
                head = head.right;
            }
        }

        return true;
    }


    /**
     * 判断树是否为完全二叉树
     * <p>
     * 每个节点都包含了左孩子，
     *
     * @param head
     * @return
     */
    public boolean isFullBinaryTree(Node head) {
        if (head == null) {
            return false;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(head);

        Node l = null;
        Node r = null;
        boolean leaf = false;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            l = cur.left;
            r = cur.right;

            if (leaf && (l != null || r != null) || (l == null && r != null)) {
                return false;
            }

            if (l != null) {
                queue.offer(l);
            }

            if (r != null) {
                queue.offer(r);
            } else {
                leaf = true;
            }
        }
        return true;
    }
}
