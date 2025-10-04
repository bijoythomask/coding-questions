package org.example.cs.heap.HighFive;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HighFive {
    public int[][] highFive(int[][] items) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int[] item : items) {
            map.computeIfAbsent(item[0], k -> new PriorityQueue<>()).add(item[1]);
            if (map.get(item[0]).size() > 5) map.get(item[0]).poll();
        }
        int[][] res = new int[map.size()][2];
        int i = 0;
        for (int id : map.keySet()) {
            int sum = 0;
            for (int score : map.get(id)) sum += score;
            res[i][0] = id;
            res[i][1] = sum / 5;
            i++;
        }
        return res;
    }
    public static void main(String[] args) {
        HighFive solver = new HighFive();
        int[][] items = {{1,91},{1,92},{2,93},{2,97},{1,60},{2,77},{1,65},{1,87},{1,100},{2,100},{2,76}};
        int[][] res = solver.highFive(items);
        for (int[] r : res) System.out.println(r[0] + ": " + r[1]);
    }
}
