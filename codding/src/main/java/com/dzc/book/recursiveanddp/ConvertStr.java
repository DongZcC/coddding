package com.dzc.book.recursiveanddp;

/**
 * @author Administrator
 * @date 2020-06-08 13:18
 * <p>
 * 数字字符串转换为字母组合的种数
 * <p>
 * 给定字符串str， 全部由数字组成， 如果一个或相邻的两个 值在1-26之间就可以转换成一个字母
 */
public class ConvertStr {

    /**
     * 定义递归函数 p(i) ( i > 0 < N) p(i)表示 str[0...i-1] 已经转换完毕.
     * p(N) = 1
     *
     * @param str
     * @return
     */
    public int convertStr(String str) {
        return p1(str.toCharArray(), 0, str.length());
    }

    private int p1(char[] str, int index, int N) {
        if (index == N) {
            return 1;
        }
        if (str[index] == '0') {
            return 0;
        }

        int res = p1(str, index + 1, N);
        if (index + 1 < str.length && ((str[index] - '0') * 10 + str[index + 1] - '0' < 27)) {
            res += p1(str, index + 2, N);
        }
        return res;
    }


    private int num2(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }

        char[] chs = str.toCharArray();

        int cur = chs[chs.length - 1] == '0' ? 0 : 1;
        int next = 1;
        int tmp = 0;
        for (int i = chs.length - 2; i >= 0; i--) {
            if (chs[i] == 0) {
                next = cur;
                cur = 0;
            } else {
                tmp = cur;
                if ((chs[i] - '0') * 10 + chs[i + 1] - '0' < 27) {
                    cur += next;
                }
                next = tmp;
            }
        }
        return cur;
    }

}
