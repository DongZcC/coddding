package com.dzc.book.binarytree;

/**
 * @author Administrator
 * @date 2020-05-25 14:56
 * <p>
 * 按照如下两种标准，实现二叉树边界节点的逆时针打印
 * <p>
 * 标准一：
 * 1.头节点是边界节点
 * 2.叶节点为边界节点
 * 3.如果节点在其所在的层中是最左或最右的吗，那么也是边界节点
 * <p>
 * 标准二：`
 * 1.头节点是边界节点
 * 2.叶节点是边界节点
 * 3.树左边界延申下去的路径为边界节点
 * 4.树有边界眼神下去的路径为边界节点`
 */
public class PrintBinaryTree {

    public void printEdge1(Node head) {
        if (head == null) {
            return;
        }
        int n = getHeight(head, 0);
        Node[][] edgeMap = new Node[n][2];

        setEdgeMap(head, 0, edgeMap);

        // 打印所有层中的最左节点.
        for (int i = 0; i < edgeMap.length; i++) {
            System.out.println(edgeMap[i][0].data);
        }

        // 打印叶节点
        printLeafNode(head, 0, edgeMap);


        // 从下到上打印
        for (int i = edgeMap.length - 1; i >= 0; i--) {
            if (edgeMap[i][1] != edgeMap[i][0])
                System.out.println(edgeMap[i][1].data);
        }

    }

    private void printLeafNode(Node head, int level, Node[][] edgeMap) {
        if (head == null) {
            return;
        }

        if (head.left == null && head.right == null && head != edgeMap[level][0] && head != edgeMap[level][1]) {
            System.out.println(head.data);
        }

        printLeafNode(head.left, level + 1, edgeMap);
        printLeafNode(head.right, level + 1, edgeMap);
    }

    /**
     * 这个递归方法可以。 想不到
     *
     * @param head
     * @param level
     * @param edgeMap
     */
    private void setEdgeMap(Node head, int level, Node[][] edgeMap) {
        if (head == null) {
            return;
        }
        edgeMap[level][0] = edgeMap[level][0] == null ? head : edgeMap[level][0];
        edgeMap[level][1] = head;
        setEdgeMap(head.left, level + 1, edgeMap);
        setEdgeMap(head.right, level + 1, edgeMap);
    }

    private int getHeight(Node head, int level) {
        if (head == null) {
            return level;
        }
        return Math.max(getHeight(head.left, level + 1), getHeight(head.right, level + 1));
    }


    public void printEdge2(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.data);
        if (head.left != null && head.right != null) {
            printLeftEdge(head.left, true);
            printRightEdge(head.right, true);
        } else {
            printEdge2(head.left != null ? head.left : head.right);
        }
    }

    private void printRightEdge(Node h, boolean print) {
        if (h == null) {
            return;
        }

        printRightEdge(h.left, print && h.right == null);
        printRightEdge(h.right, print);
        if (print || (h.left == null && h.right == null)) {
            System.out.println(h.data);
        }
    }

    private void printLeftEdge(Node h, boolean print) {
        if (h == null) {
            return;
        }

        if (print || (h.left == null && h.right == null)) {
            System.out.println(h.data + "");
        }

        printLeftEdge(h.left, print);
        printLeftEdge(h.right, print && h.left == null);
    }


    public static void main(String[] args) {
        Node root1 = new Node(1);
        Node node2 = new Node(2);
        root1.left = node2;
        root1.right = new Node(3);
        node2.left = new Node(4);
        PrintBinaryTree p = new PrintBinaryTree();
        System.out.println(p.getHeight(root1, 0));
    }
}
