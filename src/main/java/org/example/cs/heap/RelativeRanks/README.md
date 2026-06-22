# Relative Ranks

## Problem Statement
You are given an integer array `score` of size `n`, where `score[i]` is the score of the `i-th` athlete in a competition. All the scores are guaranteed to be unique.

The athletes are placed based on their scores, where the 1st place athlete has the highest score, the 2nd place athlete has the 2nd highest score, and so on. The placement of each athlete determines their rank:
- 1st place: "Gold Medal"
- 2nd place: "Silver Medal"
- 3rd place: "Bronze Medal"
- 4th to `n`-th place: their rank number (e.g., the 5th place athlete's rank is "5")

Return an array `answer` of size `n` where `answer[i]` is the rank of the `i-th` athlete.

## Solution Approach: Max-Heap (Priority Queue)
To determine the ranks, we need to sort the scores in descending order while keeping track of their original indices. A **max-heap (PriorityQueue)** is an ideal data structure for this task.

1.  **Data Structure**: We use a `PriorityQueue` to act as a max-heap. It will store pairs of `[score, original_index]`. The heap will be ordered by `score` in descending order.

2.  **Populate the Heap**:
    *   Iterate through the input `score` array from `i = 0` to `n-1`.
    *   For each score, add the pair `{score[i], i}` to the max-heap. This operation takes O(log n) time.

3.  **Determine Ranks**:
    *   Create a `result` string array of size `n`.
    *   Initialize a `rank` counter to `1`.
    *   While the heap is not empty, `poll()` the element with the highest score. Let this be `[current_score, original_index]`.
    *   Assign the rank to the `result` array at the `original_index`.
        *   If `rank` is 1, assign "Gold Medal".
        *   If `rank` is 2, assign "Silver Medal".
        *   If `rank` is 3, assign "Bronze Medal".
        *   Otherwise, assign the string representation of `rank`.
    *   Increment the `rank` for the next element.

4.  **Return Result**: After the heap is empty, the `result` array will be fully populated with the correct ranks.

## Complexity Analysis
-   **Time Complexity:** O(N log N), where N is the number of scores. Populating the heap takes O(N log N), and retrieving all elements also takes O(N log N).
-   **Space Complexity:** O(N) to store the elements in the heap and the result array.

## Example
`score = [10, 3, 8, 9, 4]`

1.  **Heap Population**:
    *   Push `{10, 0}`, `{3, 1}`, `{8, 2}`, `{9, 3}`, `{4, 4}` into the max-heap.
    *   Heap (ordered by score): `[{10, 0}, {9, 3}, {8, 2}, {4, 4}, {3, 1}]`

2.  **Rank Assignment**:
    *   `rank = 1`: Poll `{10, 0}`. `result[0] = "Gold Medal"`.
    *   `rank = 2`: Poll `{9, 3}`. `result[3] = "Silver Medal"`.
    *   `rank = 3`: Poll `{8, 2}`. `result[2] = "Bronze Medal"`.
    *   `rank = 4`: Poll `{4, 4}`. `result[4] = "4"`.
    *   `rank = 5`: Poll `{3, 1}`. `result[1] = "5"`.

3.  **Final Result**: `["Gold Medal", "5", "Bronze Medal", "Silver Medal", "4"]`

## Alternate Approach: Sorting a 2D Array
Another way is to create a 2D array of `[score, original_index]` pairs and sort it.

1.  Create a 2D array `pairs` of size `n x 2`.
2.  Populate it with `{score[i], i}`.
3.  Sort `pairs` in descending order based on the score.
4.  Iterate through the sorted `pairs` array and assign ranks to a `result` array using the original index stored in `pairs[i][1]`.

This approach also has a time complexity of **O(N log N)** and space complexity of **O(N)**. The provided Java solution uses a less optimal O(n^2) sorting method, which is simpler to write but not suitable for large inputs.
