# Find Median in Data Stream

## Problem Statement
The median is the middle value in an ordered integer list. If the size of the list is even, there is no single middle value, and the median is the average of the two middle values.

Implement the `MedianFinder` class:
- `MedianFinder()` initializes the `MedianFinder` object.
- `void addNum(int num)` adds an integer `num` from the data stream to the data structure.
- `double findMedian()` returns the median of all elements so far. Answers within `10^-5` of the actual answer will be accepted.

## Solution Approach: Two Heaps
This problem is a classic application of using two heaps to maintain the median of a dynamically growing set of numbers. The idea is to divide the numbers into two halves: a lower half and an upper half.

1.  **Data Structures**:
    *   A **max-heap (`maxHeap`)** to store the elements of the lower half. The largest element in this heap will be the largest element in the lower half.
    *   A **min-heap (`minHeap`)** to store the elements of the upper half. The smallest element in this heap will be the smallest element in the upper half.

2.  **Maintaining Balance**:
    *   The `maxHeap` should ideally contain `N/2` elements (if N is even) or `(N+1)/2` elements (if N is odd).
    *   The `minHeap` should contain `N/2` elements.
    *   This ensures that `maxHeap.peek()` is the median (if N is odd) or `(maxHeap.peek() + minHeap.peek()) / 2` (if N is even).

3.  **`addNum(int num)` method**:
    *   Add `num` to the `maxHeap` first. This ensures that smaller numbers tend to go to the lower half.
    *   Then, `poll()` the largest element from `maxHeap` and `add()` it to `minHeap`. This moves the largest element of the lower half to the upper half, ensuring `maxHeap.peek() <= minHeap.peek()`.
    *   **Balance Check**: If `maxHeap.size() < minHeap.size()`, it means the `minHeap` has too many elements. To rebalance, `poll()` the smallest element from `minHeap` and `add()` it to `maxHeap`. This ensures `maxHeap` either has the same size as `minHeap` or one more element.

4.  **`findMedian()` method**:
    *   If `maxHeap.size() > minHeap.size()`, the total number of elements is odd, and the median is `maxHeap.peek()`.
    *   If `maxHeap.size() == minHeap.size()`, the total number of elements is even, and the median is the average of `maxHeap.peek()` and `minHeap.peek()`.

## Complexity Analysis
-   **`addNum` method**: O(log N), where N is the number of elements added so far. Each heap operation (add/poll) takes logarithmic time.
-   **`findMedian` method**: O(1), as it only involves peeking at the top elements of the heaps.
-   **Space Complexity:** O(N) to store all numbers in the two heaps.

## Example
`MedianFinder` operations:

1.  `addNum(1)`:
    *   `maxHeap.add(1)` -> `maxHeap = {1}`
    *   `minHeap.add(maxHeap.poll())` -> `maxHeap = {}`, `minHeap = {1}`
    *   `maxHeap.size() < minHeap.size()` is true. `maxHeap.add(minHeap.poll())` -> `maxHeap = {1}`, `minHeap = {}`
    *   State: `maxHeap = {1}`, `minHeap = {}`

2.  `addNum(2)`:
    *   `maxHeap.add(2)` -> `maxHeap = {2, 1}`
    *   `minHeap.add(maxHeap.poll())` -> `maxHeap = {1}`, `minHeap = {2}`
    *   `maxHeap.size() < minHeap.size()` is true. `maxHeap.add(minHeap.poll())` -> `maxHeap = {2, 1}`, `minHeap = {}`. Wait, this is wrong. The `maxHeap` should be `{1}` and `minHeap` should be `{2}`.
    *   Let's re-trace `addNum(2)`:
        *   `maxHeap.add(2)`: `maxHeap` is `{1, 2}` (max-heap, so 2 is root).
        *   `minHeap.add(maxHeap.poll())`: `maxHeap` becomes `{1}`, `minHeap` becomes `{2}`.
        *   `maxHeap.size()` (1) is not less than `minHeap.size()` (1). No rebalance.
    *   State: `maxHeap = {1}`, `minHeap = {2}`

3.  `findMedian()`:
    *   `maxHeap.size() == minHeap.size()`. Median = `(maxHeap.peek() + minHeap.peek()) / 2.0 = (1 + 2) / 2.0 = 1.5`.

4.  `addNum(3)`:
    *   `maxHeap.add(3)`: `maxHeap` is `{1, 3}` (max-heap, so 3 is root).
    *   `minHeap.add(maxHeap.poll())`: `maxHeap` becomes `{1}`, `minHeap` becomes `{2, 3}` (min-heap, so 2 is root).
    *   `maxHeap.size()` (1) is less than `minHeap.size()` (2). Rebalance.
    *   `maxHeap.add(minHeap.poll())`: `maxHeap` becomes `{1, 2}`, `minHeap` becomes `{3}`.
    *   State: `maxHeap = {1, 2}`, `minHeap = {3}`

5.  `findMedian()`:
    *   `maxHeap.size()` (2) is greater than `minHeap.size()` (1). Median = `maxHeap.peek() = 2`.

## Alternate Approach: Sorted List
A naive approach would be to maintain a sorted list (e.g., `ArrayList` and sort it, or `LinkedList` and insert in order).

1.  **`addNum`**: Insert the new number into its correct sorted position in a list. This takes O(N) time.
2.  **`findMedian`**: Access the middle element(s) of the list. This takes O(1) time.

### Complexity
-   **`addNum` method**: O(N)
-   **`findMedian` method**: O(1)
-   **Space Complexity**: O(N)

This approach is less efficient for `addNum` operations compared to the two-heap solution, especially for large data streams.
