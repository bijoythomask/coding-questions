# Next Permutation

## Problem Statement
A permutation of an array of integers is an arrangement of its members into a sequence or linear order.

For example, for `arr = [1,2,3]`, the following are all the permutations: `[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], [3,2,1]`.

The **next permutation** of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all permutations of the array are sorted in one container, then the next permutation of that array is the permutation that follows it in the sorted container. If there is no next lexicographically greater permutation, the array must be rearranged in the lowest possible order (i.e., sorted in ascending order).

Given an array of integers `nums`, find the next permutation of `nums`.

The replacement must be [in-place](https://en.wikipedia.org/wiki/In-place_algorithm) and use only constant extra memory.

## Solution Approach: Standard Algorithm
The algorithm to find the next lexicographically greater permutation involves a few key steps:

1.  **Find the First Decreasing Element from Right**:
    *   Start from the second-to-last element (`i = nums.length - 2`) and move leftwards.
    *   Find the first index `i` such that `nums[i] < nums[i + 1]`.
    *   If no such `i` is found, it means the array is sorted in descending order (e.g., `[3, 2, 1]`), which is the largest possible permutation. In this case, we need to reverse the entire array to get the smallest permutation (e.g., `[1, 2, 3]`).

2.  **Find Element to Swap with `nums[i]`**:
    *   If `i` was found (i.e., the array is not fully descending), start from the rightmost element (`j = nums.length - 1`) and move leftwards.
    *   Find the first index `j` such that `nums[j] > nums[i]`. This `nums[j]` will be the smallest element to the right of `i` that is greater than `nums[i]`.

3.  **Swap `nums[i]` and `nums[j]`**:
    *   Swap the elements at indices `i` and `j`. This places a larger element at index `i`, making the permutation lexicographically greater.

4.  **Reverse the Suffix**:
    *   Reverse the subarray starting from `i + 1` up to the end of the array. This ensures that the suffix is in the smallest possible order, thus making the overall permutation the *next* lexicographically greater one.

## Complexity Analysis
-   **Time Complexity:** O(N), where N is the number of elements in `nums`. In the worst case, we might traverse the array up to three times (finding `i`, finding `j`, and reversing).
-   **Space Complexity:** O(1), as the operations are performed in-place.

## Example
`nums = [1, 2, 3]`

1.  **Find `i`**:
    *   `i = 1` (`nums[1] = 2`, `nums[2] = 3`). `2 < 3`. So, `i = 1`.
2.  **Find `j`**:
    *   `j = 2` (`nums[2] = 3`). `3 > nums[1] (2)`. So, `j = 2`.
3.  **Swap `nums[i]` and `nums[j]`**:
    *   Swap `nums[1]` (2) and `nums[2]` (3). `nums` becomes `[1, 3, 2]`.
4.  **Reverse Suffix**:
    *   Reverse subarray from `i + 1 = 2` to end. Subarray is `[2]`. Reversing `[2]` is `[2]`. No change.

Final `nums`: `[1, 3, 2]`

## Alternate Approach: Generating All Permutations
A brute-force approach would be to generate all possible permutations of the given array, sort them lexicographically, and then find the one immediately following the input array.

1.  **Generate All Permutations**: Use a recursive backtracking algorithm to generate every possible permutation of `nums`.
2.  **Sort Permutations**: Store all generated permutations in a list and sort this list lexicographically.
3.  **Find Next**: Locate the input `nums` in the sorted list and return the next permutation. If `nums` is the last permutation, return the first one.

### Complexity
-   **Time Complexity:** O(N! * N) for generating permutations and sorting them. This is highly inefficient.
-   **Space Complexity:** O(N! * N) to store all permutations.

This approach is computationally infeasible for arrays of even moderate size (e.g., N > 10).
