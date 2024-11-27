package org.example.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1, and return them in any order.
 * The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).
 */

public class AllPathsFromSourceToTarget {

        public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            path.add(0);
            dfs(graph, 0, path, result);
            return result;
        }

        private static void dfs(int[][] graph, int node, List<Integer> path, List<List<Integer>> result) {
            if (node == graph.length - 1) {
                result.add(new ArrayList<>(path));
                return;
            }
            for (int nextNode : graph[node]) {
                path.add(nextNode);
                dfs(graph, nextNode, path, result);
                path.remove(path.size() - 1);
            }
        }

        public static void main(String[] args) {
            System.out.println(allPathsSourceTarget(new int[][]{{1, 2}, {3}, {3}, {}}));
        }
}
