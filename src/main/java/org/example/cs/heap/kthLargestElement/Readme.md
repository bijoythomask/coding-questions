# Kth Largest Element in an Array

## Problem Statement

Find the **k**th largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

## Solution Approach: Min-Heap
We can solve this problem efficiently using a **min-heap** of size `k`. The heap will store the `k` largest elements encountered so far, with the smallest of them at the top.

1.  **Initialization**: Create a min-heap (PriorityQueue).

2.  **Iteration**: Iterate through the input array `nums`.
    *   For each element, add it to the min-heap.
    *   If the size of the min-heap becomes greater than `k`, `poll()` the smallest element from the heap. This ensures the heap never holds more than the `k` largest elements.

3.  **Result**: After iterating through all the elements, the heap contains the `k` largest elements of the array. The root of the min-heap (`heap.peek()`) is the smallest of these, which is exactly the `k`-th largest element overall.

This approach has a time complexity of **O(N log k)**, where N is the number of elements in the array, and a space complexity of **O(k)** for the heap.

## Example

`nums = [3, 2, 1, 5, 6, 4]`, `k = 2`

1.  Initialize an empty min-heap.
2.  Iterate through the array:
    *   `3`: heap = `{3}`
    *   `2`: heap = `{2, 3}`
    *   `1`: heap = `{1, 2, 3}`. Size > 2, poll `1`. Heap = `{2, 3}`.
    *   `5`: heap = `{2, 3, 5}`. Size > 2, poll `2`. Heap = `{3, 5}`.
    *   `6`: heap = `{3, 5, 6}`. Size > 2, poll `3`. Heap = `{5, 6}`.
    *   `4`: heap = `{4, 5, 6}`. Size > 2, poll `4`. Heap = `{5, 6}`.

3.  The root of the heap is `5`. The 2nd largest element is `5`.

## Alternate Approach: Quickselect
A more optimized approach is the **Quickselect** algorithm, which is a modification of the QuickSort algorithm.

1.  **Partition**: The core of Quickselect is a `partition` function that picks a pivot element and rearranges the array such that all elements smaller than the pivot are to its left, and all elements larger are to its right. The function returns the final index of the pivot.
2.  **Recursive Selection**:
    *   After partitioning, if the pivot's index is the same as the target index (`n-k`), then the pivot is the kth largest element.
    *   If the pivot's index is less than `n-k`, it means the kth largest element is in the right subarray, so we recurse on the right part.
    *   If the pivot's index is greater than `n-k`, we recurse on the left part.

### Complexity
-   **Time Complexity:** O(N) on average, O(N^2) in the worst case. The worst case can be avoided by using a random pivot.
-   **Space Complexity:** O(1) (if implemented in-place).

Quickselect is generally faster than the heap-based approach but can be more complex to implement correctly.
