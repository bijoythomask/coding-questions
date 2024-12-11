package org.example.sorting;
/**
 * Quick Sort is an efficient, in-place, comparison-based sorting algorithm.
 * Quick Sort is a divide and conquer algorithm that was invented by Tony Hoare in 1960.
 * Quick Sort is a comparison sort, i.e. it can be used to sort items that are ordered by a comparison operation.
 * Quick Sort is a divide and conquer algorithm that works by selecting a 'pivot' element from the array and partitioning the other elements into two sub-arrays according to whether they are less than or greater than the pivot.
 * The sub-arrays are then sorted recursively.
 * The worst-case time complexity of this algorithm is O(n^2), but its average-case time complexity is O(n log n).
 * The worst-case space complexity is O(log n).
 * Quick sort is an unstable sort, which means that the implementation does not preserve the input order of equal elements in the sorted output.
 * Quick sort is also a comparison sort, which means that it can sort items of any type for which a less-than relation is defined.
 * Quick sort is also an adaptive sort, which means that it is more efficient for larger datasets.
 * Quick sort is also a general-purpose sort, which means that it can sort any type of data for which a less-than relation is defined.
 *
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 6, 1, 3, 4};
        quickSort(arr, 0, arr.length-1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i+1, high);
        return i+1;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
