package com.dzc.learn.letcode;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GrayCode {

    public List<Integer> grayCode(int n) {
        return IntStream.range(0, (int) Math.pow(2, n))
                .map(i -> (i >> 1) ^ i)
                .boxed()
                .collect(Collectors.toList());
    }


    public static void main(String[] args) {
        GrayCode gc = new GrayCode();
        gc.grayCode(3);
    }
}
