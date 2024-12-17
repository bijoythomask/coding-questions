package org.example.cs.slidingwindow;

/**
 * Given an integer array nums and two integers left and right, return the number of contiguous non-empty sub arrays
 * such that the value of the maximum array element in that subarray is in the range [left, right].
 *
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 *
 */
public class SubarraysWithBoundedMaximum {

    public static int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int count = 0;
        int start = -1;
        int end = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > right) {
                start = end = i;
                continue;
            }
            if (nums[i] >= left) {
                end = i;
            }
            count += end - start;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(numSubarrayBoundedMax(new int[]{2, 1, 4, 3}, 2, 3));
    }
}
