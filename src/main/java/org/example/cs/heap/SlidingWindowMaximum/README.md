# Sliding Window Maximum

## Problem Statement
You are given an array of integers `nums`, there is a sliding window of size `k` which is moving from the very left of the array to the very right. You can only see the `k` numbers in the window. Each time the sliding window moves right by one position.

Return the *maximum* sliding window.

## Solution Approach: Deque (Double-Ended Queue)
This problem can be solved efficiently using a **Deque (double-ended queue)**. The deque will store indices of elements from `nums` and maintain them in **decreasing order** of their corresponding values. This way, the front of the deque (`deque.peek()`) will always hold the index of the maximum element in the current window.

### Algorithm
1.  **Initialization**:
    *   Initialize an empty `Deque<Integer>`.
    *   Initialize an `int[] result` array to store the maximums for each window.

2.  **Iterate Through `nums`**:
    *   For each element `nums[i]` at index `i`:
    *   **Remove out-of-window elements**: If the index at the front of the deque (`deque.peek()`) is outside the current window (i.e., `deque.peek() == i - k`), remove it from the front (`deque.poll()`).
    *   **Maintain decreasing order**: While the deque is not empty and the element at the back of the deque (`nums[deque.peekLast()]`) is less than the current element `nums[i]`, remove elements from the back (`deque.pollLast()`). This ensures that the deque only contains indices of elements that are potentially maximums and are in decreasing order.
    *   **Add current element**: Add the current index `i` to the back of the deque (`deque.offer(i)`).
    *   **Record maximum**: If the window has fully formed (i.e., `i >= k - 1`), the maximum element for the current window is `nums[deque.peek()]`. Store this in the `result` array at the appropriate index (`result[i - k + 1]`).

3.  **Return Result**: After iterating through all elements, return the `result` array.

## Complexity Analysis
-   **Time Complexity:** O(N), where N is the number of elements in `nums`. Although there are nested loops, each element is added to and removed from the deque at most once.
-   **Space Complexity:** O(K) for the deque, as it will store at most `k` elements.

## Example
`nums = [1, 3, -1, -3, 5, 3, 6, 7]`, `k = 3`

`result` array size: `8 - 3 + 1 = 6`

| `i` | `nums[i]` | Deque (indices) | `deque.peek()` (max index) | `nums[deque.peek()]` (max value) | `result` |
| :-- | :-------- | :-------------- | :------------------------- | :------------------------------- | :------- |
| 0   | 1         | `[0]`           |                            |                                  |          |
| 1   | 3         | `[1]`           |                            |                                  |          |
| 2   | -1        | `[1, 2]`        | 1                          | 3                                | `[3]`    |
| 3   | -3        | `[1, 2, 3]`     | 1                          | 3                                | `[3, 3]` |
| 4   | 5         | `[4]`           | 4                          | 5                                | `[3, 3, 5]` |
| 5   | 3         | `[4, 5]`        | 4                          | 5                                | `[3, 3, 5, 5]` |
| 6   | 6         | `[6]`           | 6                          | 6                                | `[3, 3, 5, 5, 6]` |
| 7   | 7         | `[7]`           | 7                          | 7                                | `[3, 3, 5, 5, 6, 7]` |

Final `result`: `[3, 3, 5, 5, 6, 7]`

## Alternate Approach: Max-Heap (Priority Queue)
A less efficient but still valid approach uses a **max-heap (PriorityQueue)**.

1.  **Data Structure**: Store pairs `[value, index]` in a max-heap, ordered by `value` in descending order.
2.  **Algorithm**:
    *   Iterate `i` from `0` to `n-1`.
    *   Add `[nums[i], i]` to the max-heap.
    *   Before getting the maximum for the current window, remove any elements from the heap whose indices are outside the current window (`heap.peek().index <= i - k`).
    *   Once the window is formed (`i >= k - 1`), the maximum for the current window is `heap.peek().value`.

### Complexity
-   **Time Complexity:** O(N log K). Each element is added to the heap once (O(log K)), and potentially removed once (O(log K)).
-   **Space Complexity:** O(K) for the heap.

This approach is less optimal than the deque method because heap operations take logarithmic time, whereas deque operations (amortized) take constant time.
