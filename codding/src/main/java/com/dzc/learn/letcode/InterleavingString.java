package com.dzc.learn.letcode;

/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * <p>
 * Example 1:
 * <p>
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * Example 2:
 * <p>
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 */
public class InterleavingString {

    public boolean isInterleave(String s1, String s2, String s3) {

        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        int[] count = new int[26];
        String tmp = s1 + s2;
        for (int i = 0; i < s3.length(); i++) {
            count[tmp.charAt(i)]++;
            count[s3.charAt(i)]--;
        }

        for (int c : count) {
            if (c != 0) {
                return false;
            }
        }

        // s1 + s2 各种组合; 但是要保证一点；就是有序

        return false;
    }
}
