package com.dzc.learn.letcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsII {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
        backtracking(results, new ArrayList<>(), 0, nums);
        return results;
    }

    private void backtracking(List<List<Integer>> results, ArrayList<Integer> temp, int start, int[] nums) {
        results.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            temp.add(nums[i]);
            backtracking(results, temp, i + 1, nums);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        SubsetsII s = new SubsetsII();
        System.out.println(s.subsetsWithDup(new int[]{1, 2, 2}));
    }
}
