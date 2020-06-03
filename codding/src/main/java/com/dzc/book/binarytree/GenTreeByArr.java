package com.dzc.book.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @date 2020-06-02 16:35
 * <p>
 * 先序、中序、后续数组两两结合 重构二叉树
 */
public class GenTreeByArr {

    public Node preInToTree(int[] pre, int[] in) {
        // 先序的数组， 第一个就是头节点
        // 用先序数组的值， 在中序数组里面切割， 左面的是左子树，右面的是右子树， 继续递归
        if (pre == null || in == null || pre.length <= 0 || in.length <= 0) {
            return null;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }

        return preIn(pre, 0, pre.length - 1, in, 0, in.length - 1, map);
    }

    private Node preIn(int[] pre, int pi, int pj, int[] in, int ii, int ij, HashMap<Integer, Integer> map) {
        if (pi > pj) {
            return null;
        }

        Node head = new Node(pre[pi]);
        int index = map.get(pre[pi]);

        head.left = preIn(pre, pi + 1, pi + index - ii, in, ii, index - 1, map);
        head.right = preIn(pre, pi + index - ii + 1, pj, in, index + 1, ij, map);
        return head;
    }

    /**
     * 只有每个节点的孩子都是 0 或 2 的二叉树才可以被先序 + 后续重构出来
     *
     * @param pre
     * @param post
     * @return
     */
    public Node prePostToTree(int[] pre, int[] post) {
        if (pre == null || post == null) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < post.length; i++) {
            map.put(post[i], i);
        }
        return prePost(pre, 0, pre.length - 1, post, 0, post.length - 1, map);
    }

    private Node prePost(int[] p, int pi, int pj, int[] s, int si, int sj, Map<Integer, Integer> map) {
        Node head = new Node(s[sj--]);
        if (pi == pj) {
            return head;
        }

        int index = map.get(p[++pi]);
        head.left = prePost(p, pi, pi + index - si, s, si, index, map);
        head.right = prePost(p, pi + index - si + 1, pj, s, index + 1, sj, map);
        return head;
    }


    /**
     * 有中序数组的都很类似， 没有什么明显差别
     *
     * @param in
     * @param post
     * @return
     */
    public Node inPostToTree(int[] in, int[] post) {
        return null;
    }
}
