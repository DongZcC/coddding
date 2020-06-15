package com.dzc.book.string;

/**
 * @author Administrator
 * @date 2020-06-15 13:57
 * <p>
 * 字符串的调整与替换
 * <p>
 * 给定一个字符类型的数组 chas[], chas右半区全是空字符， 左半区不含有空字符。
 */
public class AdjustAndReplaceString {


    /**
     * 算法 O(n)  额外空间要求 O(1)
     *
     * @param chas
     * @return
     */
    public char[] replace(char[] chas) {
        if (chas == null || chas.length == 0) {
            return null;
        }

        // 左半区的长度
        int len = 0;
        int num = 0;
        for (len = 0; len < chas.length && chas[len] != 0; len++) {
            if (chas[len] == ' ') {
                num++;
            }
        }

        int j = len + num * 2 - 1;
        for (int i = len - 1; i > -1; i--) {
            if (chas[i] != ' ') {
                chas[j--] = chas[i];
            } else {
                chas[j--] = '0';
                chas[j--] = '2';
                chas[j--] = '%';
            }
        }
        return chas;
    }


    /**
     * 字符型数组 包含数字 和 *
     * <p>
     * * 到chas左边， 数字到chas右边
     * <p>
     * 要求: 数字字符从左到右的顺序不改变
     * <p>
     * 从右到左复制. 最后直接写*
     * <p>
     * 12**345  -> **12345
     *
     * @param chas
     */
    public void adjust(char[] chas) {
        if (chas == null || chas.length == 0) {
            return;
        }

        int j = chas.length - 1;
        for (int i = chas.length - 1; i > -1; i--) {
            if (chas[i] != '*') {
                chas[j--] = chas[i];
            }
        }
        for (; j > -1; ) {
            chas[j--] = '*';
        }
    }
}
