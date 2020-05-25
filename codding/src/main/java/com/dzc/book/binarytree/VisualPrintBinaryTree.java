package com.dzc.book.binarytree;


/**
 * @author Administrator
 * @date 2020-05-25 16:44
 * <p>
 * 非常直观的打印出一颗二叉树
 */
public class VisualPrintBinaryTree {

    public void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInorder(head, 0, "H", 17);
        System.out.println();

    }

    private void printInorder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }

        printInorder(head.right, height + 1, "v", len);
        String val = to + head.data + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInorder(head.left, height + 1, "^", len);
    }

    private java.lang.String getSpace(int lenL) {
        String space = " ";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lenL; i++) {
            sb.append(space);
        }
        return sb.toString();
    }
}
