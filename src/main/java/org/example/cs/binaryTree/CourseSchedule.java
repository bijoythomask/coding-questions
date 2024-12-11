package org.example.cs.binaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
 *
 */
public class CourseSchedule {
    //The idea is to use DFS to detect a cycle in the graph. If a cycle is detected, then it is impossible to finish all courses.
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (isCyclic(i, graph, visited, recStack)) {
                return false;
            }
        }
        return true;
    }

    private boolean isCyclic(int node, List<List<Integer>> graph, boolean[] visited, boolean[] recStack) {
        //The method first checks if the current node is already in the recursion stack
        if (recStack[node]) {
            return true;
        }
        if (visited[node]) {
            return false;
        }
        visited[node] = true;
        recStack[node] = true;
        for (int neighbor : graph.get(node)) {
            if (isCyclic(neighbor, graph, visited, recStack)) {
                return true;
            }
        }
        recStack[node] = false;
        return false;
    }
    public static void main(String[] args) {
        CourseSchedule courseSchedule = new CourseSchedule();
        System.out.println(courseSchedule.canFinish(2, new int[][]{{1, 0}}));
        System.out.println(courseSchedule.canFinish(2, new int[][]{{1, 0}, {0, 1}}));
    }
}

