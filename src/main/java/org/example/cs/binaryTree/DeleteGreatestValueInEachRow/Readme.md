# Delete Greatest Value in Each Row

## Problem Statement

You are given an `m x n` matrix `grid` consisting of positive integers.

You perform the following operation until the grid becomes empty:

1.  For each row, find the greatest value. If there are multiple such values, you can choose any.
2.  Delete the greatest value from each row.
3.  Add the maximum of the deleted values to your score.

Return the total score.

## Solution Approach

The problem can be solved by simulating the process described. A straightforward way to do this is:

1.  **Sort Each Row**: For each row in the grid, sort the elements in ascending order.

2.  **Iterate Through Columns**: After sorting, the largest elements will be in the last column, a
nd so on. We can iterate through the columns from right to left (from `n-1` down to `0`).

3.  **Find Maximum in Column**: In each iteration `j`, the elements `grid[i][j]` represent the largest remaining values in each row `i`. We find the maximum value among all `grid[i][j]` for the current column `j`.

4.  **Add to Result**: Add this maximum value to our total result.

5.  **Repeat**: Continue this process for all columns.

This approach has a time complexity of **O(m * n log n)** due to sorting each of the `m` rows of length `n`. The space complexity is **O(log n)** or **O(n)** depending on the implementation of the sorting algorithm.

## Example

`grid = [[1,2,4],[3,3,1]]`

1.  **Sort each row**:
    *   `[1, 2, 4]` remains `[1, 2, 4]`
    *   `[3, 3, 1]` becomes `[1, 3, 3]`
    *   The grid is now `[[1, 2, 4], [1, 3, 3]]`

2.  **First Iteration (last column)**:
    *   The values are `4` and `3`.
    *   The maximum is `4`.
    *   `score = 4`

3.  **Second Iteration (middle column)**:
    *   The values are `2` and `3`.
    *   The maximum is `3`.
    *   `score = 4 + 3 = 7`

4.  **Third Iteration (first column)**:
    *   The values are `1` and `1`.
    *   The maximum is `1`.
    *   `score = 7 + 1 = 8`

The final score is `8`.

## Alternate Approach: Using Priority Queues

An alternative approach is to use a priority queue for each row to efficiently find the greatest value.

1.  **Initialize Priority Queues**: For each row, create a priority queue (max-heap) and insert all the elements of that row into it. This will take **O(m * n log n)** time.

2.  **Simulate the Process**:
    *   Initialize `score = 0`.
    *   Perform the operation `n` times (the number of columns).
    *   In each step, find the maximum value among the top elements of all priority queues.
    *   Add this maximum value to the `score`.
    *   Remove the top element from each priority queue.

3.  **Return Score**: After `n` iterations, return the total `score`.

This approach has the same time complexity as the sorting approach but might be more intuitive to some, as it directly simulates the process of finding and removing the greatest element from each row. The space complexity would be **O(m * n)** to store the elements in the priority queues.
