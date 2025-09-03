package org.example.cs.heap.RelativeRanks;

public class RelativeRanks {
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        String[] result = new String[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) rank[i] = i;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (score[rank[i]] < score[rank[j]]) {
                    int temp = rank[i];
                    rank[i] = rank[j];
                    rank[j] = temp;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (i == 0) result[rank[i]] = "Gold Medal";
            else if (i == 1) result[rank[i]] = "Silver Medal";
            else if (i == 2) result[rank[i]] = "Bronze Medal";
            else result[rank[i]] = String.valueOf(i + 1);
        }
        return result;
    }
    public static void main(String[] args) {
        RelativeRanks solver = new RelativeRanks();
        int[] score = {5,4,3,2,1};
        String[] ranks = solver.findRelativeRanks(score);
        for (String r : ranks) System.out.print(r + " ");
    }
}
