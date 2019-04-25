package com.dzc.learn.letcode;

import java.util.ArrayList;
import java.util.List;

public class ConvertSortedListtoBinarySearchTree {


    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> array = new ArrayList<>();
        while (head != null) {
            array.add(head.val);
            head = head.next;
        }
        return helper(array, 0, array.size() - 1);
    }

    private TreeNode helper(List<Integer> array, int lo, int hi) {
        if (lo > hi)
            return null;
        int mid = (lo + hi) / 2;
        TreeNode head = new TreeNode(array.get(mid));
        head.left = helper(array, lo, mid - 1);
        head.right = helper(array, mid + 1, hi);
        return head;
    }
}
