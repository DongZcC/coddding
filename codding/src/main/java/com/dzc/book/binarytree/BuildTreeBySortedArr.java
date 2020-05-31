package com.dzc.book.binarytree;

/**
 * Created on 2020/5/31.
 *
 * @author _Shrimp
 * <p>
 * 通过有序数组生成一个平衡搜索二叉树
 */
public class BuildTreeBySortedArr {


    public Node buildTree(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        return generate(arr, 0, arr.length - 1);
    }

    private Node generate(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        Node n = new Node(arr[mid]);
        n.left = generate(arr, start, mid - 1);
        n.right = generate(arr, mid + 1, end);
        return n;
    }
}
