# Top K Frequent Elements

## Problem Statement
Given a non-empty array of integers `nums`, return the `k` most frequent elements. You may return the answer in any order.

## Solution Approach: HashMap and Min-Heap
This problem requires us to count the frequency of each element and then find the `k` elements with the highest frequencies. A combination of a `HashMap` and a `PriorityQueue` (min-heap) is an efficient way to solve this.

1.  **Frequency Count**:
    *   First, iterate through the input array `nums`.
    *   Use a `HashMap<Integer, Integer>` to store the frequency of each number. The key will be the number, and the value will be its frequency.

2.  **Min-Heap for Top K**:
    *   Create a `PriorityQueue<Integer>` configured as a min-heap. The heap will store numbers, and its elements will be ordered based on their frequencies (the number with the lowest frequency will be at the top).
    *   Iterate through the keys (numbers) in the frequency map:
        *   Add the current number to the min-heap.
        *   If the size of the min-heap exceeds `k`, `poll()` the element with the lowest frequency (which is at the top of the min-heap). This ensures that the heap always contains the `k` elements with the highest frequencies encountered so far.

3.  **Extract Results**:
    *   After iterating through all elements in the frequency map, the min-heap will contain the `k` most frequent elements.
    *   Extract these elements from the heap and store them in an `int[]` array.

## Complexity Analysis
-   **Time Complexity:** O(N + M log K), where N is the number of elements in `nums`, M is the number of unique elements, and K is the target `k`.
    *   Counting frequencies takes O(N) time.
    *   Iterating through M unique elements and performing heap operations (add/poll) takes O(M log K) time. In the worst case, M can be N, so O(N log K).
-   **Space Complexity:** O(M) for the frequency map and O(K) for the heap. In the worst case, O(N).

## Example
`nums = [1, 1, 1, 2, 2, 3]`, `k = 2`

1.  **Frequency Map**:
    *   `{1: 3, 2: 2, 3: 1}`

2.  **Min-Heap (k=2)**:
    *   Process `1` (freq 3): Heap: `{1}`
    *   Process `2` (freq 2): Heap: `{2, 1}` (ordered by frequency, so 2 is smaller than 1)
    *   Process `3` (freq 1): Heap: `{3, 2, 1}`. Size is 3 > k. Poll `3` (lowest freq). Heap: `{2, 1}`.

3.  **Result**: The heap contains `[2, 1]`. Extracting them gives `[2, 1]` or `[1, 2]`.

## Alternate Approach: Bucket Sort (Frequency Array)
This approach can achieve O(N) time complexity in the best case.

1.  **Frequency Count**: Same as before, use a `HashMap<Integer, Integer>` to count frequencies.
2.  **Bucket Array**: Create an array of lists (buckets), where the index represents the frequency and the list at that index contains all numbers with that frequency. The size of this array will be `N + 1` (since max frequency can be N).
3.  **Populate Buckets**: Iterate through the frequency map. For each `(number, frequency)` pair, add the `number` to `bucket[frequency]`.
4.  **Extract Top K**: Iterate through the bucket array from the highest frequency index down to 1. Collect numbers from each bucket until `k` elements are found.

### Complexity
-   **Time Complexity:** O(N) on average. Counting frequencies is O(N). Populating buckets is O(M) (number of unique elements). Extracting elements is O(N) in the worst case.
-   **Space Complexity:** O(N) for the frequency map and the bucket array.

This approach is generally more efficient for larger inputs, especially when `k` is small relative to `N`.
