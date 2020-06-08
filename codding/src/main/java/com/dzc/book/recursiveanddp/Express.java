package com.dzc.book.recursiveanddp;

/**
 * @author Administrator
 * @date 2020-06-08 14:05
 * <p>
 * 表达式得到起往结果组成的种数
 * <p>
 * <p>
 * 给定一个只有 0 1  & | ^ 五种字符组成的字符串 express , 给定一个boolean desired
 * <p>
 * express能有多少种组合方式， 可以达到 desired 的结果
 */
public class Express {

    /**
     * 暴力递归的方法.
     *
     * @param express
     * @param desired
     * @return
     */
    public int num1(String express, boolean desired) {
        // express 必须为奇数
        // 表达式偶数位置字符一定是 '0' or '1'
        // 表达式奇数位一定是 | & ^
        //

        if (!isValid(express.toCharArray())) {
            return 0;
        }

        return process(express.toCharArray(), desired, 0, express.length() - 1);
    }

    private int process(char[] exp, boolean desired, int l, int r) {
        if (l == r) {
            if (exp[l] == '1') {
                return desired ? 1 : 0;
            } else {
                return desired ? 0 : 1;
            }
        }

        int res = 0;
        if (desired) {
            // true
            for (int i = l + 1; i < r; i++) {
                switch (exp[i]) {
                    case '&':
                        res += process(exp, true, l, i - 1) * process(exp, true, i + 1, r);
                        break;
                    case '|':
                        res += process(exp, true, l, i - 1) * process(exp, false, i + 1, r);
                        res += process(exp, false, l, i - 1) * process(exp, true, i + 1, r);
                        res += process(exp, true, l, i - 1) * process(exp, true, i + 1, r);
                        break;
                    case '^':
                        res += process(exp, true, l, i - 1) * process(exp, false, i + 1, r);
                        res += process(exp, false, l, i - 1) * process(exp, true, i + 1, r);
                        break;
                }
            }
        } else {
            for (int i = l + 1; i < r; i++) {
                switch (exp[i]) {
                    case '&':
                        res += process(exp, true, l, i - 1) * process(exp, false, i + 1, r);
                        res += process(exp, false, l, i - 1) * process(exp, true, i + 1, r);
                        res += process(exp, false, l, i - 1) * process(exp, false, i + 1, r);
                        break;
                    case '|':
                        res += process(exp, false, l, i - 1) * process(exp, false, i + 1, r);
                        break;
                    case '^':
                        res += process(exp, true, l, i - 1) * process(exp, true, i + 1, r);
                        res += process(exp, false, l, i - 1) * process(exp, false, i + 1, r);
                        break;
                }
            }
        }

        return res;
    }

    private boolean isValid(char[] exp) {
        if ((exp.length & 1) == 0) {
            return false;
        }

        for (int i = 0; i < exp.length; i += 2) {
            if (exp[i] != '1' && exp[i] != '0') {
                return false;
            }
        }

        for (int i = 1; i < exp.length; i += 2) {
            if (exp[i] != '^' && exp[i] != '|' && exp[i] != '&') {
                return false;
            }
        }

        return true;
    }


    private int num2(String express, boolean desired) {
        if (express == null || express.equals("")) {
            return 0;
        }

        char[] exp = express.toCharArray();
        if (!isValid(exp)) {
            return 0;
        }

        // t[j][i] 表示 express[j..i]组成true 的种树
        int[][] t = new int[exp.length][exp.length];
        int[][] f = new int[exp.length][exp.length];

        t[0][0] = exp[0] == '0' ? 0 : 1;
        f[0][0] = exp[0] == '1' ? 0 : 1;

        for (int i = 2; i < exp.length; i += 2) {
            t[i][i] = exp[i] == '0' ? 0 : 1;
            f[i][i] = exp[i] == '1' ? 0 : 1;
            for (int j = i - 2; j >= 0; j -= 2) {
                for (int k = j; k < i; k += 2) {
                    if (exp[k + 1] == '&') {
                        t[j][i] += t[j][k] * t[k + 1][i];
                        f[j][i] += (f[j][k] + t[j][k]) * f[k + 2][i];
                    }
                }
            }
        }

        return 0;
    }
}
