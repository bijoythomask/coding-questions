# Largest Square

## Problem Statement

A farmer has a plot of land represented by a 2D binary matrix. Each cell contains either 1 (good land) or 0 (bad land). The farmer wants to choose a contiguous square subregion consisting entirely of good land (all 1s) that has the maximum possible area. Return the area (number of cells) of the largest such square.

Example input:

```
matrix = [
	[1, 0, 1, 0, 0],
	[1, 0, 1, 1, 1],
	[1, 1, 1, 1, 1],
	[1, 0, 0, 1, 0]
]
```

Expected output: 4 (a 2x2 square of 1s)

## Goals and Requirements

- Input: an m x n binary matrix (0/1 integers)
- Output: a single integer representing the area of the largest square containing only 1s
- Time complexity should be efficient for large matrices (prefer O(m*n))
- Space should be reasonable; an in-place or O(n) additional memory solution is preferred when possible

## Constraints and Edge Cases

- Empty matrix (return 0)
- Matrix with all zeros (return 0)
- Matrix with all ones (return min(m, n)^2)
- Non-square matrices (rectangular)
- Very large matrices (memory/time considerations)

## High-level Approach

Use dynamic programming. For each cell (i, j) that contains a 1, compute the side length of the largest square whose bottom-right corner is at (i, j). Let dp[i][j] be that side length. The recurrence:

`dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])` if `matrix[i][j] == 1`, otherwise `0`.

Track the maximum dp value while filling the table; the area is `max_side * max_side`.

This yields O(m*n) time and O(m*n) space for a full DP table. Space can be optimized to O(n) by using a single row (or two rows) since `dp[i][j]` depends only on the previous row and the left cell.

## Detailed Algorithm (DP, optimized to O(n) space)

1.  If the matrix is empty return 0.
2.  Let `n = number of columns`. Create an array `dp` of length `n+1` initialized to 0. Use an extra variable `prev` to store `dp[i-1][j-1]` from the previous iteration.
3.  Iterate rows `i` from `1..m`:
    -   Set `prev = 0`
    -   Iterate columns `j` from `1..n`:
        -   `temp = dp[j]`
        -   if `matrix[i-1][j-1] == 1`:
            `dp[j] = 1 + min(dp[j], dp[j-1], prev)`
            update `max_side` if `dp[j] > max_side`
        -   else:
            `dp[j] = 0`
        -   `prev = temp`
4.  Return `max_side * max_side`

## Function Contract

-   Function name: `largestSquareArea`
-   Inputs: `matrix: List[List[int]]` (0/1)
-   Output: `int` (area)
-   Error modes: malformed input (non-binary values) — caller should validate; function will treat non-1 values as 0

Pseudo-signature (Python):

```python
def largestSquareArea(matrix: List[List[int]]) -> int:
"""Return area of largest square containing only 1s."""
```

## Complexity

-   Time: O(m * n) where m is the number of rows and n is the number of columns.
-   Space: O(n) additional space using the optimized DP row; O(m*n) if using a full table.

## Testing Plan

-   Unit tests (small matrices):
    -   empty matrix -> 0
    -   single cell 0 -> 0
    -   single cell 1 -> 1
    -   rectangular matrix with known largest square
    -   all ones matrix -> min(m, n)^2
    -   all zeros matrix -> 0
    -   example from problem statement
-   Edge cases:
    -   very wide or very tall matrices
    -   non-binary values (treated as 0 by doc convention)

## Example Implementation (Python)

```python
def largestSquareArea(matrix):
		if not matrix or not matrix[0]:
				return 0
		m, n = len(matrix), len(matrix[0])
		dp = [0] * (n + 1)
		max_side = 0
		for i in range(1, m + 1):
				prev = 0
				for j in range(1, n + 1):
						temp = dp[j]
						if matrix[i-1][j-1] == 1:
								dp[j] = 1 + min(dp[j], dp[j-1], prev)
								if dp[j] > max_side:
										max_side = dp[j]
						else:
								dp[j] = 0
						prev = temp
		return max_side * max_side
```

### Java Implementation

```java
public static int largestSquareArea(int[][] matrix) {
	if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
	int m = matrix.length;
	int n = matrix[0].length;
	int[] dp = new int[n + 1];
	int maxSide = 0;
	for (int i = 1; i <= m; i++) {
		int prev = 0;
		for (int j = 1; j <= n; j++) {
			int temp = dp[j];
			if (matrix[i-1][j-1] == 1) {
				dp[j] = 1 + Math.min(Math.min(dp[j], dp[j-1]), prev);
				if (dp[j] > maxSide) maxSide = dp[j];
			} else {
				dp[j] = 0;
			}
			prev = temp;
		}
	}
	return maxSide * maxSide;
}
```

## Deployment / Usage

-   This function is language-agnostic; the same DP idea translates to Java, C++, JavaScript, etc.
-   For large inputs, ensure the runtime environment has sufficient memory for the input matrix. The O(n) DP uses minimal extra memory.

## Trade-offs and Alternatives

-   Brute-force checking of all squares is O(m*n*min(m,n)^2) and not acceptable for large inputs.
-   Using a full dp table (m*n) increases clarity and is simpler to debug; optimized O(n) reduces memory but is slightly trickier to implement correctly.
-   If the matrix is extremely sparse, alternative algorithms based on runs of ones or histogram methods could be considered.

## Notes

-   This design focuses on clarity, correctness, and efficiency. The provided implementation is a minimal, production-ready solution that's easy to port.
