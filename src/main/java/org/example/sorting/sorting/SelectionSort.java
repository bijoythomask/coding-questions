package org.example.sorting.sorting;

/**
 * Selection Sort is an in-place comparison sorting algorithm that divides the input list into two parts: the sublist of items already sorted and the sublist of items remaining to be sorted.
 * It has O(n^2) time complexity (Quadratic), making it inefficient on large lists, and generally performs worse than the similar insertion sort.
 * Selection sort is noted for its simplicity and it has performance advantages over more complicated algorithms in certain situations, particularly where auxiliary memory is limited.
 * The algorithm divides the input list into two parts: the sublist of items already sorted, which is built up from left to right at the front (left) of the list, and the sublist of items remaining to be sorted that occupy the rest of the list.
 * Initially, the sorted sublist is empty and the unsorted sublist is the entire input list.
 * The algorithm proceeds by finding the smallest (or largest, depending on sorting order) element in the unsorted sublist, exchanging (swapping) it with the leftmost unsorted element (putting it in sorted order), and moving the sublist boundaries one element to the right.
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 6, 1, 3, 4};
        selectionSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    private static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
