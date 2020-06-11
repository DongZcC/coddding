package com.dzc.book.string;

/**
 * @author Administrator
 * @date 2020-06-11 17:12
 * <p>
 * 字符串中数字字串的求和
 * <p>
 * 给定一个字符串str， 求其中全部数字串 所代表的数字之和
 */
public class SumSubString {


    /**
     * 从左到右依次遍历；
     * <p>
     * 对于生成的数字 有两种判断
     * '0' - '9' 保持累加状态
     * 'else' 直接进行累加和
     *
     * @param str
     * @return
     */
    public int sumSubString(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        char[] chs = str.toCharArray();

        int res = 0;
        int num = 0;
        boolean posi = true;
        int cur = 0;

        for (int i = 0; i < chs.length; i++) {
            cur = chs[i] - '0';

            if (cur < 0 || cur > 9) {
                res += num;
                num = 0;
                if (chs[i] == '-') {
                    if (i - 1 > -1 && chs[i - 1] == '-') {
                        posi = !posi;
                    } else {
                        posi = false;
                    }
                }
            } else {
                num = num * 10 + (posi ? cur : -cur);
            }
        }
        return res;
    }
}
