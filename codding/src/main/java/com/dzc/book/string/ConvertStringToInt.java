package com.dzc.book.string;

/**
 * @author Administrator
 * @date 2020-06-12 10:00
 * <p>
 * 将正数字符串转成整数值
 * <p>
 * 给定字符串str 如果str符合日常书写的正数形式，并且属于32位正数的范围， 返回str所代表的正数值
 * 否则返回0
 */
public class ConvertStringToInt {

//    public int convert(String str) {
//        if (str == null || str.length() == 0) {
//            return 0;
//        }
//
//        char[] chs = str.toCharArray();
//
//        boolean posi = true;
//
//        if ((chs[0] - '0' <= 0 || chs[0] - '0' > 9) && chs[0] != '-') {
//            return 0;
//        }
//
//        if (chs[0] == '-') {
//            posi = false;
//        }
//        int sum = 0;
//        int cur = 0;
//        for (int i = 0; i < chs.length; i++) {
//            if (chs[i] - '0' < 0 || chs[i] - '0' > 9) {
//                return 0;
//            }
//
//            cur = chs[i] - '0';
//            sum = sum * 10 + cur;
//        }
//
//        return posi ? sum : -sum;
//    }


    public int convert(String str) {
        if (str == null || !isValid(str.toCharArray())) {
            return 0;
        }

        char[] chas = str.toCharArray();
        boolean posi = chas[0] != '-';
        int minq = Integer.MIN_VALUE / 10;
        int minr = Integer.MIN_VALUE % 10;
        int res = 0;

        int cur = 0;
        for (int i = posi ? 0 : 1; i < chas.length; i++) {
            // 用负数来表示， 避免溢出
            cur = '0' - chas[i];
            if (res < minq || (res == minq && cur < minr)) {
                return 0;
            }
            res = res * 10 + cur;
        }

        if (posi && res == Integer.MIN_VALUE) {
            return 0;
        }

        return posi ? -res : res;
    }

    public boolean isValid(char[] chas) {
        if (chas[0] != '-' && (chas[0] < '0' || chas[0] > '9')) {
            return false;
        }
        if (chas[0] == '-' && (chas.length == 1 || chas[1] == '0')) {
            return false;
        }
        if (chas[0] == '0' && chas.length > 1) {
            return false;
        }

        for (int i = 1; i < chas.length; i++) {
            if (chas[i] < '0' || chas[i] > '9') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ConvertStringToInt c = new ConvertStringToInt();
        int convert = c.convert("123");
        System.out.println(convert);
    }
}
