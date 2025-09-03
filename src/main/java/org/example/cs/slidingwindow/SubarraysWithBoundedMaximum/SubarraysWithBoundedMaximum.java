package org.example.cs.slidingwindow;

public class SubarraysWithBoundedMaximum {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        return count(nums, right) - count(nums, left - 1);
    }
    private int count(int[] nums, int bound) {
        int ans = 0, cur = 0;
        for (int x : nums) {
            cur = x <= bound ? cur + 1 : 0;
            ans += cur;
        }
        return ans;
    }
    public static void main(String[] args) {
        SubarraysWithBoundedMaximum solver = new SubarraysWithBoundedMaximum();
        int[] nums = {2,1,4,3};
        System.out.println(solver.numSubarrayBoundedMax(nums, 2, 3)); // 3
    }
}
