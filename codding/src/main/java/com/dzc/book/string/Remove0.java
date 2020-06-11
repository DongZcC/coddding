package com.dzc.book.string;

import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * @author Administrator
 * @date 2020-06-11 19:14
 * <p>
 * 去掉字符串中连续出现的k个0的子串
 */
public class Remove0 {

    public String remove0(String str, int k) {
        if (str == null) {
            return null;
        }

        char[] chs = str.toCharArray();
        int start = -1;
        int count = 0;
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == '0') {
                start = start == -1 ? i : start;
                count++;
            } else {
                if (count == k) {
                    while (count-- > 0) {
                        chs[start++] = 0;
                    }
                }
            }
        }

        if (count == k) {
            while (count-- > 0) {
                chs[start++] = 0;
            }
        }
        return String.valueOf(chs);
    }

    public static void main(String[] args) {
        Remove0 r = new Remove0();
        String a000B = r.remove0("A000B", 3);
        System.out.println(a000B);
    }
}
