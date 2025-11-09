package org.example.cs.twopointers.RemoveDuplicatesfromSortedArray;

public class RemoveDuplicatesfromSortedArray {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // write_pointer will track the position for the next unique element.
        // The first element nums[0] is always unique, so we start write_pointer at 1.
        int writePointer = 1;

        // read_pointer iterates through the array from the second element.
        for (int readPointer = 1; readPointer < nums.length; readPointer++) {
            // If the current element (nums[readPointer]) is different from the element
            // just before the writePointer (nums[writePointer - 1]),
            // it means we found a new unique element.
            if (nums[readPointer] != nums[writePointer - 1]) {
                // Place this unique element at the position indicated by writePointer.
                nums[writePointer] = nums[readPointer];
                // Move writePointer to the next available position for a unique element.
                writePointer++;
            }
            // If nums[readPointer] is a duplicate, we simply skip it.
            // writePointer does not advance, effectively "removing" the duplicate
            // by overwriting it later with a unique element.
        }

        // The final value of writePointer is the count of unique elements, k.
        return writePointer;
    }

    public static void main(String[] args) {
        RemoveDuplicatesfromSortedArray solver = new RemoveDuplicatesfromSortedArray();

        // Example 1
        int[] nums1 = {1, 1, 2};
        int k1 = solver.removeDuplicates(nums1);
        System.out.println("Example 1: k = " + k1 + ", nums = ");
        for (int i = 0; i < k1; i++) {
            System.out.print(nums1[i] + " ");
        }
        System.out.println(); // Expected output: k = 2, nums = 1 2

        // Example 2
        int[] nums2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int k2 = solver.removeDuplicates(nums2);
        System.out.println("Example 2: k = " + k2 + ", nums = ");
        for (int i = 0; i < k2; i++) {
            System.out.print(nums2[i] + " ");
        }
        System.out.println(); // Expected output: k = 5, nums = 0 1 2 3 4
    }
}
