package org.example.cs.heap.SubSequanceOfArrayWithLargestSum;

public class SubSequanceOfArrayWithLargestSum {
    public int[] largestSubsequence(int[] nums, int k) {
        int[] result = new int[k];
        int[] count = new int[100];
        for (int num : nums) count[num]++;
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
    public static void main(String[] args) {
        SubSequanceOfArrayWithLargestSum solver = new SubSequanceOfArrayWithLargestSum();
        int[] nums = {2, 1, 3, 3};
        int k = 2;
        int[] result = solver.largestSubsequence(nums, k);
        for (int i : result) System.out.print(i + " "); // Output: 3 3
    }
}
