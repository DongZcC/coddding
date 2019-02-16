package com.dzc.learn.letcode;

/**
 * Description:
 * User: dzczyw
 * Date: 2019-02-03
 * Time: 16:35
 */
public class SearchInRotatedSortedArrayII {

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int minIndex = findMinIndex(nums);
        if (nums[minIndex] == target)
            return true;

        int len = nums.length;
        int start = 0;
        int end = len - 1;
        if (minIndex != 0) {
            start = (nums[len - 1] >= target) ? minIndex + 1 : 0;
            end = (nums[len - 1] >= target) ? len - 1 : minIndex - 1;
        }

        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target)
                return true;
            else if (nums[mid] < target) {
                start = mid + 1;
            } else
                end = mid - 1;
        }
        return false;
    }

    public boolean search2(int[] nums, int target) {
        // note here end is initialized to len instead of (len-1)
        int start = 0, end = nums.length;
        while (start < end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) return true;
            if (nums[mid] > nums[start]) { // nums[start..mid] is sorted
                // check if target in left half
                if (target < nums[mid] && target >= nums[start]) end = mid;
                else start = mid + 1;
            } else if (nums[mid] < nums[start]) { // nums[mid..end] is sorted
                // check if target in right half
                if (target > nums[mid] && target < nums[start]) start = mid + 1;
                else end = mid;
            } else { // have no idea about the array, but we can exclude nums[start] because nums[start] == nums[mid]
                start++;
            }
        }
        return false;
    }

    // 数组中是有重复元素的，因此需要考虑找到最小元素的问题；
    private int findMinIndex(int[] nums) {
        int minIndex = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArrayII searchInRotatedSortedArrayII = new SearchInRotatedSortedArrayII();
        System.out.println(searchInRotatedSortedArrayII.search2(new int[]{
                1, 3, 1, 1, 1
        }, 3));
    }
}
