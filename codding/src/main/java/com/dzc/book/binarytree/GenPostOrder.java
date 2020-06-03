package com.dzc.book.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @date 2020-06-02 17:23
 * <p>
 * 通过先序和中序数组生成后序数组
 */
public class GenPostOrder {


    public int[] genPostOrder(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return null;
        }

        int len = pre.length;
        int[] pos = new int[len];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        setPos(pre, 0, len - 1, in, 0, len - 1, pos, len - 1, map);
        return pos;
    }

    private int setPos(int[] p, int pi, int pj, int[] n, int ni, int nj, int[] s, int si, Map<Integer, Integer> map) {
        if (pi > pj) {
            return si;
        }
        s[si--] = p[pi];
        int i = map.get(p[pi]);
        si = setPos(p, pj - nj + i + 1, pj, n, i + 1, nj, s, si, map);
        return setPos(p, pi + 1, pi + i - ni, n, ni, i - 1, s, si, map);
    }
}
