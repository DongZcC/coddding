package com.dzc.learn.letcode;


/**
 * Description:
 * User: dzczyw
 * Date: 2019-01-05
 * Time: 11:15
 *
 *
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
 * <p>
 *     If the last word does not exist, return 0.
 * </p>
 * Note: A word is defined as a character sequence consists of non-space characters only.
 *
 * <p>
 *     Example:
 * </p>
 *
 * Input: "Hello World"
 * Output: 5
 */
public class LengthofLastWord {

    public int lengthOfLastWord(String s) {
        if (s == null ||  s.length() == 0 || "".equals(s.trim()))
            return 0;
        s = s.trim();
        return s.length() - 1 - s.lastIndexOf(" ");
    }
}
