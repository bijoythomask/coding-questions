# Kth Largest Element in an Array

## Problem Statement

Find the **k**th largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

## Solution Approach

We can solve this problem efficiently using a **min-heap** of size `k`.

1.  **Initialization**: Create a min-heap.

2.  **Iteration**: Iterate through the input array `nums`.
    *   For each element, add it to the min-heap.
    *   If the size of the min-heap becomes greater than `k`, remove the smallest element from the heap (which is the root of the min-heap).

3.  **Result**: After iterating through all the elements in the array, the root of the min-heap will be the `k`th largest element.

This approach has a time complexity of **O(N log k)**, where N is the number of elements in the array, and a space complexity of **O(k)** for the heap.

## Example

Let's say we have the following array and `k`:

`nums = [3, 2, 1, 5, 6, 4]`
`k = 2`

1.  Initialize an empty min-heap.
2.  Iterate through the array:
    *   `3`: heap = `[3]`
    *   `2`: heap = `[2, 3]`
    *   `1`: heap = `[1, 2, 3]`. Heap size is 3 > k. Remove min element (1). heap = `[2, 3]`
    *   `5`: heap = `[2, 3, 5]`. Heap size is 3 > k. Remove min element (2). heap = `[3, 5]`
    *   `6`: heap = `[3, 5, 6]`. Heap size is 3 > k. Remove min element (3). heap = `[5, 6]`
    *   `4`: heap = `[4, 5, 6]`. Heap size is 3 > k. Remove min element (4). heap = `[5, 6]`

3.  The root of the heap is `5`. So, the 2nd largest element is `5`.
