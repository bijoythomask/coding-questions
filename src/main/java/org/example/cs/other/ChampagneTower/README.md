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
    -   Iterate through the rows from `i = 0` to `99`.
    -   For each row, iterate through the glasses from `j = 0` to `i`.
    -   For each glass `dp[i][j]`, check if it has overflowed. A glass overflows if the amount of champagne in it is greater than 1.
        -   If `dp[i][j] > 1`:
            -   Calculate the `excess` champagne: `dp[i][j] - 1`.
            -   Distribute this `excess` equally to the two glasses below it:
                -   `dp[i + 1][j] += excess / 2.0`
                -   `dp[i + 1][j + 1] += excess / 2.0`
            -   After the overflow is distributed, the current glass `dp[i][j]` is now full, so its level is capped at `1`.

3.  **Result**:
    -   After the simulation is complete, the value `dp[query_row][query_glass]` will hold the amount of champagne in the target glass. Since a glass cannot be more than full, the final answer is `min(1.0, dp[query_row][query_glass])`. The provided code handles this implicitly because it only returns the final value after all overflows have been propagated downwards.

### Code Implementation

```java
public class ChampagneTower {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] dp = new double[101][101];
        dp[0][0] = poured;

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j <= i; j++) {
                if (dp[i][j] > 1) {
                    // Distribute the excess to the two glasses below
                    double excess = dp[i][j] - 1;
                    dp[i + 1][j] += excess / 2.0;
                    dp[i + 1][j + 1] += excess / 2.0;
                    // The current glass is now full
                    dp[i][j] = 1.0;
                }
            }
        }

        return dp[query_row][query_glass];
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
        -   `dp[0][0]` is set to `1`.
    -   After row 0, `dp` looks like:
        -   `dp[0][0] = 1`
        -   `dp[1][0] = 0.5`
        -   `dp[1][1] = 0.5`

-   **Row `i = 1`**:
    -   `j = 0`: `dp[1][0]` is `0.5`, which is not `> 1`. Nothing happens.
    -   `j = 1`: `dp[1][1]` is `0.5`, which is not `> 1`. Nothing happens.

-   The loops continue, but no more champagne will flow down from these glasses.

**Result**:
-   The function returns `dp[query_row][query_glass]`, which is `dp[1][1]`.
-   The final value is `0.5`.

## 4. Complexity Analysis

-   **Time Complexity**: **O(R^2)**, where `R` is the number of rows (fixed at 100 in this problem). We iterate through each glass in the pyramid approximately once.
-   **Space Complexity**: **O(R^2)**, for the `dp` table used to store the state of the champagne in each glass.
