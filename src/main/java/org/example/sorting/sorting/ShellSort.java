package org.example.sorting.sorting;

/**
 * Shell Sort is an in-place comparison sort. It can be seen as either a generalization of sorting by exchange (bubble sort) or sorting by insertion (insertion sort).
 * The method starts by sorting pairs of elements far apart from each other, then progressively reducing the gap between elements to be compared.
 * Starting with far apart elements, it can move some out-of-place elements into position faster than a simple nearest neighbor exchange.
 * The running time of Shellsort is heavily dependent on the gap sequence it uses. For many practical variants, determining their time complexity remains an open problem.
 * In this implementation, we use the gap sequence proposed by Donald Shell, which is n/2, n/4, ..., 1.
 * The worst-case time complexity of this algorithm is O(n^2), but its average-case time complexity is O(n log n).
 * The worst-case space complexity is O(1).
 * Shell sort is not a stable sort.
 * The algorithm is named after its inventor, Donald Shell, who published the algorithm in 1959.
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 6, 1, 3, 4};
        shellSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    private static void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n/2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }
}
