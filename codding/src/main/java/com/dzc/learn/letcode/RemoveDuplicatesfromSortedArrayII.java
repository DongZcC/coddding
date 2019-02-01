package com.dzc.learn.letcode;


public class RemoveDuplicatesfromSortedArrayII {

    public int removeDuplicates(int[] nums) {
        if (nums.length < 2)
            return nums.length;
        int begin = 1;
        int end = nums.length - 1;
        int count = 0;
        while (begin <= end) {
            if (nums[begin - 1] == nums[begin]) {
                count++;
                if (count == 2) {
                    // 超过两个了
                    swap(nums, begin, end);
                    count--;
                    end--;
                } else {
                    begin++;
                }
            } else {
                count = 0;
                begin++;
            }
        }
        return end + 1;
    }


    public int removeDuplicates2(int[] nums) {
        if (nums.length < 3)
            return nums.length;

        int slow = 2;
        for (int i = slow; i < nums.length; i++) {
            if (nums[i] > nums[slow - 2]) {
                nums[slow++] = nums[i];
            }
        }

        return slow;
    }


    private void swap(int[] nums, int begin, int end) {
        for (int i = begin; i < end; i++) {
            int temp = nums[i];
            nums[i] = nums[i + 1];
            nums[i + 1] = temp;
        }
    }


    public static void main(String[] args) {
        RemoveDuplicatesfromSortedArrayII r = new RemoveDuplicatesfromSortedArrayII();
        r.removeDuplicates(new int[]{1, 1, 1, 1, 2, 2, 3, 1});
    }


}
