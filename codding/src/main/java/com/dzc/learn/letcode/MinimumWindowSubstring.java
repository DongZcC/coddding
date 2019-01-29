package com.dzc.learn.letcode;



public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {

        int[] c = new int[256]; // 存放t 中的字符
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;
        for (char c1 : t.toCharArray()) {
            c[c1]++;
        }

        // construct the sliding window
        int i = 0;
        int j = 0;
        int count = t.length(); // the count of the character

        while (j < s.length()) {
            if (c[s.charAt(j)] > 0) {
                count--;
            }
            c[s.charAt(j)]--;
            j++;
            while (count == 0) {
                if (minLen > j - i) {
                    minLen = j - i;
                    minStart = i;
                }
                c[s.charAt(i)]++;
                if (c[s.charAt(i)] > 0) {
                    count++;
                }
                i++;
            }
        }
        return s.substring(minStart, minStart + minLen);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring minimumWindowSubstring = new MinimumWindowSubstring();
        minimumWindowSubstring.minWindow("ADOBECODEBANC", "ABC");
    }
}
