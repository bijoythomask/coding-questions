package org.example.sorting.sorting;

/** what is bubble sort?
 * Bubble Sort is the simplest sorting algorithm that works by repeatedly swapping the adjacent elements if they are in wrong order.
 * It is not suitable for large datasets as its average and worst case complexity are of Ο(n2) where n is the number of items.
 * It is a comparison-based algorithm in which each pair of adjacent elements is compared and the elements are swapped if they are not in order.
 * This algorithm is not suitable for large data sets as its average and worst case complexity are of Ο(n2) where n is the number of items.
 * Bubble sort is stable and adaptive.
 * It is stable because it does not change the relative order of elements with equal keys.
 * It is adaptive because it takes advantage of existing order in its input.
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 6, 1, 3, 4};
        bubbleSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    private static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
