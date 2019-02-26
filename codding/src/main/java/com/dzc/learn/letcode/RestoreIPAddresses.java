package com.dzc.learn.letcode;


import java.util.ArrayList;
import java.util.List;

/**
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * <p>
 * For example:
 * Given "25525511135",
 * <p>
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 * <p>
 *
 * @author dongzc
 */
public class RestoreIPAddresses {

    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        backtracking(result, new ArrayList<>(), s, 0, s.length(), 0);
        return result;
    }

    public void backtracking(List<String> result, List<Integer> tempList, String s,
                             int start, int length, int maskNum) {
        if (maskNum == 4 && start == length) {
            StringBuilder sb = new StringBuilder();
            tempList.forEach(n -> {
                sb.append(n);
                sb.append(".");
            });
            result.add(sb.substring(0, sb.length() - 1));
        }
        if (maskNum > 4) {
            return;
        }

        for (int i = 1; i < 4 && start + i <= length; i++) {
            String num = s.substring(start, start + i);
            if (isValid(num)) {
                tempList.add(Integer.parseInt(num));
                backtracking(result, tempList, s, start + i, length, maskNum + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    private boolean isValid(String num) {
//        if (num.startsWith("0") && num.length() > 1) {
//            return false;
//        }
        int n = Integer.parseInt(num);
        return n >= 0 && n < 256;
    }
}
