package org.example.slidingwindow;
/**
 * Given a binary array nums, return the maximum number of consecutive 1's in the array if you can flip at most one 0.
 *
 * Example 1:
 *
 * Input: nums = [1,0,1,1,0]
 * Output: 4
 * Explanation: Flip the first zero will get the maximum number of consecutive 1s.
 * After flipping, the maximum number of consecutive 1s is 4.
 * Example 2:
 *
 * Input: nums = [1,0,1,1,0,1]
 * Output: 4
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1.
 */
public class MaxConsecutiveOnesII {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int zeroCount = 0;
        int start = 0;
        for (int end = 0; end < nums.length; end++) {
            if (nums[end] == 0) {
                zeroCount++;
            }
            while (zeroCount > 1) {
                if (nums[start] == 0) {
                    zeroCount--;
                }
                start++;
            }
            max = Math.max(max, end - start + 1);
        }
        return max;
    }
}
