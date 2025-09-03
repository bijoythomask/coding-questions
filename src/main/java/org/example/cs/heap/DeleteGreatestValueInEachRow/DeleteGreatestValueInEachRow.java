package org.example.cs.heap.DeleteGreatestValueInEachRow;

import java.util.Arrays;

public class DeleteGreatestValueInEachRow {
    public int deleteGreatestValue(int[][] grid) {
        int m = grid.length, n = grid[0].length, res = 0;
        for (int[] row : grid) Arrays.sort(row);
        for (int j = n - 1; j >= 0; j--) {
            int max = 0;
            for (int i = 0; i < m; i++) {
                max = Math.max(max, grid[i][j]);
            }
            res += max;
        }
        return res;
    }
    public static void main(String[] args) {
        DeleteGreatestValueInEachRow solver = new DeleteGreatestValueInEachRow();
        int[][] grid = {{1,2,4},{3,3,1}};
        System.out.println(solver.deleteGreatestValue(grid)); // Output: 8
    }
}
