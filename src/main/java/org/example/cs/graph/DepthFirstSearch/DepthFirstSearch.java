package org.example.cs.graph.DepthFirstSearch;

import java.util.HashSet;
import java.util.Set;

public class DepthFirstSearch {
    public void dfs(int[][] graph, int node, Set<Integer> visited) {
        if (visited.contains(node)) return;
        visited.add(node);
        System.out.print(node + " ");
        for (int neighbor : graph[node]) {
            dfs(graph, neighbor, visited);
        }
    }
    public static void main(String[] args) {
        int[][] graph = {{1,2},{3},{3},{}};
        new DepthFirstSearch().dfs(graph, 0, new HashSet<>());
    }
}
