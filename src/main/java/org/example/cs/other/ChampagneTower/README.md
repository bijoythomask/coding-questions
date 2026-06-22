# Champagne Tower

## 1. Problem Description

We have a pyramid of champagne glasses stacked in a triangular shape. The top row has 1 glass, the second row has 2 glasses, and so on, up to the 100th row.

When champagne is poured into the top glass, it fills up. If it overflows (i.e., more than 1 cup of champagne is poured into it), the excess champagne is split equally between the two glasses directly below it (one to the left and one to the right).

Given the total amount of champagne poured (`poured`) and the coordinates of a specific glass (`query_row`, `query_glass`), the task is to determine how full that glass is. Assume the top glass is at `(0, 0)`.

## 2. Approach: Dynamic Programming Simulation

This problem can be modeled as a simulation of champagne flowing down the pyramid. A 2D array, let's call it `dp`, can be used to represent the amount of champagne in each glass. `dp[i][j]` will store the total amount of champagne that has flowed *into* the glass at row `i`, column `j`.

### Algorithm Steps:

1.  **Initialization**:
    -   Create a 2D array `dp` of size `101x101` to be safe (since rows go up to 99).
    -   Pour all the initial champagne into the top glass: `dp[0][0] = poured`.

2.  **Simulation Loop**:
    -   Iterate through the rows from `i = 0` to `query_row`. We only need to simulate up to the `query_row` because glasses below it won't affect the target glass.
    -   For each row, iterate through the glasses from `j = 0` to `i`.
    -   For each glass `dp[i][j]`, check if it has overflowed. A glass overflows if the amount of champagne in it is greater than 1.
        -   If `dp[i][j] > 1`:
            -   Calculate the `excess` champagne: `dp[i][j] - 1`.
            -   Distribute this `excess` equally to the two glasses below it:
                -   `dp[i + 1][j] += excess / 2.0`
                -   `dp[i + 1][j + 1] += excess / 2.0`
            -   The current glass `dp[i][j]` is now considered full for its own calculation, but its value in `dp` might still be `> 1` if we don't explicitly cap it. The crucial part is that only the *excess* flows down.

3.  **Result**:
    -   After the simulation is complete, the value `dp[query_row][query_glass]` will hold the total amount of champagne that has flowed *into* the target glass. Since a glass cannot be more than full, the final answer is `min(1.0, dp[query_row][query_glass])`. The provided code implicitly handles this by returning the value, which will be `1.0` if it's full or the fraction if it's not.

### Code Implementation

```java
public class ChampagneTower {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] dp = new double[101][101];
        dp[0][0] = poured;

        for (int i = 0; i < query_row + 1; i++) { // Only need to go up to query_row
            for (int j = 0; j <= i; j++) {
                if (dp[i][j] > 1) {
                    double excess = dp[i][j] - 1;
                    dp[i + 1][j] += excess / 2.0;
                    dp[i + 1][j + 1] += excess / 2.0;
                    // No need to set dp[i][j] = 1.0 here, as only excess matters for next row
                }
            }
        }

        return Math.min(1.0, dp[query_row][query_glass]); // Cap at 1.0
    }
}
```

## 3. Example Walkthrough

Let's trace with `poured = 2`, `query_row = 1`, `query_glass = 1`.

**Initialization**:
-   `dp` is a 101x101 array of zeros.
-   `dp[0][0] = 2`.

**Simulation**:

-   **Row `i = 0`**:
    -   `j = 0`: `dp[0][0]` is `2`, which is `> 1`.
        -   `excess = 2 - 1 = 1`.
        -   `dp[1][0] += 1 / 2.0`  (becomes 0.5)
        -   `dp[1][1] += 1 / 2.0`  (becomes 0.5)
    -   After row 0, `dp` looks like:
        -   `dp[0][0] = 2` (value not capped in `dp` array, but only excess used)
        -   `dp[1][0] = 0.5`
        -   `dp[1][1] = 0.5`

-   **Row `i = 1`**:
    -   `j = 0`: `dp[1][0]` is `0.5`, which is not `> 1`. Nothing happens.
    -   `j = 1`: `dp[1][1]` is `0.5`, which is not `> 1`. Nothing happens.

-   The loops continue up to `query_row`.

**Result**:
-   The function returns `Math.min(1.0, dp[query_row][query_glass])`, which is `Math.min(1.0, dp[1][1])`.
-   The final value is `0.5`.

## 4. Complexity Analysis

-   **Time Complexity**: **O(query_row^2)**. We iterate through each glass up to the `query_row`. Since the number of glasses in a row `i` is `i+1`, the total number of glasses processed is approximately `query_row * (query_row + 1) / 2`.
-   **Space Complexity**: **O(query_row^2)**, for the `dp` table.

## 5. Alternate Approach: Space-Optimized Dynamic Programming
We can optimize the space complexity to O(query_row) by observing that to calculate the values for the current row `i`, we only need the values from the previous row `i-1`.

1.  **Initialization**:
    -   Create a 1D array `dp` of size `query_row + 1`.
    -   Set `dp[0] = poured`.
2.  **Simulation Loop**:
    -   Iterate through the rows from `i = 0` to `query_row - 1`.
    -   For each row `i`, create a new 1D array `next_row_dp` of size `query_row + 1` (or iterate `dp` in reverse).
    -   Iterate `j` from `i` down to `0` (important to iterate in reverse if updating `dp` in place, or use a `next_row_dp` array):
        -   If `dp[j] > 1`:
            -   `excess = dp[j] - 1`.
            -   `next_row_dp[j] += excess / 2.0`.
            -   `next_row_dp[j + 1] += excess / 2.0`.
    -   Update `dp = next_row_dp` for the next iteration.
3.  **Result**: Return `Math.min(1.0, dp[query_glass])`.

### Complexity of Alternate Approach
-   **Time Complexity**: O(query_row^2), same as the 2D DP.
-   **Space Complexity**: O(query_row), as we only need to store two rows (or one row if updated carefully in reverse).
