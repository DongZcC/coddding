package com.dzc.book.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @date 2020-06-15 15:45
 * <p>
 * 最小距离
 * <p>
 * 给定一个字符串数组strs, 给定两个字符串 str1 str2 返回str1, str2 在strs 中的最小距离
 */
public class MinDistance {

    public int minDistance(String[] strs, String str1, String str2) {
        if (strs == null || str1 == null || str2 == null) {
            return -1;
        }

        int s1 = -1;
        int s2 = -1;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < strs.length; i++) {
            if (strs[i].equals(str1)) {
                min = Math.min(min, s2 == -1 ? min : i - s2);
                s1 = i;
            }
            if (strs[i].equals(str2)) {
                min = Math.min(min, s1 == -1 ? min : i - s1);
                s2 = i;
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }


    class Record {
        private HashMap<String, HashMap<String, Integer>> record;

        public Record(String[] strArr) {
            record = new HashMap<>();
            HashMap<String, Integer> indexMap = new HashMap<>();
            for (int i = 0; i < strArr.length; i++) {
                String curStr = strArr[i];
                update(indexMap, curStr, i);
                indexMap.put(curStr, i);
            }
        }

        private void update(HashMap<String, Integer> indexMap, String str, int i) {
            if (!record.containsKey(str)) {
                record.put(str, new HashMap<>());
            }

            HashMap<String, Integer> map = record.get(str);
            for (Map.Entry<String, Integer> lastEntry : map.entrySet()) {
                String key = lastEntry.getKey();
                int index = lastEntry.getValue();

                if (!key.equals(str)) {
                    HashMap<String, Integer> lastMap = record.get(key);
                    int curMin = i = index;
                    if (map.containsKey(key)) {
                        int preMin = map.get(key);
                        if (curMin < preMin) {

                        }
                    }
                }
            }
        }
    }
}
