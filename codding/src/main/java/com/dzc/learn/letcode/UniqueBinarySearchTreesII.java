package com.dzc.learn.letcode;

import java.util.ArrayList;
import java.util.List;

/**
 * G(n) = F(1, n) + F(2, n) + ... + F(n, n).
 * <p>
 * F(i, n) = G(i-1) * G(n-i)	1 <= i <= n
 * <p>
 * G(n) = G(0) * G(n-1) + G(1) * G(n-2) + … + G(n-1) * G(0)
 */
public class UniqueBinarySearchTreesII {

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<TreeNode>();
        }
        return genTrees(1, n);
    }

    private List<TreeNode> genTrees(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        if (start > end) {
            list.add(null);
            return list;
        }

        if (start == end) {
            list.add(new TreeNode(start));
            return list;
        }

        List<TreeNode> left, right;
        for (int i = start; i <= end; i++) {
            left = genTrees(start, i - 1);
            right = genTrees(i + 1, end);

            for (TreeNode lNode : left) {
                for (TreeNode rNode : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = lNode;
                    root.right = rNode;
                    list.add(root);
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        UniqueBinarySearchTreesII uq = new UniqueBinarySearchTreesII();
        uq.generateTrees(3);
    }
}
