package com.dzc.book.recursiveanddp;

/**
 * @author Administrator
 * @date 2020-06-05 11:09
 * <p>
 * 汉诺塔
 */
public class HanoiTower {


    public void hanoi(int n, String left, String mid, String right) {
        if (n == 1) {
            System.out.println("move from " + left + " to " + right);
            return;
        }

        hanoi(n - 1, left, right, mid);
        hanoi(1, left, mid, right);
        hanoi(n - 1, mid, left, right);
    }

    public static void main(String[] args) {
        HanoiTower hanoi = new HanoiTower();
        hanoi.hanoi(2, "left", "mid", "right");
    }
}
