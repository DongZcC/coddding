package com.dzc.book.linkedlist;

/**
 * @author Administrator
 * @date 2020-05-21 16:00
 * <p>
 * 两个链表相交的一系列问题
 * <p>
 * 这个问题非常复杂可以分为以下三个问题:
 * <p>
 * 一、 如何判断一个链表是否有环， 如果有， 则返回第一个进入环的节点， 没有则返回null
 * 1、 直接用hashmap来搞，一直循环，如果放进去的时候 map中已经存在了key 那就说明有环
 * 2、 用两个指针， 一个快 一个慢
 * 二、 如何判断两个无环链表是否相交， 相交则返回第一个相交节点， 没有则返回null
 * 1、 先循环两个链表， 因为两个五环链表如果相交， 尾节点一定是一样的
 * 2、
 * 三、 如何判断两个有环链表是否相交，相交则返回第一个相交节点，不相交则返回null
 */
public class TwoLinkedListIntersect {

    public Node twoLinkedListIntersect(Node head1, Node head2) {
        Node node1 = getLoopNode(head1);
        Node node2 = getLoopNode(head2);
        if (node1 == null && node2 == null) {
            return noLoopIntersect(head1, head2, null);
        } else if (node1 != null && node2 != null) {

            // 两个都是有环
            return bothLoop(node1, node2, head1, head2);
        }
        return null;
    }

    private Node bothLoop(Node loop1, Node loop2, Node head1, Node head2) {
        if (loop1 == loop2) {
            return noLoopIntersect(head1, head2, loop1);
        } else {
            // loop1 和 loop2 不相等； 那么loop1 在环中移动的时候 一定会碰到loop2
            Node cur = loop1;
            while (cur != loop1) {
                if (cur == loop2) {
                    return cur;
                }
                cur = cur.next;
            }
            return null;
        }
    }

    private Node noLoopIntersect(Node head1, Node head2, Node endNode) {
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1 != endNode) {
            n++;
            cur1 = cur1.next;
        }

        while (cur2 != endNode) {
            n--;
            cur2 = cur2.next;
        }

        if (cur1 != cur2) {
            return null;
        }

        cur1 = n > 0 ? head1 : head2;
        cur2 = cur1 == head1 ? head2 : head1;

        n = Math.abs(n);
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }


        // 现在两个链表长度相同了. 一起往下走。如果value相同 就是第一个相交节点
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return cur1;
    }

    private Node getLoopNode(Node node) {

        if (node == null || node.next == null || node.next.next == null) {
            return null;
        }

        Node slow = node.next;
        Node fast = node.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = node;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    public static void main(String[] args) {

        TwoLinkedListIntersect intersect = new TwoLinkedListIntersect();

        Node h1 = new Node(9);
        Node h2 = new Node(7);
        Node h3 = new Node(6);

        h1.next = h2;
        h2.next = h3;


        Node l1 = new Node(5);
        l1.next = h3;

        Node node = intersect.twoLinkedListIntersect(h1, l1);
        System.out.println(node);
    }
}
