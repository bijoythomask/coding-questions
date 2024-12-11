package org.example.cs.slidingwindow;

/**
 * Given a sorted array, two integers k and x, find the k closest elements to x in the array.
 * The result should also be sorted in ascending order.
 * If there is a tie, the smaller elements are always preferred.
 *
 * Example 1:
 * Input: [1, 2, 3, 4, 5], k=4, x=3
 * Output: [1, 2, 3, 4]
 *
 * Example 2:
 * Input: [1, 2, 3, 4, 5], k=4, x=-1
 * Output: [1, 2, 3, 4]
 */
public class FindKClosestElements {

        public static void main(String[] args) {
            int[] arr = {1, 2, 3, 4, 5};
            int k = 4;
            int x = 3;
            System.out.println(findKClosestElements(arr, k, x)); // Output: [1, 2, 3, 4]

            arr = new int[]{1, 2, 3, 4, 5};
            k = 4;
            x = -1;
            System.out.println(findKClosestElements(arr, k, x)); // Output: [1, 2, 3, 4]
        }

        public static int[] findKClosestElements(int[] arr, int k, int x) {
            int left = 0;
            int right = arr.length - k;

            while (left < right) {
                int mid = left + (right - left) / 2;
                if (x - arr[mid] > arr[mid + k] - x) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            int[] result = new int[k];
            for (int i = 0; i < k; i++) {
                result[i] = arr[left + i];
            }

            return result;
        }
}
