package com.dzc.book.binarytree;

/**
 * @author Administrator
 * @date 2020-05-26 14:26
 * <p>
 * 遍历二叉树的神级方法
 */
public class MorrisIterateTree {

    /**
     * 步骤 1 ： 找到节点左子树的最右节点 链接到当前节点中
     * <p>
     * 步骤2 ： 判断节点左子树中最右节点是否指向cur ， 如果是 指向空， 打印cur 然后通过cur 的right 指针移动到下一个节点 。 重复步骤2
     *
     * @param head
     */
    public void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur1 = head;
        Node cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            }
            System.out.println(cur1.data + " ");
            cur1 = cur1.right;
        }
        System.out.println();
    }


    public void morrisPre(Node head) {
        if (head == null) {
            return;
        }

        Node cur1 = head;
        Node cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    System.out.println(cur1.data + " ");
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            } else {
                System.out.println(cur1.data + " ");
            }
            cur1 = cur1.right;
        }
    }


    public void morrisPos(Node head) {
        if (head == null) {
            return;
        }
        Node cur1 = head;
        Node cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                    printEdge(cur1.left);
                }
            }
            cur1 = cur1.right;
        }
        printEdge(head);
        System.out.println();
    }

    private void printEdge(Node head) {
        Node tail = reverseEdge(head);
        Node cur = tail;
        while (cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    private Node reverseEdge(Node from) {
        Node pre = null;
        Node next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        MorrisIterateTree tree = new MorrisIterateTree();

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        node4.left = node2;
        node4.right = node6;
        node2.left = node1;
        node2.right = node3;
        node6.left = node5;
        node6.right = node7;
        tree.morrisPre(node4);
    }
}
