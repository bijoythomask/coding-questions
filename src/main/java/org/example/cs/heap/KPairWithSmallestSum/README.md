# Find K Pairs with Smallest Sums

## Problem Statement
You are given two integer arrays `nums1` and `nums2` both sorted in ascending order, and an integer `k`.

Find `k` pairs `(u, v)` such that `u` is from `nums1` and `v` is from `nums2` with the smallest sums.

Return an array of these `k` pairs.

## Solution Approach: Min-Heap (Optimized)
This problem is a variation of merging `k` sorted lists, where each "list" is formed by taking an element from `nums1` and pairing it with all elements from `nums2`. A **min-heap** is the most efficient data structure to find the `k` smallest sums.

The key idea is to only explore pairs that have the potential to be among the smallest. We start by considering pairs formed by `nums1[i]` and `nums2[0]` for a limited number of `i`.

1.  **Data Structure**: We use a `PriorityQueue` configured as a min-heap. Each element in the heap will be an array `[sum, index_in_nums1, index_in_nums2]`. The heap will be ordered by `sum`.

2.  **Initialization**:
    *   Create a `List<List<Integer>>` to store the `k` result pairs.
    *   Initialize the min-heap.
    *   For each element `nums1[i]` (where `i` ranges from `0` to `min(nums1.length, k) - 1`), add the pair `[nums1[i] + nums2[0], i, 0]` to the heap. We only need to consider up to `k` elements from `nums1` because if `nums1.length > k`, the `k` smallest sums will likely involve the smaller elements of `nums1`.

3.  **Extract K Smallest Pairs**:
    *   Loop `k` times (or until the heap is empty):
        *   `poll()` the element with the smallest sum from the heap. Let this be `[current_sum, i, j]`.
        *   Add the pair `[nums1[i], nums2[j]]` to our `result` list.
        *   If `j + 1` is a valid index in `nums2` (i.e., `j + 1 < nums2.length`), it means there's a next potential pair from `nums1[i]` with `nums2[j+1]`. `offer()` the new pair `[nums1[i] + nums2[j+1], i, j + 1]` to the heap.

4.  **Return Result**: After the loop, the `result` list will contain the `k` pairs with the smallest sums.

## Complexity Analysis
-   **Time Complexity:** O(k log k).
    *   Initializing the heap takes O(k log k) time (adding `k` elements).
    *   In the main loop, we perform `k` `poll` operations and at most `k` `offer` operations. Each heap operation takes O(log k) time.
-   **Space Complexity:** O(k) to store elements in the heap.

## Example
`nums1 = [1, 7, 11]`, `nums2 = [2, 4, 6]`, `k = 3`

1.  **Initialization**:
    *   Add `[1+2, 0, 0]` to heap: `{[3, 0, 0]}`
    *   Add `[7+2, 1, 0]` to heap: `{[3, 0, 0], [9, 1, 0]}`
    *   Add `[11+2, 2, 0]` to heap: `{[3, 0, 0], [9, 1, 0], [13, 2, 0]}`
    *   Heap: `{[3, 0, 0], [9, 1, 0], [13, 2, 0]}`

2.  **Extract Pairs**:
    *   **1st pair**: Poll `[3, 0, 0]`. Result: `[[1, 2]]`.
        *   `j+1 = 1 < nums2.length`. Offer `[nums1[0] + nums2[1], 0, 1]` which is `[1+4, 0, 1] = [5, 0, 1]`.
        *   Heap: `{[5, 0, 1], [9, 1, 0], [13, 2, 0]}`
    *   **2nd pair**: Poll `[5, 0, 1]`. Result: `[[1, 2], [1, 4]]`.
        *   `j+1 = 2 < nums2.length`. Offer `[nums1[0] + nums2[2], 0, 2]` which is `[1+6, 0, 2] = [7, 0, 2]`.
        *   Heap: `{[7, 0, 2], [9, 1, 0], [13, 2, 0]}`
    *   **3rd pair**: Poll `[7, 0, 2]`. Result: `[[1, 2], [1, 4], [1, 6]]`.
        *   `j+1 = 3 == nums2.length`. Do not offer.
        *   Heap: `{[9, 1, 0], [13, 2, 0]}`

3.  **Final Result**: `[[1, 2], [1, 4], [1, 6]]`

## Alternate Approach: Brute-Force with Heap (as implemented in the provided code)
The provided Java code implements a simpler, but less efficient, heap-based approach.

1.  **Generate Limited Pairs**: It iterates through `nums1` and `nums2` up to `k` elements from each. This means it considers `min(nums1.length, k) * min(nums2.length, k)` pairs.
2.  **Populate Heap**: All these generated pairs `[nums1[i], nums2[j]]` are added to a min-heap, ordered by their sum.
3.  **Extract K Pairs**: Then, `k` pairs are extracted from the heap.

### Complexity of Alternate Approach
-   **Time Complexity:** O(min(N1, k) * min(N2, k) * log(min(N1, k) * min(N2, k))). This can be up to O(k^2 log k) in the worst case, which is less efficient than O(k log k) for larger `k`.
-   **Space Complexity:** O(min(N1, k) * min(N2, k)) for the heap, potentially O(k^2).

This approach is simpler to implement but can be significantly slower and use more memory if `k` is large relative to `nums1.length` and `nums2.length`.
