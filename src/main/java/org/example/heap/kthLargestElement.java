package org.example.heap;

/**
 * Q: Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * Example 1:
 *
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 */
public class kthLargestElement {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int p = partition(nums, left, right);
            if (p == n - k) {
                return nums[p];
            } else if (p > n - k) {
                right = p - 1;
            } else {
                left = p + 1;
            }
        }
        return -1;
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int pivotIndex = left;
        for (int j = left; j < right; j++) {
            if (nums[j] < pivot) {
                swap(nums, pivotIndex, j);
                pivotIndex++;
            }
        }
        swap(nums, pivotIndex, right);
        return pivotIndex;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        kthLargestElement kthLargestElement = new kthLargestElement();
        int[] nums = {3,2,1,5,6,4};
        int k = 2;
        System.out.println(kthLargestElement.findKthLargest(nums, k));
    }

}
