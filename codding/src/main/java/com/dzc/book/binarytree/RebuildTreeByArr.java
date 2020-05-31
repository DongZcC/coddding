package com.dzc.book.binarytree;

/**
 * Created on 2020/5/31.
 *
 * @author _Shrimp
 * <p>
 * 根据后序数组重建搜索二叉树
 */
public class RebuildTreeByArr {


    public static void main(String[] args) {
        RebuildTreeByArr rebuild = new RebuildTreeByArr();
        boolean bst = rebuild.isBST(new int[]{1, 3, 2});
        Node node = rebuild.rebuildTree(new int[]{1, 3, 2});
        System.out.println(bst);
    }

    /**
     * 已知数组中没有重复节点， 判断是否可能是一个搜索二叉树的后续遍历结果
     *
     * @param arr
     * @return
     */
    public boolean isBST(int[] arr) {

        // 搜索二叉树有什么特性？.  BST， 左边节点比头节点小， 右边节点比头节点大
        // 后续遍历: 头节点在最后.   比头节点小的节点全部在数组的左边， 比头节点大的数字全部在头节点右边。
        if (arr == null || arr.length == 0) {
            return false;
        }
        return isBST(arr, 0, arr.length - 1);
    }

    private boolean isBST(int[] arr, int start, int end) {
        if (start == end) {
            return true;
        }

        int less = -1;
        int more = end;
        for (int i = 0; i < end; i++) {
            if (arr[end] > arr[i]) {
                less = i;
            } else {
                // 找到第一个位置
                more = more == end ? i : more;
            }
        }

        // 只有一边有树
        if (less == -1 || more == end) {
            return isBST(arr, start, end - 1);
        }

        if (less != more - 1) {
            return false;
        }

        return isBST(arr, start, less) && isBST(arr, more, end - 1);
    }

    public Node rebuildTree(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        return buildTree(arr, 0, arr.length - 1);
    }

    private Node buildTree(int[] arr, int start, int end) {
        if (start == end) {
            return new Node(arr[start]);
        }

        int less = -1;
        int more = end;
        for (int i = 0; i < end; i++) {
            if (arr[end] > arr[i]) {
                less = i;
            } else {
                // 找到第一个位置
                more = more == end ? i : more;
            }
        }

        Node n = new Node(arr[end]);
        if (less == -1) {
            n.right = buildTree(arr, start, end - 1);
        } else if (more == end) {
            n.left = buildTree(arr, start, end - 1);
        } else {
            n.left = buildTree(arr, start, less);
            n.right = buildTree(arr, more, end - 1);
        }
        return n;
    }
}
