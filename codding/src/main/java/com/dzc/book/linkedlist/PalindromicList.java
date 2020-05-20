package com.dzc.book.linkedlist;

import java.util.Stack;

/**
 * @author Administrator
 * @date 2020-05-20 17:29
 * <p>
 * <p>
 * 判断链表是否回文 结构
 */
public class PalindromicList {

    public boolean isPalindromic(Node head) {
        Stack<Integer> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur.value);
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            if (cur.value != stack.pop()) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }


    public boolean isPalindromic2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        Node right = head.next;
        Node cur = head;
        // cur 走两步 , right 走一步
        // right 正好走到中间的时候 cur 走完了
        while (cur.next != null && cur.next.next != null) {
            right = right.next;
            cur = cur.next.next;
        }

        Stack<Integer> stack = new Stack<>();

        while (right != null) {
            stack.push(right.value);
            right = right.next;
        }

        while (stack.isEmpty()) {
            if (head.value != stack.pop()) {
                return false;
            }
            head = head.next;
        }

        // 先走到中间的位置.
        return true;
    }


    public boolean isPalindromic3(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }
        // n1 是中间的节点
        n2 = n1.next;
        // mid.next  = null
        n1.next = null;

        Node n3 = null;
        // 右半边反转
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }

        n3 = n1; // n3  保存最后一个节点
        n2 = head; // n2 左边第一个节点

        boolean res = true;
        while (n1 != null && n2 != null) {
            if (n1.value != n2.value) {
                res = false;
                break;
            }

            n1 = n1.next;
            n2 = n2.next;
        }

        n1 = n3.next;
        n3.next = null;
        // 恢复列表
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }


    public static void main(String[] args) {
        PalindromicList p = new PalindromicList();

        Node head = new Node(5);
        Node head4 = new Node(4);
        Node head3 = new Node(3);
        Node head2 = new Node(2);
        Node head1 = new Node(1);

        // 5 -> 2 -> 3 -> 1 -> 4
        head.next = head2;
        head2.next = head3;
        Node h2 = new Node(2);
        head3.next = h2;
        h2.next = new Node(5);
        System.out.println(p.isPalindromic(head));
    }
}
