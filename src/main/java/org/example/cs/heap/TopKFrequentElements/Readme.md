# Top K Frequent Elements

## Problem Statement

Given a non-empty array of integers, return the **k** most frequent elements.

## Solution Approach

We can solve this problem by using a combination of a `HashMap` to store frequencies and a `PriorityQueue` (min-heap) to keep track of the top `k` frequent elements.

1.  **Frequency Count**: First, we iterate through the input array and store the frequency of each element in a `HashMap`.

2.  **Min-Heap**: We then create a min-heap of size `k`. The heap will be ordered by the frequency of the elements. We iterate through the keys of our frequency map:
    *   For each element, we add it to the heap.
    *   If the size of the heap exceeds `k`, we remove the element with the lowest frequency (the root of the min-heap).

3.  **Result**: After iterating through all the elements, the heap will contain the `k` most frequent elements. We can then extract these elements from the heap to form our result.

This approach has a time complexity of **O(N log k)**, where N is the number of elements in the array, and a space complexity of **O(N)** in the worst case for the frequency map.

## Example

`nums = [1,1,1,2,2,3], k = 2`

1.  **Frequency Map**: `{1: 3, 2: 2, 3: 1}`

2.  **Min-Heap (k=2)**:
    *   Iterate through the map keys: `1`, `2`, `3`.
    *   Add `1` to the heap. Heap: `[1]`
    *   Add `2` to the heap. Heap: `[2, 1]` (ordered by frequency, so `2` is before `1` as `freq(2) < freq(1)`). Let's assume the comparator is `(a,b) -> map.get(a) - map.get(b)`.
    *   Heap becomes `[2, 1]`.
    *   Add `3`. Heap: `[3, 2, 1]`. Size is 3 > k. Poll `3`. Heap: `[2, 1]`.

3.  **Result**: The final heap contains `[1, 2]`. So the result is `[1, 2]`.
