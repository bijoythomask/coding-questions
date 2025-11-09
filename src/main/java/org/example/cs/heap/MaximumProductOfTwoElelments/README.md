# Maximum Product of Two Elements

## Problem Statement
Given the array of integers `nums`, you will choose two different indices `i` and `j` of that array.

Return the maximum value of `(nums[i]-1)*(nums[j]-1)`.

## Solution Approach: Finding the Two Largest Elements
To maximize the product `(nums[i]-1)*(nums[j]-1)`, we need to choose the two largest numbers in the `nums` array. This can be done efficiently by iterating through the array once.

1.  **Initialization**:
    *   Initialize `max1` to `Integer.MIN_VALUE` to store the largest number found so far.
    *   Initialize `max2` to `Integer.MIN_VALUE` to store the second largest number found so far.

2.  **Single Pass Iteration**:
    *   Iterate through each `num` in the `nums` array.
    *   If `num` is greater than `max1`:
        *   Update `max2` to the current value of `max1`.
        *   Update `max1` to `num`.
    *   Else if `num` is greater than `max2` (but not `max1`):
        *   Update `max2` to `num`.

3.  **Calculate Result**: After iterating through the entire array, `max1` will hold the largest number and `max2` will hold the second largest number. The result is then `(max1 - 1) * (max2 - 1)`.

## Complexity Analysis
-   **Time Complexity:** O(N), where N is the number of elements in `nums`. We iterate through the array only once.
-   **Space Complexity:** O(1), as we only use a few variables to store `max1` and `max2`.

## Example
`nums = [3, 4, 5, 2]`

1.  **Initialization**: `max1 = Integer.MIN_VALUE`, `max2 = Integer.MIN_VALUE`.
2.  **Iteration**:
    *   `num = 3`: `max1 = 3`, `max2 = Integer.MIN_VALUE`.
    *   `num = 4`: `max1 = 4`, `max2 = 3`.
    *   `num = 5`: `max1 = 5`, `max2 = 4`.
    *   `num = 2`: `2` is not greater than `max1` (5) or `max2` (4). No change.
3.  **Result**: `(max1 - 1) * (max2 - 1) = (5 - 1) * (4 - 1) = 4 * 3 = 12`.

## Alternate Approach: Max-Heap
While the linear scan is more efficient for this specific problem, a max-heap could also be used to find the two largest elements.

1.  **Populate Heap**: Insert all elements from `nums` into a max-heap.
2.  **Extract Two Largest**: `poll()` the largest element (`max1`) and then `poll()` the second largest element (`max2`).
3.  **Calculate Result**: Return `(max1 - 1) * (max2 - 1)`.

### Complexity
-   **Time Complexity:** O(N log N) to build the heap, or O(N) if using heapify. Then O(log N) for each poll. Overall, O(N log N).
-   **Space Complexity:** O(N) for the heap.

This approach is less optimal than the linear scan for this problem but demonstrates the use of a heap for finding largest elements.
