package org.example.cs.heap;

/**
 * You are given an integer array score of size n, where score[i] is the score of the ith athlete in a competition. All the scores are guaranteed to be unique.
 *
 * The athletes are placed based on their scores, where the 1st place athlete has the highest score, the 2nd place athlete has the 2nd highest score, and so on. The placement of each athlete determines their rank:
 *
 * The 1st place athlete's rank is "Gold Medal".
 * The 2nd place athlete's rank is "Silver Medal".
 * The 3rd place athlete's rank is "Bronze Medal".
 * For the 4th place to the nth place athlete, their rank is their placement number (i.e., the xth place athlete's rank is "x").
 * Return an array answer of size n where answer[i] is the rank of the ith athlete.
 */
public class RelativeRanks {
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        String[] result = new String[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[i] = i;
        }
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
            if (i == 0) {
                result[rank[i]] = "Gold Medal";
            } else if (i == 1) {
                result[rank[i]] = "Silver Medal";
            } else if (i == 2) {
                result[rank[i]] = "Bronze Medal";
            } else {
                result[rank[i]] = String.valueOf(i + 1);
            }
        }
        return result;
    }
}
