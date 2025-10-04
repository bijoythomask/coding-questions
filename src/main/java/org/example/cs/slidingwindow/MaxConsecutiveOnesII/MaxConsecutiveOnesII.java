package org.example.cs.slidingwindow;

public class MaxConsecutiveOnesII {
    public int findMaxConsecutiveOnes(int[] nums) {
        int left = 0, right = 0, zeroCount = 0, maxLen = 0;
        while (right < nums.length) {
            if (nums[right] == 0) zeroCount++;
            while (zeroCount > 1) {
                if (nums[left] == 0) zeroCount--;
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
    }
    public static void main(String[] args) {
        MaxConsecutiveOnesII solver = new MaxConsecutiveOnesII();
        int[] nums = {1,0,1,1,0};
        System.out.println(solver.findMaxConsecutiveOnes(nums)); // 4
    }
}
