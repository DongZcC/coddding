package com.dzc.learn.letcode;


import java.util.ArrayList;
import java.util.List;

public class Combinations {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();

        backtracking(result, new ArrayList<>(), n, k, 1);

        return result;
    }

    private void backtracking(List<List<Integer>> result, List<Integer> temp, int candidates, int len, int start) {
        if (temp.size() == len) {
            result.add(new ArrayList<>(temp));
        } else {
            for (int i = start; i <= candidates; i++) {
                temp.add(i);
                backtracking(result, temp, candidates, len, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
