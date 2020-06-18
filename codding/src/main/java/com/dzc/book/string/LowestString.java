package com.dzc.book.string;

import java.util.Arrays;

/**
 * @author Administrator
 * @date 2020-06-18 8:53
 * <p>
 * 拼接所有字符串中字典顺序最小的字符串
 */
public class LowestString {


    public String lowestString(String[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        }

        Arrays.sort(strs, (a, b) -> {
            return (a + b).compareTo(b + a);
        });

        String res = "";

        for (String str : strs) {
            res += str;
        }

        return res;
    }

}
