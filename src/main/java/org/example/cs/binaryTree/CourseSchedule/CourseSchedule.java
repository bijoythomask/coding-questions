package org.example.cs.binaryTree.CourseSchedule;

import java.util.*;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());
        for (int[] pre : prerequisites) graph.get(pre[1]).add(pre[0]);
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(graph, visited, i)) return false;
        }
        return true;
    }
    private boolean dfs(List<List<Integer>> graph, int[] visited, int i) {
        if (visited[i] == 1) return false;
        if (visited[i] == 2) return true;
        visited[i] = 1;
        for (int j : graph.get(i)) {
            if (!dfs(graph, visited, j)) return false;
        }
        visited[i] = 2;
        return true;
    }
    public static void main(String[] args) {
        CourseSchedule cs = new CourseSchedule();
        int[][] prerequisites = {{1,0},{2,1},{3,2}};
        System.out.println(cs.canFinish(4, prerequisites)); // true
    }
}
