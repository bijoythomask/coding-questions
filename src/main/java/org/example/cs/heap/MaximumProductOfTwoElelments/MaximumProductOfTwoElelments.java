package org.example.cs.heap.MaximumProductOfTwoElelments;

public class MaximumProductOfTwoElelments {
    public int maxProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > max1) {
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max2 = num;
            }
        }
        return (max1 - 1) * (max2 - 1);
    }
    public static void main(String[] args) {
        MaximumProductOfTwoElelments solution = new MaximumProductOfTwoElelments();
        int[] nums = {3, 4, 5, 2};
        System.out.println(solution.maxProduct(nums)); // 12
        nums = new int[]{1, 5, 4, 5};
        System.out.println(solution.maxProduct(nums)); // 16
        nums = new int[]{3, 7};
        System.out.println(solution.maxProduct(nums)); // 12
    }
}
