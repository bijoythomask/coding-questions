package org.example.heap;

/**
 * The K Weakest Rows in a Matrix
 * LeetCode 1337. Easy.
 * You are given an m x n binary matrix mat of 1's (representing soldiers) and 0's (representing civilians).
 * The soldiers are positioned in front of the civilians. That is, all the 1's will appear to the left of all the 0's in each row.
 * A row i is weaker than a row j if one of the following is true:
 * The number of soldiers in row i is less than the number of soldiers in row j.
 * Both rows have the same number of soldiers and i < j.
 * Return the indices of the k weakest rows in the matrix ordered from weakest to strongest.
 * Example 1:
 * Input: mat = [[1,1,0,0,0],
 *              [1,1,1,1,0],
 *              [1,0,0,0,0],
 *              [1,1,0,0,0],
 *              [1,1,1,1,1]],
 *              k = 3
 *              Output: [2,0,3]
 *
 */
public class KWeakestRowInMatrix {
    public static void main(String[] args) {
        KWeakestRowInMatrix solution = new KWeakestRowInMatrix();
        int[][] mat = {{1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 1}};
        int k = 3;
        int[] result = solution.kWeakestRows(mat, k);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    public int[] kWeakestRows(int[][] mat, int k) {
        int[] result = new int[k];
        int[] count = new int[mat.length];
        for (int i = 0; i < mat.length; i++) {
            count[i] = countOnes(mat[i]);
        }
        boolean[] visited = new boolean[mat.length];
        for (int i = 0; i < k; i++) {
            int minIndex = -1;
            for (int j = 0; j < mat.length; j++) {
                if (!visited[j] && (minIndex == -1 || count[j] < count[minIndex])) {
                    minIndex = j;
                }
            }
            result[i] = minIndex;
            visited[minIndex] = true;
        }
        return result;
    }

    private int countOnes(int[] row) {
        int left = 0;
        int right = row.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (row[mid] == 1) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
