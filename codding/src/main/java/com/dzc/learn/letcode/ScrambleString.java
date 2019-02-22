package com.dzc.learn.letcode;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class ScrambleString {

    public boolean isScramble(String s1, String s2) {

        if (s1 == null || s2 == null) {
            return s1 == s2;
        }

        if (s1.equals(s2)) {
            return true;
        }

        if (s1.length() != s2.length()) {
            return false;
        }

        int[] count = new int[26];


        // 检验是否所有的字符都完全一致
        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                return false;
            }
        }


        // 这里开始分治递归校验
        int len = s1.length();
        for (int i = 1; i < len; i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) &&
                    isScramble(s1.substring(i), s2.substring(i))) {
                return true;
            }

            if (isScramble(s1.substring(0, i), s2.substring(len - i)) &&
                    isScramble(s1.substring(i), s2.substring(0, len - i))) {
                return true;
            }
        }
        return false;
    }


    @Test
    public void test() {
        assertThat(isScramble("great", "rgeat"), Matchers.is(true));
        assertThat(isScramble("great", "rgtae"), Matchers.is(true));
        assertThat(isScramble("great", "taerg"), Matchers.is(true));
        assertThat(isScramble("great", "rgate"), Matchers.is(true));
    }
}
