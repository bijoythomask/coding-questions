# Kth Largest Element in a Stream

## Problem Statement
Design a class to find the `k`-th largest element in a stream of numbers. Note that it is the `k`-th largest element in the sorted order, not the `k`-th distinct element.

Implement the `KthLargest` class:
- `KthLargest(int k, int[] nums)`: Initializes the object with the integer `k` and the stream of integers `nums`.
- `int add(int val)`: Appends the integer `val` to the stream and returns the element representing the `k`-th largest element in the current stream.

## Solution Approach: Min-Heap of Size K
The key to solving this problem efficiently is to maintain a data structure that holds the `k` largest elements seen in the stream so far. A **min-heap** of a fixed size `k` is perfect for this.

The heap will store the `k` largest numbers, with the smallest of them (which is the k-th largest overall) at the root, ready for quick access.

### Class Design
-   **Properties**:
    -   A `PriorityQueue<Integer>` named `minHeap` to store the `k` largest elements.
    -   An integer `k` to store the desired rank.

-   **Constructor `KthLargest(int k, int[] nums)`**:
    -   Initialize the `k` property.
    -   Initialize the `minHeap`.
    -   Iterate through the initial `nums` array and call the `add` method for each number to populate the heap correctly.

-   **Method `int add(int val)`**:
    -   Add the new `val` to the `minHeap`.
    -   After adding, if the size of the heap is greater than `k`, `poll()` the smallest element. This ensures the heap's size is maintained at `k`.
    -   The root of the heap (`minHeap.peek()`) is now the `k`-th largest element in the stream. Return this value.

## Complexity Analysis
-   **Constructor**: O(N log K), where N is the number of initial elements in `nums` and K is the target rank. Each of the N initial elements requires an `add` operation.
-   **`add` method**: O(log K). Each call to `add` involves one `offer` and possibly one `poll` operation on the heap of size K.
-   **Space Complexity:** O(K) to store the `k` largest elements in the heap.

## Example
`KthLargest(3, [4, 5, 8, 2])`
`k = 3`

1.  **Constructor**:
    *   `add(4)`: heap = `{4}`
    *   `add(5)`: heap = `{4, 5}`
    *   `add(8)`: heap = `{4, 5, 8}`
    *   `add(2)`: heap = `{2, 4, 5, 8}`. Size > 3, poll `2`. Heap = `{4, 5, 8}`.
    *   Initial state is set. The 3rd largest is `4`.

2.  `add(3)`:
    *   Offer `3`. Heap: `{3, 4, 5, 8}`.
    *   Size > 3, poll `3`. Heap: `{4, 5, 8}`.
    *   Return `peek()`: **4**.

3.  `add(5)`:
    *   Offer `5`. Heap: `{4, 5, 5, 8}`.
    *   Size > 3, poll `4`. Heap: `{5, 5, 8}`.
    *   Return `peek()`: **5**.

4.  `add(10)`:
    *   Offer `10`. Heap: `{5, 5, 8, 10}`.
    *   Size > 3, poll `5`. Heap: `{5, 8, 10}`.
    *   Return `peek()`: **5**.

5.  `add(9)`:
    *   Offer `9`. Heap: `{5, 8, 9, 10}`.
    *   Size > 3, poll `5`. Heap: `{8, 9, 10}`.
    *   Return `peek()`: **8**.
