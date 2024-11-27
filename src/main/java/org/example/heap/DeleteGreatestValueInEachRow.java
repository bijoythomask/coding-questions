package org.example.heap;

/**
 * You are given an m x n matrix grid consisting of positive integers.
 *
 * Perform the following operation until grid becomes empty:
 *
 * Delete the element with the greatest value from each row. If multiple such elements exist, delete any of them.
 * Add the maximum of deleted elements to the answer.
 * Note that the number of columns decreases by one after each operation.
 *
 * Return the answer after performing the operations described above.
 * Example 1:
 * Input: grid = [[3,3,1],[8,5,2],[4,2,1]]
 * Output: 12
 *
 *Explanation: The diagram above shows the removed values in each step.
 * - In the first operation, we remove 4 from the first row and 3 from the second row (notice that, there are two cells with value 3 and we can remove any of them). We add 4 to the answer.
 * - In the second operation, we remove 2 from the first row and 3 from the second row. We add 3 to the answer.
 * - In the third operation, we remove 1 from the first row and 1 from the second row. We add 1 to the answer.
 * The final answer = 4 + 3 + 1 = 8.
 * 
 *
 */

import java.util.*;

public class DeleteGreatestValueInEachRow {

    public int deleteGreatestValue(int[][] grid) {
        int answer = 0;

        while (grid[0].length > 0) {
            int maxDeletedValue = Integer.MIN_VALUE;

            for (int i = 0; i < grid.length; i++) {
                int maxInRow = Integer.MIN_VALUE;
                int maxIndex = -1;

                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] > maxInRow) {
                        maxInRow = grid[i][j];
                        maxIndex = j;
                    }
                }

                maxDeletedValue = Math.max(maxDeletedValue, maxInRow);
                grid[i] = removeElement(grid[i], maxIndex);
            }

            answer += maxDeletedValue;
        }

        return answer;
    }

    private int[] removeElement(int[] array, int index) {
        int[] newArray = new int[array.length - 1];
        for (int i = 0, k = 0; i < array.length; i++) {
            if (i == index) {
                continue;
            }
            newArray[k++] = array[i];
        }
        return newArray;
    }

    public static void main(String[] args) {
        DeleteGreatestValueInEachRow solver = new DeleteGreatestValueInEachRow();
        int[][] grid = {{3, 3, 1}, {8, 5, 2}, {4, 2, 1}};
        System.out.println(solver.deleteGreatestValue(grid)); // Output: 12
    }
}

