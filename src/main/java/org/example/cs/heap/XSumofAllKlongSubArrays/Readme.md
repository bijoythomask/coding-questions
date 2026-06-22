# Sum of All K-length Subarrays

## Problem Statement

Given an array of integers `nums` and an integer `k`, you need to find the sum of all contiguous subarrays of length `k`. The output should be a list of these sums.

## Solution Approach (Sliding Window)

A naive approach would be to iterate through the array and for each starting index `i`, calculate the sum of the subarray from `i` to `i+k-1`. This would have a time complexity of O(N*k), where N is the number of elements in the array.

A more efficient approach is to use a **sliding window**.

1.  **Initial Window**: Calculate the sum of the first `k` elements. This is the sum of the first subarray.
2.  **Slide the Window**: To get the sum of the next subarray, we can "slide" the window one position to the right by:
    *   Subtracting the element that is leaving the window (from the left).
    *   Adding the new element that is entering the window (from the right).
3.  **Iteration**: We repeat this process until the window has moved through the entire array.

This approach has a time complexity of **O(N)** because we iterate through the array only once. The space complexity is **O(1)** if we don't count the space for the output list.

## Complexity Analysis
-   **Time Complexity:** O(N), where N is the number of elements in the array. We iterate through the array only once.
-   **Space Complexity:** O(1) (excluding the space for the output list).

## Example

`nums = [1, 2, 3, 4, 5, 6], k = 3`

1.  **Initial Window**: `[1, 2, 3]`. Sum = `1 + 2 + 3 = 6`.
    *   Result: `[6]`
2.  **Slide 1**:
    *   Window moves from `[1, 2, 3]` to `[2, 3, 4]`.
    *   Previous sum was `6`.
    *   Subtract the outgoing element: `6 - 1 = 5`.
    *   Add the incoming element: `5 + 4 = 9`.
    *   Result: `[6, 9]`
3.  **Slide 2**:
    *   Window moves from `[2, 3, 4]` to `[3, 4, 5]`.
    *   Previous sum was `9`.
    *   Subtract the outgoing element: `9 - 2 = 7`.
    *   Add the incoming element: `7 + 5 = 12`.
    *   Result: `[6, 9, 12]`
4.  **Slide 3**:
    *   Window moves from `[3, 4, 5]` to `[4, 5, 6]`.
    *   Previous sum was `12`.
    *   Subtract the outgoing element: `12 - 3 = 9`.
    *   Add the incoming element: `9 + 6 = 15`.
    *   Result: `[6, 9, 12, 15]`

The final list of sums is `[6, 9, 12, 15]`.

## Alternate Approach: Brute Force
A brute-force approach would involve iterating through all possible starting positions for a subarray of length `k` and then summing the elements within each subarray.

1.  Initialize an empty list `results`.
2.  Iterate `i` from `0` to `nums.length - k`.
3.  For each `i`, calculate the sum of elements from `nums[i]` to `nums[i + k - 1]`.
4.  Add this sum to `results`.

### Complexity
-   **Time Complexity:** O(N * K). For each of the `N - K + 1` subarrays, we sum `K` elements.
-   **Space Complexity:** O(1) (excluding the space for the output list).

This approach is less efficient than the sliding window method, especially for larger `K`.

## Note on Folder Placement
While the most efficient solution for finding the sum of k-length subarrays is the sliding window technique (which does not typically involve a heap), this problem might be grouped under the 'heap' category for contextual reasons. Many other sliding window problems, such as finding the maximum or minimum element in a sliding window, *do* leverage heaps (or deques) for optimal performance. This problem serves as a good contrast, demonstrating when a simpler O(N) sliding window is sufficient.
