package org.example.ds.sorting;
/**
 * Counting Sort is an algorithm for sorting a collection of objects according to keys that are small integers; that is, it is an integer sorting algorithm.
 * It operates by counting the number of objects that have each distinct key value, and using arithmetic on those counts to determine the positions of each key value in the output sequence.
 * Its running time is linear in the number of items and the difference between the maximum and minimum key values, so it is only suitable for direct use in situations where the variation in keys is not significantly greater than the number of items.
 * However, it is often used as a subroutine in another sorting algorithm, radix sort, that can handle larger keys more efficiently.
 * Counting sort is a stable sort, meaning that the relative order of equal keys is preserved.
 * Counting sort is not a comparison sort, so it is not subject to the O(n log n) lower bound of comparison sorting algorithms.
 * Counting sort is a linear time sorting algorithm that sort in O(n+k) time when elements are in the range from 1 to k.
 * Counting sort is a sorting technique based on keys between a specific range.
 * It works by counting the number of objects having distinct key values (kind of hashing).
 * Then doing some arithmetic to calculate the position of each object in the output sequence.
 */
public class CountingSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 6, 2, 3, 5};
        countingSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    private static void countingSort(int[] arr) {
        int n = arr.length;
        int[] output = new int[n];
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int[] count = new int[max+1];
        for (int i = 0; i < max; ++i) {
            count[i] = 0;
        }
        for (int j : arr) {
            count[j]++;
        }
        for (int i = 1; i <= max; i++) {
            count[i] += count[i-1];
        }
        for (int i = n-1; i >= 0; i--) {
            output[count[arr[i]]-1] = arr[i];
            count[arr[i]]--;
        }
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }
}
