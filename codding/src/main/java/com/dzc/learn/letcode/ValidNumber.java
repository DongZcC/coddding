package com.dzc.learn.letcode;

public class ValidNumber {
    public boolean isNumber(String s) {
        if (s == null)
            return false;
        s = s.trim();

        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        boolean numberAfterE = true;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                numberSeen = true;
                numberAfterE = true;
            } else if (s.charAt(i) == '.') {
                // e 出现过 或者 小数点出现过都是非法
                if (eSeen || pointSeen)
                    return false;
                pointSeen = true;
            } else if (s.charAt(i) == 'e') {
                // 如果没见过数字 直接出现e 非法
                // 如果出现过e 再出现 非法
                if (!numberSeen || eSeen)
                    return false;
                eSeen = true;
                numberAfterE = false;
            } else if (s.charAt(i) == '-' || s.charAt(i) == '+') {
                // 指数的符号 / 整个数字的符号
                if (i != 0 && s.charAt(i - 1) != 'e')
                    return false;
            } else
                return false;
        }

        return numberSeen && numberAfterE;
    }

    public static void main(String[] args) {
        Integer.parseInt(".1");
    }
}
