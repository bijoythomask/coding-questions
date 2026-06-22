# K Weakest Rows in a Matrix

## Problem Statement
You are given an `m x n` binary matrix `mat` of `1`s (soldiers) and `0`s (civilians). The soldiers are always positioned to the left of the civilians. That is, if `mat[i][j]` is `1`, then every `mat[i][k]` for `k < j` is `1`. Similarly, if `mat[i][j]` is `0`, then every `mat[i][k]` for `k > j` is `0`.

A row `i` is weaker than a row `j` if one of the following is true:
- The number of soldiers in row `i` is less than the number of soldiers in row `j`.
- Both rows have the same number of soldiers and `i < j`.

Return the indices of the `k` weakest rows in the matrix, sorted from weakest to strongest.

## Solution Approach: Max-Heap (Priority Queue)
This problem requires us to sort rows based on a custom criterion (number of soldiers, then index) and then select the top `k` weakest rows. A **max-heap (PriorityQueue)** of size `k` is an efficient way to achieve this.

1.  **Custom Comparator**: We need a way to compare rows. Each row can be represented by a pair `[soldier_count, row_index]`. The comparison logic is:
    *   If `soldier_count`s are different, the row with fewer soldiers is weaker.
    *   If `soldier_count`s are the same, the row with a smaller `row_index` is weaker.
    *   Since we want the `k` *weakest* rows, and we're using a max-heap of size `k`, the heap should store the `k` *strongest* rows encountered so far. This way, when a new weaker row comes along, it can replace one of the stronger ones. Therefore, the comparator for the max-heap should prioritize:
        *   More soldiers (stronger).
        *   Larger index (stronger, for tie-breaking).

2.  **Iterate and Maintain Heap**:
    *   Initialize a `PriorityQueue` with the custom comparator.
    *   Iterate through each row `i` from `0` to `m-1`:
        *   Calculate the `soldier_count` for the current row. This can be done by summing the elements or by finding the first `0` (or end of row).
        *   Add the pair `[soldier_count, i]` to the max-heap.
        *   If the size of the heap exceeds `k`, `poll()` the strongest row (which will be at the top of the max-heap). This ensures the heap always contains the `k` strongest rows seen so far.

3.  **Extract Results**:
    *   After processing all rows, the heap will contain the `k` strongest rows. To get the `k` weakest rows in sorted order (weakest to strongest), we need to `poll()` all elements from the heap and store their indices.
    *   Since the heap is a max-heap, polling will give us the strongest first. We need to store these in a temporary array and then reverse it, or simply fill the result array from the end.

## Complexity Analysis
-   **Time Complexity:** O(m * (n + log k)).
    *   Calculating `soldier_count` for each row takes O(n) time.
    *   Adding/polling from the heap takes O(log k) time.
    *   We do this for `m` rows.
-   **Space Complexity:** O(k) to store the elements in the priority queue.

## Example
`mat = [[1,1,0,0,0],[1,1,1,1,0],[1,0,0,0,0],[1,1,0,0,0],[1,1,1,1,1]]`, `k = 3`

**Row Soldier Counts:**
- Row 0: 2 soldiers
- Row 1: 4 soldiers
- Row 2: 1 soldier
- Row 3: 2 soldiers
- Row 4: 5 soldiers

**Heap (max-heap of size 3, storing [soldiers, index]):**

1.  Process `[2, 0]`: Heap: `{[2, 0]}`
2.  Process `[4, 1]`: Heap: `{[4, 1], [2, 0]}`
3.  Process `[1, 2]`: Heap: `{[4, 1], [2, 0], [1, 2]}` (Heap size is 3, no poll yet)
4.  Process `[2, 3]`: Add `[2, 3]`. Heap: `{[4, 1], [2, 3], [2, 0], [1, 2]}`. Size is 4, poll strongest: `[4, 1]`. Heap: `{[2, 3], [2, 0], [1, 2]}`. (Note: `[2,3]` is stronger than `[2,0]` due to index tie-breaking in the max-heap, if `b[1]-a[1]` is used for tie-breaking)
5.  Process `[5, 4]`: Add `[5, 4]`. Heap: `{[5, 4], [2, 3], [2, 0], [1, 2]}`. Size is 4, poll strongest: `[5, 4]`. Heap: `{[2, 3], [2, 0], [1, 2]}`.

After all rows, the heap contains: `{[2, 3], [2, 0], [1, 2]}`.

**Extracting Results (from weakest to strongest):**
- Poll `[2, 3]`. Result: `[3]`
- Poll `[2, 0]`. Result: `[3, 0]`
- Poll `[1, 2]`. Result: `[3, 0, 2]`

Reversed to get weakest to strongest: `[2, 0, 3]`.

## Alternate Approach: Sorting
A straightforward approach is to calculate the soldier count for each row and then sort all rows based on the defined criteria.

1.  Create a list of `[soldier_count, row_index]` pairs for all `m` rows.
2.  Sort this list using a custom comparator that implements the "weaker than" logic:
    *   Compare `soldier_count`s first.
    *   If `soldier_count`s are equal, compare `row_index`s.
3.  Take the first `k` elements from the sorted list and extract their `row_index`.

This approach has a time complexity of **O(m * n + m log m)**, where `m * n` is for calculating all soldier counts and `m log m` is for sorting. The space complexity is **O(m)** for storing the pairs.
