package com.dzc.learn.letcode;

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {

    public String getPermutation(int n, int k) {
        List<String> result = new ArrayList<>();
        backtracking(result, new Holder(), n);
        return result.get(k - 1);
    }

    class Holder {
        StringBuilder sequence = new StringBuilder();
    }

    private void backtracking(List<String> result, Holder holder, int n) {
        if (holder.sequence.length() == n) {
            result.add(holder.sequence.toString());
        } else {
            for (int i = 1; i <= n; i++) {
                if (holder.sequence.indexOf(String.valueOf(i)) >= 0)
                    continue;
                holder.sequence.append(i);
                backtracking(result, holder, n);
                holder.sequence.deleteCharAt(holder.sequence.length() - 1);
            }
        }
    }


    public String getPermutation2(int n, int k) {
        List<Character> characters = new ArrayList<>();
        for (char i = '1'; i < '1' + n; i++) {
            characters.add(i);
        }
        k--;
        char[] t = new char[n];
        int l = 0;
        for (int i = n - 1; i >= 0; i--) {
            int fact = factorial(i);
            t[l++] = characters.get(k / fact);
            characters.remove(k / fact);
            k %= fact;
        }
        return new String(t);
    }

    private int factorial(int i) {
        int r = 1;
        for (int j = 1; j <= i; j++) {
            r *= j;
        }
        return r;
    }

    public static void main(String[] args) {
        PermutationSequence ps = new PermutationSequence();
        ps.getPermutation2(3, 3);
    }


}
