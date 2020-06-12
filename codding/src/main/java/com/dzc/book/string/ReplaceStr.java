package com.dzc.book.string;

/**
 * @author Administrator
 * @date 2020-06-12 11:25
 */
public class ReplaceStr {


    public String replace(String str, String from, String to) {
        if (str == null || str.length() == 0) {
            return null;
        }

        char[] chas = str.toCharArray();
        int match = 0;
        char[] cfrom = from.toCharArray();
        for (int i = 0; i < chas.length; i++) {
            if (chas[i] == cfrom[match++]) {
                if (match == cfrom.length) {
                    clear(chas, 0, match);
                    match = 0;
                } else {
                    match = 0;
                }
            }
        }

        String res = "";
        String cur = "";
        for (int i = 0; i < chas.length; i++) {
            if (chas[i] != 0) {
                cur = cur + String.valueOf(chas[i]);
            }
            if (chas[i] == 0 && (i == 0 || chas[i - 1] != 0)) {
                res = res + cur + to;
                cur = "";
            }
        }
        if (!cur.equals("")) {
            res = res + cur;
        }
        return res;
    }

    private void clear(char[] chas, int start, int end) {
        for (int i = 0; i < chas.length; i++) {
            if (i >= start && i <= end) {
                chas[i] = 0;
            }
        }
    }
}
