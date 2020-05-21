package com.dzc.book.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @date 2020-05-21 10:59
 * <p>
 * 复制含有随机指针节点的链表
 */
public class CopyRandLinkedList {

    /**
     * 构造一个map 去存好所有的关联关系
     *
     * @param head
     * @return
     */
    public Node copyRandLinkedList(Node head) {
        // copy 一下整个节点.
        Node cur = head;
        Map<Node, Node> linkMap = new HashMap<>();
        while (cur != null) {
            linkMap.put(cur, new Node(cur.value));
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            linkMap.get(cur).next = linkMap.get(cur.next);
            linkMap.get(cur).rand = linkMap.get(cur.rand);
            cur = cur.next;
        }
        return linkMap.get(head);
    }


    /**
     * 进阶，不使用额外的数据结构， 只用有限的几个变量， 时间复杂度 O(N)
     *
     * @param head
     * @return
     */
    public Node copyRandLinkedList2(Node head) {
        Node cur = head;

        // 1->2->3->null
        while (cur != null) {
            Node next = cur.next;
            Node cur2 = new Node(cur.value);
            cur.next = cur2;
            cur2.next = next;
            cur = next;
        }

        // 设置rand指针
        cur = head;
        while (cur != null) {
            cur.next.rand = cur.rand == null ? null : cur.rand.next;
            cur = cur.next.next;
        }

        // 分离.

        // 1->1'->2->2'->null
        Node newHead = head.next;
        cur = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next = cur.next.next;
            next.next = next.next.next;
            cur = cur.next.next;
        }

        return newHead;
    }


    static class Node {

        public int value;

        public Node next;

        public Node rand;

        public Node(int value) {
            this.value = value;
        }
    }


    public static void main(String[] args) {
        Node h1 = new Node(1);
        Node h2 = new Node(2);
        Node h3 = new Node(3);

        h1.next = h2;
        h2.next = h3;
        h1.rand = h3;
        h3.rand = h1;

        CopyRandLinkedList copy = new CopyRandLinkedList();
        Node node = copy.copyRandLinkedList2(h1);
        System.out.println(node);

    }
}


