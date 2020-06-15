package com.dzc.book.string;

/**
 * @author Administrator
 * @date 2020-06-15 14:32
 * <p>
 * 翻转字符串
 */
public class FlipString {


    /**
     * 翻转字符串。 单词逆序
     * <p>
     * dog  loves pig -> pig loves dog
     *
     * @param chas
     */
    public void flip(char[] chas) {
        if (chas == null || chas.length == 0) {
            return;
        }
        // 先整体逆序
        reverse(chas, 0, chas.length - 1);

        int l = -1;
        int r = -1;
        // 单词逆序.
        for (int i = 0; i < chas.length; i++) {
            if (chas[i] == ' ') {
                l = i == 0 || chas[i - 1] == ' ' ? i : l;
                r = i == chas.length - 1 || chas[i + 1] == ' ' ? i : r;
            }

            if (l != -1 && r != -1) {
                reverse(chas, l, r);
                l = -1;
                r = -1;
            }
        }
    }

    /**
     * 给定字符串 ABCDE  size = 3. -> DEABC
     *
     * @param chas
     */
    public void flip2(char[] chas, int size) {
        if (chas == null || chas.length == 0 || size <= 0 || size >= chas.length) {
            return;
        }

        reverse(chas, 0, size - 1);
        reverse(chas, size, chas.length - 1);
        reverse(chas, 0, chas.length - 1);
    }

    private void reverse(char[] chas, int start, int end) {
        while (start < end) {
            swap(chas, start++, end--);
        }
    }

    private void swap(char[] chas, int i, int j) {
        char tmp = chas[i];
        chas[i] = chas[j];
        chas[j] = tmp;
    }
}
