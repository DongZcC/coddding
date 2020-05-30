package com.dzc.book.binarytree;


/**
 * @author Administrator
 * @date 2020-05-30 15:43
 * <p>
 * 判断T1是否含有T2拓扑结构完全相同的子树
 */
public class JudgeT2HasT1SubTree {


    // 解法1： 对于t2的每一个子树， 都去判断结构是否和 t1 完全相同


    // 解法2： 先序遍历两个二叉树。 判断是否字串可以匹配
    public boolean isSubTree(Node t1, Node t2) {
        String t1Str = getNodeStr(t1);
        String t2Str = getNodeStr(t2);
        return getIndexOf(t1Str, t2Str) != -1;
    }

    private int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || m.length() > s.length()) {
            return -1;
        }

        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int[] next = getNextArr(ms);
        int si = 0;
        int mi = 0;
        while (si < ss.length && mi < ms.length) {
            if (ss[si] == ms[mi]) {
                si++;
                mi++;
            } else if (next[mi] < -1) {
                si++;
            } else {
                mi = next[mi];
            }
        }
        return mi == ms.length ? si - mi : -1;
    }

    private int[] getNextArr(char[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 1;
        int pos = 2;
        int cn = 0;
        while (pos < next.length) {
            if (ms[pos] == ms[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }
        return next;
    }

    private String getNodeStr(Node n) {
        if (n == null) {
            return "#!";
        }
        String res = n.data + "!";
        res += getNodeStr(n.left);
        res += getNodeStr(n.right);
        return res;
    }

}
