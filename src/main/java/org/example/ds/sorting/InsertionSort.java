package org.example.ds.sorting;

/**
 * Insertion Sort is a simple sorting algorithm that builds the final sorted array (or list) one item at a time.
 * It is much less efficient on large lists than more advanced algorithms such as quicksort, heapsort, or merge sort.
 * However, insertion sort provides several advantages:
 * Simple implementation: Jon Bentley shows a three-line C version, and a five-line optimized version
 * Efficient for (quite) small data sets, much like other quadratic sorting algorithms
 * More efficient in practice than most other simple quadratic (i.e., O(n2)) algorithms such as selection sort or bubble sort
 * Adaptive, i.e., efficient for data sets that are already substantially sorted: the time complexity is O(nk) when each element in the input is no more than k places away from its sorted position
 * Stable; i.e., does not change the relative order of elements with equal keys
 * In-place; i.e., only requires a constant amount O(1) of additional memory space
 *
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 6, 1, 3, 4};
        insertionSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    private static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }
}
