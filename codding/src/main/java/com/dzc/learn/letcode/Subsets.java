package com.dzc.learn.letcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtracking(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtracking(List<List<Integer>> result, ArrayList<Integer> temp, int[] nums, int start) {
        result.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            backtracking(result, temp, nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }


    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        subsets.subsets(new int[]{1, 2, 3});
    }
}
