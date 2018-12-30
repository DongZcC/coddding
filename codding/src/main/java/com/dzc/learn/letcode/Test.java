package com.dzc.learn.letcode;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Test t = new Test();

       t.solveNQueens(3).forEach(System.out::println);

    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        // 无解 直接返回
//        if (n < 4)
//            return results;
        // 先构造一个数组 ； 下标属于第几行； 值代表在行中的第几列
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }

        // 问题转换为一个全排列的问题; 给定数组 找出全部符合要求的排列方式
        backtracking(results, new ArrayList<>(), a);
        return results;
    }


    private void backtracking(List<List<String>> results, List<Integer> tempList, int[] candidates) {
        // 长度符合, 并且符合皇后问题解法
        if (tempList.size() == candidates.length) {
            results.add(convert(tempList));
        } else {
            for (int i = 0; i < candidates.length; i++) {
                if (tempList.contains(candidates[i]) || !isValidate(tempList, candidates[i], candidates.length))
                    continue;
                tempList.add(candidates[i]);
                backtracking(results, tempList, candidates);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    private boolean isValidate(List<Integer> tempList, int x, int n) {
        // 这种解法已经限制死了不可能出现同行或者同列的问题; 只要解决斜线问题就可以
        // 皇后 是直接可以按照延长线来吃的 ; 很凶; 那么就需要检查  45° 和 135° 延长线才行
        // 在往里面增加的时候就判断是否符合 皇后的规则

        // x 代表当前的横坐标
        // y 代表当前的纵坐标
        int y = tempList.size();

        // check 45 diagonal
        for (int i = x, j = y; i >= 0 && j >= 0; i--, j--) {
            if (tempList.size() > j && tempList.get(j) == i)
                return false;
        }

        // check 135 diagonal
        for (int i = x, j = y; i < n && j >= 0; i++, j--) {
            if (tempList.size() > j && tempList.get(j) == i)
                return false;
        }

        return true;
    }

    private List<String> convert(List<Integer> tempList) {
        List<String> results = new ArrayList();
        tempList.forEach(i -> {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < tempList.size(); j++) {
                if (j == i) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            results.add(sb.toString());
        });
        return results;
    }


    // [Q...., ..Q.., ....Q, .Q..., ...Q.]
//     [Q...., ...Q., .Q..., ....Q, ..Q..]
//     [.Q..., ...Q., Q...., ..Q.., ....Q]
//     [.Q..., ...Q., Q...., ....Q, ..Q..]
//     [.Q..., ....Q, ..Q.., Q...., ...Q.]
//     [..Q.., Q...., ...Q., .Q..., ....Q]
//     [..Q.., Q...., ....Q, .Q..., ...Q.]
//     [..Q.., ....Q, Q...., ...Q., .Q...]
//     [..Q.., ....Q, .Q..., ...Q., Q....]
//     [...Q., Q...., ..Q.., ....Q, .Q...]
//     [...Q., .Q..., ....Q, Q...., ..Q..]
//     [...Q., .Q..., ....Q, ..Q.., Q....]
//     [....Q, .Q..., ...Q., Q...., ..Q..]
//     [....Q, ..Q.., Q...., ...Q., .Q...]

//    [["Q....","..Q..","....Q",".Q...","...Q."],
//     ["Q....","...Q.",".Q...","....Q","..Q.."],
//     [".Q...","...Q.","Q....","..Q..","....Q"],
//     [".Q...","....Q","..Q..","Q....","...Q."],
//     ["..Q..","Q....","...Q.",".Q...","....Q"],
//     ["..Q..","....Q",".Q...","...Q.","Q...."],
//     ["...Q.","Q....","..Q..","....Q",".Q..."],
//     ["...Q.",".Q...","....Q","..Q..","Q...."],
//     ["....Q",".Q...","...Q.","Q....","..Q.."],
//     ["....Q","..Q..","Q....","...Q.",".Q..."]]


//     [".Q...","...Q.","Q....","..Q..","....Q"],
//     [".Q...","....Q","..Q..","Q....","...Q."],


//     [.Q..., ...Q., Q...., ..Q.., ....Q]
//     [.Q..., ...Q., Q...., ....Q, ..Q..]
//     [.Q..., ....Q, ..Q.., Q...., ...Q.]
}
