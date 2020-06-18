package com.dzc.book.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @date 2020-06-18 8:56
 * <p>
 * 找到字符串的最长无重复字串
 */
public class FindLongestSubString {


    public int longestSubString(String str) {
        if (str == null || str.equals("")) {
            return -1;
        }

        // 存储当前字符首次出现的位置.
        Map<Character, Integer> map = new HashMap<>();
        int len = 0;
        // 当前遍历到str[i]  表示必须以str[i-1]结尾的情况下，最长无重复字符串开始的前一个位置
        int pre = -1;

        char[] chas = str.toCharArray();

        int cur = 0;
        for (int i = 0; i != chas.length; i++) {
            pre = Math.max(pre, map.get(chas[i]) == null ? -1 : map.get(chas[i]));
            cur = i - pre;
            len = Math.max(len, cur);
            map.put(chas[i], i);
        }

        return len;
    }
}
