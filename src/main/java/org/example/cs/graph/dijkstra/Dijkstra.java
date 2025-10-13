package org.example.cs.graph.dijkstra;

import java.util.*;

public class Dijkstra {

    // Represents an edge in the graph
    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // Represents a pair for the priority queue: {distance, node}
    static class Pair implements Comparable<Pair> {
        int distance;
        int node;

        Pair(int distance, int node) {
            this.distance = distance;
            this.node = node;
        }

        @Override
        public int compareTo(Pair other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static int[] dijkstra(int n, List<List<Edge>> adj, int source) {
        // 1. Initialization
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(0, source));

        // 2. Main Loop
        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int d = current.distance;
            int u = current.node;

            // Optimization: If we've already found a shorter path, skip
            if (d > dist[u]) {
                continue;
            }

            // 3. Relaxation
            for (Edge edge : adj.get(u)) {
                int v = edge.to;
                int weight = edge.weight;

                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Pair(dist[v], v));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        int n = 5; // Number of nodes
        int source = 0;

        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Example Graph
        adj.get(0).add(new Edge(1, 2));
        adj.get(0).add(new Edge(2, 4));
        adj.get(1).add(new Edge(2, 1));
        adj.get(1).add(new Edge(3, 7));
        adj.get(2).add(new Edge(4, 3));
        adj.get(3).add(new Edge(4, 1));

        int[] shortestDistances = dijkstra(n, adj, source);

        System.out.println("Shortest distances from source node " + source + ":");
        for (int i = 0; i < n; i++) {
            System.out.println("Node " + i + ": " + (shortestDistances[i] == Integer.MAX_VALUE ? "Infinity" : shortestDistances[i]));
        }
    }
}
