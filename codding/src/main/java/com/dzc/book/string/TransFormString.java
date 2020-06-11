package com.dzc.book.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @date 2020-06-11 16:58
 * <p>
 * 判断两个字符串是否互为变形词
 */
public class TransFormString {

    /**
     * 用hash表 或者用数组 来代表 出现的次数
     *
     * @param str1
     * @param str2
     * @return
     */
    public boolean isTransform(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (char c : str1.toCharArray()) {
            map.putIfAbsent(c, 1);
            map.put(c, map.get(c) + 1);
        }

        for (char c : str2.toCharArray()) {
            Integer v = map.get(c);
            if (v == null) {
                return false;
            }

            map.put(c, map.get(c) - 1);
        }


        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 0) {
                return false;
            }
        }

        return true;
    }
}
