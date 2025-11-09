# Sum of All k-length Subarrays

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
