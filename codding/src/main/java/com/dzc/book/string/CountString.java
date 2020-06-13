package com.dzc.book.string;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Administrator
 * @date 2020-06-13 15:42
 * <p>
 * 字符串的统计字符串
 * <p>
 * 给定一个字符串str 返回str 的统计字符串
 */
public class CountString {

    public String countString(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }

        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for (char c : str.toCharArray()) {
            map.computeIfAbsent(c, k -> 1);
            map.computeIfPresent(c, (k, v) -> v + 1);
        }

        String res = "";
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();

            res += String.valueOf(key) + "_" + value + "_";
        }

        return res.substring(0, res.length() - 1);
    }


    public static void main(String[] args) {
        CountString c = new CountString();
        String aaabbadddffc = c.countString("aaabbadddffc");
        System.out.println(aaabbadddffc);

    }

}
