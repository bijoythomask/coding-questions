# Last Stone Weight

## Problem Statement
You are given an array of integers `stones` where `stones[i]` is the weight of the `i-th` stone.

We are playing a game with the stones. On each turn, we choose the two **heaviest** stones and smash them together. Suppose the heaviest stones have weights `x` and `y` with `x <= y`. The result of this smash is:
- If `x == y`, both stones are destroyed.
- If `x != y`, the stone of weight `x` is destroyed, and the stone of weight `y` has its new weight updated to `y - x`.

The game ends when there is at most one stone left. Return the weight of the last remaining stone. If no stones are left, return `0`.

## Solution Approach: Max-Heap (Priority Queue)
The problem requires us to repeatedly find and operate on the two heaviest stones. A **max-heap** is the ideal data structure for this, as it provides efficient O(log N) access to the largest element.

1.  **Data Structure**: We use a `PriorityQueue` configured as a max-heap to store the weights of the stones.

2.  **Populate the Heap**:
    *   Insert all the stone weights from the input `stones` array into the max-heap.

3.  **Simulate the Game**:
    *   Loop while the heap contains more than one stone (`heap.size() > 1`).
    *   In each iteration, `poll()` the two largest stones from the heap. Let them be `y` (the heaviest) and `x` (the second heaviest).
    *   If `y > x`, calculate the new weight `y - x` and `offer()` it back into the heap.
    *   If `y == x`, both stones are destroyed, so we do nothing.

4.  **Return the Result**:
    *   After the loop, the heap will contain either one stone or be empty.
    *   If the heap is empty, it means all stones were destroyed. Return `0`.
    *   If the heap contains one stone, `poll()` it and return its weight.

## Complexity Analysis
-   **Time Complexity:** O(N log N), where N is the number of stones.
    -   Building the heap from N stones takes O(N) time (if done efficiently) or O(N log N) if adding one by one.
    -   In each step of the simulation, we perform two `poll` operations and at most one `offer` operation, each taking O(log N) time. We do this at most N-1 times.
-   **Space Complexity:** O(N) to store the stones in the priority queue.

## Example
`stones = [2, 7, 4, 1, 8, 1]`

1.  **Heap**: Populate a max-heap. It will be ordered as `{8, 7, 4, 2, 1, 1}`.
2.  **Simulation**:
    *   **Smash 1**: Poll `8` and `7`. `8 - 7 = 1`. Offer `1`. Heap: `{4, 2, 1, 1, 1}`.
    *   **Smash 2**: Poll `4` and `2`. `4 - 2 = 2`. Offer `2`. Heap: `{2, 1, 1, 1}`.
    *   **Smash 3**: Poll `2` and `1`. `2 - 1 = 1`. Offer `1`. Heap: `{1, 1, 1}`.
    *   **Smash 4**: Poll `1` and `1`. They are equal, so both are destroyed. Heap: `{1}`.
3.  **End**: The loop terminates as the heap size is now 1.
4.  **Result**: Return the last stone, which is `1`.

## Alternate Approach: Sorting
A less efficient approach would be to sort the array in each step of the simulation.

1.  Place all stones in a list.
2.  Loop while the list has more than one stone:
    *   Sort the list in ascending order.
    *   Remove the two largest elements (`y` and `x`).
    *   If `y > x`, add `y - x` back to the list.
3.  Return the last element or `0`.

This approach is much slower, with a time complexity of roughly **O(N^2 log N)**, because sorting the list of size `i` takes O(i log i) time, and we do this N-1 times.
