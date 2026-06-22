# Subsequence of Array with Largest Sum

## Problem Statement
You are given an integer array `nums` and an integer `k`.

Return a subsequence of `nums` of length `k` that has the largest sum.

A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

## Solution Approach: Min-Heap (Priority Queue)
To find the subsequence of length `k` with the largest sum, we need to find the `k` largest elements in the array. A **min-heap** of size `k` is an excellent tool for this.

However, the problem requires returning a subsequence, which means the relative order of the elements must be preserved. This adds a layer of complexity. A better approach is to find the `k` largest elements and then reconstruct the subsequence in the correct order.

1.  **Find the K Largest Elements**:
    *   Use a `HashMap` to count the frequency of each number in the `nums` array.
    *   Use a **min-heap** of size `k` to find the `k` largest numbers.
        *   Iterate through the numbers in the frequency map.
        *   Add each number to the min-heap.
        *   If the heap size exceeds `k`, `poll()` the smallest number.
    *   After this, the heap contains the `k` numbers that must be in our result subsequence.

2.  **Reconstruct the Subsequence**:
    *   Create a frequency map (e.g., `HashMap`) of the `k` largest numbers from the heap.
    *   Initialize an empty `result` array of size `k`.
    *   Iterate through the original `nums` array. For each `num`:
        *   Check if `num` is in our frequency map of `k` largest numbers.
        *   If it is, add `num` to our `result` array and decrement its count in the frequency map.
    *   The `result` array will now contain the `k` largest numbers in their original relative order.

## Complexity Analysis
-   **Time Complexity:** O(N log K + N) = O(N log K), where N is the number of elements in `nums`.
    -   Finding the `k` largest elements using a heap takes O(N log K).
    -   Reconstructing the subsequence takes O(N).
-   **Space Complexity:** O(N) for the initial frequency map and O(K) for the heap and the map of top k elements.

## Example
`nums = [2, 1, 3, 3]`, `k = 2`

1.  **Find K Largest**:
    *   A min-heap of size 2 will end up with `{3, 3}`.
2.  **Reconstruct**:
    *   Frequency map of top 2: `{3: 2}`.
    *   Iterate through `nums = [2, 1, 3, 3]`:
        *   `num = 2`: Not in map.
        *   `num = 1`: Not in map.
        *   `num = 3`: In map. Add `3` to result. Decrement count for `3` to `1`. `result = [3]`.
        *   `num = 3`: In map. Add `3` to result. Decrement count for `3` to `0`. `result = [3, 3]`.
3.  **Final Result**: `[3, 3]`

## Alternate Approach: Counting Sort (as implemented in the provided code)
The provided Java solution uses a counting sort approach, which is very efficient if the range of numbers is small and known.

1.  **Count Frequencies**: Create a `count` array to store the frequency of each number. The code assumes numbers are in the range `[0, 99]`.
2.  **Extract Largest**: Iterate from the largest possible number (99) down to 0.
3.  For each number `i`, while its count is greater than 0 and we still need elements (`k > 0`), add `i` to the result, decrement its count, and decrement `k`.

### Complexity of Alternate Approach
-   **Time Complexity:** O(N + M), where N is the number of elements and M is the range of possible values (100 in this case).
-   **Space Complexity:** O(M) for the count array.

This approach is very fast but is not general. It fails if the numbers in `nums` are negative or larger than the assumed range. The heap-based solution is more robust.
