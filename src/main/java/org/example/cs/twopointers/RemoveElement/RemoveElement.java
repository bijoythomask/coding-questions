package org.example.cs.twopointers;

public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // writePointer will track the position for the next non-val element.
        int writePointer = 0;

        // readPointer iterates through the array.
        for (int readPointer = 0; readPointer < nums.length; readPointer++) {
            // If the current element (nums[readPointer]) is NOT equal to val,
            // it means we want to keep this element.
            if (nums[readPointer] != val) {
                // Place this non-val element at the position indicated by writePointer.
                nums[writePointer] = nums[readPointer];
                // Move writePointer to the next available position for a non-val element.
                writePointer++;
            }
            // If nums[readPointer] IS equal to val, we simply skip it.
            // writePointer does not advance, effectively "removing" the element
            // by overwriting it later with a non-val element.
        }

        // The final value of writePointer is the count of elements not equal to val, k.
        return writePointer;
    }

    public static void main(String[] args) {
        RemoveElement solver = new RemoveElement();

        // Example 1
        int[] nums1 = {3, 2, 2, 3};
        int val1 = 3;
        int k1 = solver.removeElement(nums1, val1);
        System.out.println("Example 1: k = " + k1 + ", nums = ");
        for (int i = 0; i < k1; i++) {
            System.out.print(nums1[i] + " ");
        }
        System.out.println(); // Expected output: k = 2, nums = 2 2

        // Example 2
        int[] nums2 = {0, 1, 2, 2, 3, 0, 4, 2};
        int val2 = 2;
        int k2 = solver.removeElement(nums2, val2);
        System.out.println("Example 2: k = " + k2 + ", nums = ");
        for (int i = 0; i < k2; i++) {
            System.out.print(nums2[i] + " ");
        }
        System.out.println(); // Expected output: k = 5, nums = 0 1 3 0 4
    }
}
