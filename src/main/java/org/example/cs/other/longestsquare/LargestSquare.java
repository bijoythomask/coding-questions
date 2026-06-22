package org.example.cs.other.longestsquare;

/*
 * Efficient DP solution to find the area of the largest square of 1s in a binary matrix.
 * Time: O(m*n)
 * Space: O(n) additional
 */
public class LargestSquare {
    /**
     * Returns the area of the largest square containing only 1s.
     * Input matrix is expected as int[][] with values 0 or 1.
     */
    public static int largestSquareArea(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[] dp = new int[n + 1];
        int maxSide = 0;
        for (int i = 1; i <= m; i++) {
            int prev = 0; // dp[j-1] from previous row (dp[i-1][j-1] concept)
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

    // Small main for manual testing
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 0, 1, 0, 0},
            {1, 0, 1, 1, 1},
            {1, 1, 1, 1, 1},
            {1, 0, 0, 1, 0}
        };
        System.out.println("Largest square area: " + largestSquareArea(matrix)); // expected 4
    }
}
