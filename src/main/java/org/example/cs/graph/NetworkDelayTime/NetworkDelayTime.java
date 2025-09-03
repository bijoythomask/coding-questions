package org.example.cs.graph.NetworkDelayTime;

import java.util.Arrays;
import java.util.PriorityQueue;

public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{k, 0});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0], d = curr[1];
            if (d > dist[u]) continue;
            for (int[] edge : times) {
                if (edge[0] == u) {
                    int v = edge[1], w = edge[2];
                    if (dist[v] > dist[u] + w) {
                        dist[v] = dist[u] + w;
                        pq.offer(new int[]{v, dist[v]});
                    }
                }
            }
        }
        int max = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) return -1;
            max = Math.max(max, dist[i]);
        }
        return max;
    }
    public static void main(String[] args) {
        NetworkDelayTime ndt = new NetworkDelayTime();
        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
        System.out.println(ndt.networkDelayTime(times, 4, 2)); // 2
    }
}
