package org.example.cs.heap.KWeakestRowInMatrix;

import java.util.PriorityQueue;

public class KWeakestRowInMatrix {
    public int[] kWeakestRows(int[][] mat, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] == b[0] ? b[1] - a[1] : b[0] - a[0]);
        for (int i = 0; i < mat.length; i++) {
            int count = 0;
            for (int num : mat[i]) count += num;
            pq.offer(new int[]{count, i});
            if (pq.size() > k) pq.poll();
        }
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) res[i] = pq.poll()[1];
        return res;
    }
    public static void main(String[] args) {
        KWeakestRowInMatrix solver = new KWeakestRowInMatrix();
        int[][] mat = {{1,1,0,0,0},{1,1,1,1,0},{1,0,0,0,0},{1,1,0,0,0},{1,1,1,1,1}};
        int[] res = solver.kWeakestRows(mat, 3);
        for (int r : res) System.out.print(r + " "); // Output: 2 0 3
    }
}
