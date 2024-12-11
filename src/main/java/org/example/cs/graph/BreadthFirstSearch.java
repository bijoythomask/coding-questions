package org.example.cs.graph;

import java.util.*;

public class BreadthFirstSearch {

    // Method to perform BFS on a graph represented as an adjacency list
    public List<Integer> bfs(int startNode, Map<Integer, List<Integer>> graph) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.add(startNode);
        visited.add(startNode);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            result.add(currentNode);

            for (int neighbor : graph.getOrDefault(currentNode, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        BreadthFirstSearch bfs = new BreadthFirstSearch();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(3));
        graph.put(2, Arrays.asList(3));
        graph.put(3, Arrays.asList());

        System.out.println(bfs.bfs(0, graph)); // Output: [0, 1, 2, 3]
    }
}