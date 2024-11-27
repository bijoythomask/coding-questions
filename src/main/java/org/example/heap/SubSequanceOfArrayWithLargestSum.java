package org.example.heap;

/**
 * You are given an integer array nums and an integer k. You want to find a subsequence of nums of length k that has the largest sum.
 *
 * Return any such subsequence as an integer array of length k.
 *
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,1,3,3], k = 2
 * Output: [3,3]
 * Explanation:
 * The subsequence has the largest sum of 3 + 3 = 6.
 * Example 2:
 *
 * Input: nums = [-1,-2,3,4], k = 3
 * Output: [-1,3,4]
 * Explanation:
 * The subsequence has the largest sum of -1 + 3 + 4 = 6.
 * Example 3:
 *
 * Input: nums = [3,4,3,3], k = 2
 * Output: [3,4]
 * Explanation:
 * The subsequence has the largest sum of 3 + 4 = 7.
 * Another possible subsequence is [4, 3].
 */
public class SubSequanceOfArrayWithLargestSum {
    public static void main(String[] args) {
        SubSequanceOfArrayWithLargestSum subSequanceOfArrayWithLargestSum = new SubSequanceOfArrayWithLargestSum();
        int[] nums = {2, 1, 3, 3};
        int k = 2;
        int[] result = subSequanceOfArrayWithLargestSum.largestSubsequence(nums, k);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    public int[] largestSubsequence(int[] nums, int k) {
        int[] result = new int[k];
        int[] count = new int[100];
        for (int num : nums) {
            count[num]++;
        }
        int index = 0;
        for (int i = 99; i >= 0; i--) {
            while (count[i] > 0 && k > 0) {
                result[index++] = i;
                count[i]--;
                k--;
            }
        }
        return result;
    }
}
