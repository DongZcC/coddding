package com.dzc.learn.letcode;

import java.util.Arrays;

public class DecodeWays {


    /**
     * Decode Ways - LeetCode: https://leetcode.com/problems/decode-ways
     *
     * @author Benyam Ephrem
     * <p>
     * This code is by NO MEANS cleanly or well done. I just threw this together
     * for teaching purposes and it is generally a sloppy example but gets the point
     * across
     * <p>
     * This code passes all Leetcode test cases as of Jan. 11 2019
     * Runtime: 5 ms, faster than 51.10% of Java online submissions for Decode Ways.
     * <p>
     * The video to explain this code is here: https://www.youtube.com/watch?v=YcJTyrG3bZs
     */

    public int numDecodings(String s) {

      /*
        We will cache the answers to our subproblems
      */
        int[] previousAnswers = new int[s.length() + 1];
        Arrays.fill(previousAnswers, -1);

        return numDecodings(s, 0, previousAnswers);
    }

    public int numDecodings(String s, int decodePointer, int[] previousAnswers) {

  /*
    If our decoding pointer out of bounds then we know that we have
    exhausted our ability to decode the string
  */
        if (decodePointer >= s.length()) {
            return 1;
        }

  /*
    If we already know the answer to this subproblem then just return it,
    don't waste time calculating it
  */
        if (previousAnswers[decodePointer] > -1) {
            return previousAnswers[decodePointer];
        }

  /*
    We don't already know the answer to this subproblem, calculate it
    by taking the sum of the total ways for a single character decoring
    or 2 character decoding
  */
        int totalWaysFromHere = 0;

  /*
    These checks are sloppy but they ensure we don't substring out of bounds
  */
        if (decodePointer + 1 <= s.length()) {
            String firstWay = s.substring(decodePointer, decodePointer + 1); // single character decoding
            if (isValid(firstWay)) {
                totalWaysFromHere += numDecodings(s, decodePointer + 1, previousAnswers);
            }
        }

        if (decodePointer + 2 <= s.length()) {
            String secondWay = s.substring(decodePointer, decodePointer + 2); // 2 character decoding
            if (isValid(secondWay)) {
      /*
        If this is a valid decoding then recurse on it since it is ONE valid way to decode
        a piece of the string off. If it is INVALID we will not factor this way of decoing
        in and the path in the "tree" of recursion is cut short
      */
                totalWaysFromHere += numDecodings(s, decodePointer + 2, previousAnswers);
            }
        }

  /*
    CACHE THE SUBPROBLEM ANSWER. We will need this later when asked more subproblems
  */
        previousAnswers[decodePointer] = totalWaysFromHere;

        return previousAnswers[decodePointer]; // The answer to this subproblem
    }

    /*
      Simple helper function to checks if a substring is a valid
      decoding. This function is probably not totally optimal but eh.
    */
    public boolean isValid(String s) {

        if (s.length() == 0) {
            return false;
        }

        if (s.charAt(0) == '0') {
            return false;
        }

        int value = Integer.parseInt(s);

        return value >= 1 && value <= 26;
    }

    public int numDecodings2(String s) {
        if (s == null || s.isEmpty() || s.charAt(0) == '0') {
            return 0;
        }
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= s.length(); i++) {
            int num = Integer.parseInt(s.substring(i - 2, i));
            int twoStepsBehind = (num <= 26 && num >= 10) ? dp[i - 2] : 0;
            int oneStepBehind = (Integer.parseInt(s.substring(i - 1, i)) != 0) ? dp[i - 1] : 0;
            dp[i] = twoStepsBehind + oneStepBehind; // can reach here by either hopping 2 steps or 1 step
        }

        return dp[s.length()];
    }


}
