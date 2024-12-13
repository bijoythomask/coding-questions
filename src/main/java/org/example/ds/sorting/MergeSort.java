package org.example.ds.sorting;

/**
 * Merge Sort is an efficient, stable, comparison-based, divide and conquer sorting algorithm.
 * Most implementations produce a stable sort, meaning that the implementation preserves the input order of equal elements in the sorted output.
 * Merge Sort is a comparison sort, i.e. it can be used to sort items that are ordered by a comparison operation.
 * Merge Sort is a divide and conquer algorithm that was invented by John von Neumann in 1945.
 * A divide and conquer algorithm works by recursively breaking down a problem into two or more sub-problems of the same or related type, until these become simple enough to be solved directly.
 * The solutions to the sub-problems are then combined to give a solution to the original problem.
 * Merge Sort first divides the array into equal halves and then combines them in a sorted manner.
 * The worst-case time complexity of this algorithm is O(n log n), but its average-case time complexity is O(n log n).
 * The worst-case space complexity is O(n).
 * Merge sort is a stable sort, which means that the implementation preserves the input order of equal elements in the sorted output.
 * Merge sort is also a comparison sort, which means that it can sort items of any type for which a less-than relation is defined.
 * Merge sort is also a stable sort, which means that it preserves the input order of equal elements in the sorted output.
 * Merge sort is also an adaptive sort, which means that it is more efficient for larger datasets.
 * Merge sort is also a general-purpose sort, which means that it can sort any type of data for which a less-than relation is defined.
 *
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 6, 1, 3, 4};
        mergeSort(arr, 0, arr.length-1);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    private static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l+r)/2;
            mergeSort(arr, l, m);
            mergeSort(arr, m+1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int L[] = new int [n1];
        int R[] = new int [n2];
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }

    }


}
