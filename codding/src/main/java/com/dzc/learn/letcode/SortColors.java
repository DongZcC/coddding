package com.dzc.learn.letcode;

public class SortColors {
    public void sortColors(int[] nums) {
        int begin = 0;
        int end = nums.length - 1;
        int current = 0;
        while (current <= end) {
            if (nums[current] == 0) {
                swap(nums, current, begin);
                begin++;
                current++;
            } else if (nums[current] == 1) {
                current++;
            } else {
                swap(nums, current, end);
                end--;
            }
        }
    }

    private void swap(int[] nums, int begin, int end) {
        int temp = nums[begin];
        nums[begin] = nums[end];
        nums[end] = temp;
    }

    public static void main(String[] args) {
//        float sum = 0f;
//
//        for (int j = 0; j < 100; j++) {
//            sum += 0.1;
//        }
//        System.out.println(sum);

        float data = .75f;
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(data)));
    }

}
