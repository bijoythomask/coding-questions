package org.example.cs.graph;

import java.util.*;

public class DepthFirstSearch {

    // Method to perform DFS on a graph represented as an adjacency list
    public List<Integer> dfs(int startNode, Map<Integer, List<Integer>> graph) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        stack.push(startNode);

        while (!stack.isEmpty()) {
            int currentNode = stack.pop();

            if (!visited.contains(currentNode)) {
                visited.add(currentNode);
                result.add(currentNode);

                for (int neighbor : graph.getOrDefault(currentNode, new ArrayList<>())) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        DepthFirstSearch dfs = new DepthFirstSearch();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(3));
        graph.put(2, Arrays.asList(3));
        graph.put(3, Arrays.asList());

        System.out.println(dfs.dfs(0, graph)); // Output: [0, 2, 3, 1]
    }
}