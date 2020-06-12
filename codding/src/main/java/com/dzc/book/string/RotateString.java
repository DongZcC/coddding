package com.dzc.book.string;

/**
 * @author Administrator
 * @date 2020-06-12 9:48
 * <p>
 * 判断两个字符串是否互为旋转词
 */
public class RotateString {

    public boolean isRotate(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        // 这个题目比较简单。 把b 再累加一次。 看下有没有包含a 就可以了
        // abcd ---> cdab + cdab / 肯定有构造了一个 abcd
        String b2 = str2 + str2;
        return b2.contains(str1);
    }
}
