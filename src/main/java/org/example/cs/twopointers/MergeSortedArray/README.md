# Merge Sorted Array

## Problem Statement
You are given two integer arrays `nums1` and `nums2`, sorted in non-decreasing order, and two integers `m` and `n`, representing the number of elements in `nums1` and `nums2` respectively.

Merge `nums1` and `nums2` into a single array sorted in non-decreasing order.

The final sorted array should not be returned by the function, but instead be stored inside the array `nums1`. To accommodate this, `nums1` has a length of `m + n`, where the first `m` elements denote the elements that should be merged, and the last `n` elements are set to `0` and should be ignored. `nums2` has a length of `n`.

## Solution Approach: Two Pointers (Merge from End)
This problem is a classic two-pointer problem that requires merging two sorted arrays in-place. The key insight for an in-place solution is to start merging from the end of `nums1` to avoid overwriting elements that haven't been processed yet.

1.  **Initialization**:
    *   Initialize `i` to `m - 1` (pointer to the last element of the actual `nums1` data).
    *   Initialize `j` to `n - 1` (pointer to the last element of `nums2`).
    *   Initialize `k` to `m + n - 1` (pointer to the last position in the merged `nums1` array).

2.  **Merge Loop**:
    *   Loop while both `i` and `j` are non-negative (meaning there are still elements in both `nums1` and `nums2` to compare):
        *   If `nums1[i]` is greater than `nums2[j]`:
            *   Place `nums1[i]` at `nums1[k]`.
            *   Decrement `i` and `k`.
        *   Else (`nums2[j]` is greater than or equal to `nums1[i]`):
            *   Place `nums2[j]` at `nums1[k]`.
            *   Decrement `j` and `k`.

3.  **Handle Remaining Elements**:
    *   After the main loop, one of the arrays might still have remaining elements. Since `nums1`'s elements are already in their correct relative positions (or have been moved), we only need to consider `nums2`.
    *   Loop while `j` is non-negative:
        *   Place `nums2[j]` at `nums1[k]`.
        *   Decrement `j` and `k`.

## Complexity Analysis
-   **Time Complexity:** O(M + N), where M and N are the number of elements in `nums1` and `nums2` respectively. Each element is compared and placed once.
-   **Space Complexity:** O(1), as the merging is done in-place without using any extra data structures.

## Example
`nums1 = [1, 2, 3, 0, 0, 0]`, `m = 3`
`nums2 = [2, 5, 6]`, `n = 3`

1.  **Initialization**: `i = 2`, `j = 2`, `k = 5`.
2.  **Merge Loop**:
    *   `nums1[2] = 3`, `nums2[2] = 6`. `6 > 3`.
        *   `nums1[5] = 6`. `k = 4`, `j = 1`. `nums1 = [1, 2, 3, 0, 0, 6]`
    *   `nums1[2] = 3`, `nums2[1] = 5`. `5 > 3`.
        *   `nums1[4] = 5`. `k = 3`, `j = 0`. `nums1 = [1, 2, 3, 0, 5, 6]`
    *   `nums1[2] = 3`, `nums2[0] = 2`. `3 > 2`.
        *   `nums1[3] = 3`. `k = 2`, `i = 1`. `nums1 = [1, 2, 3, 3, 5, 6]`
    *   `j` is now `-1`. Loop terminates.

3.  **Handle Remaining**: `j` is `-1`, so no elements left in `nums2`.

4.  **Final `nums1`**: `[1, 2, 3, 3, 5, 6]`

## Alternate Approach: Using an Auxiliary Array
A simpler approach, though not in-place, involves creating a new auxiliary array to store the merged result and then copying it back to `nums1`.

1.  **Create Auxiliary Array**: Create a new array `merged_array` of size `m + n`.
2.  **Merge**: Use two pointers (`ptr1` for `nums1`, `ptr2` for `nums2`) to fill `merged_array` by comparing elements from `nums1` and `nums2`.
3.  **Copy Back**: Copy all elements from `merged_array` back into `nums1`.

### Complexity
-   **Time Complexity:** O(M + N) for merging and copying.
-   **Space Complexity:** O(M + N) for the auxiliary array.

This approach is easier to implement but uses additional space.
