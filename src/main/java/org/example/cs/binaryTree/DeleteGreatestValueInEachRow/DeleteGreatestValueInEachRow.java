package org.example.cs.binaryTree.DeleteGreatestValueInEachRow;

import java.util.Arrays;

public class DeleteGreatestValueInEachRow {
    /**
     * Deletes the greatest value in each row as described in the problem statement.
     * @param grid the input m x n matrix
     * @return the total score after performing the operations
     */
    public int deleteGreatestValue(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // Sort each row
        for (int i = 0; i < m; i++) {
            Arrays.sort(grid[i]);
        }
        int score = 0;
        // Iterate through columns from right to left
        for (int col = n - 1; col >= 0; col--) {
            int max = 0;
            for (int row = 0; row < m; row++) {
                max = Math.max(max, grid[row][col]);
            }
            score += max;
        }
        return score;
    }
}
