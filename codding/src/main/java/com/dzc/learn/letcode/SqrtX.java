package com.dzc.learn.letcode;


public class SqrtX {
    public int mySqrt(int x) {
        for (int i = 0; i <= x; i++) {
            if ((i + 1) * (i + 1) < x) {

            } else if ((i + 1) * (i + 1) > x)
                return i;
            else
                return i + 1;
        }
        return 0;
    }

    public int mySqrt2(int x) {
        long r = x / 2 + 1;
        while (r * r > x) {
            r = (r + x / r) / 2;
        }
        return (int) r;
    }

    public int mySqrt3(int x) {
        if (x == 0)
            return 0;
        int left = 1;
        int right = x / 2 + 1;
        while (true) {
            int mid = left + (right - left) / 2;
            if (mid > x / mid) {
                right = mid - 1;
            } else {
                if (mid + 1 > x / (mid + 1))
                    return mid;
                left = mid + 1;
            }
        }
    }

    public static void main(String[] args) {
        SqrtX x = new SqrtX();
        x.mySqrt2(5);
    }
}
