package com.dzc.book.string;

/**
 * @author Administrator
 * @date 2020-05-30 15:48
 * <p>
 * KMP 字符串算法
 */
public class KMP {


    /**
     * 判断串中是否包含
     *
     * @param str
     * @param match
     * @return
     */
    public int sub(String str, String match) {
        if (str == null || match == null || match.length() < 1 || match.length() > str.length()) {
            return -1;
        }


        char[] ss = str.toCharArray();
        char[] ms = match.toCharArray();

        int si = 0;
        int mi = 0;
        int[] next = getNextArray(ms);
        while (si < ss.length && mi < ms.length) {
            if (ss[si] == ms[mi]) {
                si++;
                mi++;
            } else if (next[mi] == -1) {
                si++;
            } else {
                mi = next[mi];
            }
        }
        return mi == ms.length ? si - mi : -1;
    }

    private int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[-1];
        }

        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;

        int pos = 2;
        int cn = 0;
        while (pos < next.length) {
            if (ms[pos - 1] == ms[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }
        return next;
    }


    public static void main(String[] args) {
        KMP k = new KMP();
        k.sub("aaacaaab", "aaab");
    }
}
