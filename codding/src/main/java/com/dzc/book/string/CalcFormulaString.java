package com.dzc.book.string;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Administrator
 * @date 2020-06-17 20:31
 * <p>
 * 公式字符串求值
 */
public class CalcFormulaString {

    public int getValue(String exp) {
        return value(exp.toCharArray(), 0)[0];
    }


    /**
     * 递归实现一个 栈结构
     *
     * @param chas
     * @param i
     * @return
     */
    private int[] value(char[] chas, int i) {
        Deque<String> deq = new LinkedList<>();
        int pre = 0;
        int[] bra;
        while (i < chas.length && chas[i] != ')') {
            if (chas[i] > '0' && chas[i] < '9') {
                pre = pre * 10 + chas[i] - '0';
            } else if (chas[i] != '(') {
                addNum(deq, pre);
                deq.addLast(String.valueOf(chas[i++]));
                pre = 0;
            } else {
                bra = value(chas, i + 1);
                pre = bra[0];
                i = bra[1] + 1;
            }
        }
        addNum(deq, pre);
        return new int[]{getNum(deq), i};
    }

    private int getNum(Deque<String> deq) {
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while (!deq.isEmpty()) {
            cur = deq.pollFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else {
                num = Integer.valueOf(cur);
                res += add ? num : -num;
            }
        }
        return res;
    }

    private void addNum(Deque<String> deq, int num) {
        if (!deq.isEmpty()) {
            int cur = 0;
            String top = deq.pollLast();
            if (top.equals("+") || top.equals("-")) {
                deq.addLast(top);
            } else {
                cur = Integer.parseInt(deq.pollLast());
                num = top.equals("*") ? (num * cur) : (cur / num);
            }
        }
        deq.addLast(String.valueOf(num));
    }
}
