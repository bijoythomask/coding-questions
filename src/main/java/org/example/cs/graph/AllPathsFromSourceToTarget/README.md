# All Paths From Source to Target

## Problem Description
Given a directed acyclic graph (DAG) of `n` nodes labeled from `0` to `n - 1`, the task is to find all possible paths from the source node `0` to the target node `n - 1`.

The graph is provided as an adjacency list, where `graph[i]` contains a list of all nodes that can be visited from node `i`.

## Approach: Depth-First Search (DFS)
The problem of finding all paths in a graph is a classic use case for a Depth-First Search (DFS) traversal combined with backtracking.

The core idea is to start a traversal from the source node `0` and explore as far as possible along each branch. We maintain a `path` list to keep track of the nodes in the current traversal from the source.

### Algorithm
1.  Start a DFS from the source node `0`. The initial path contains just the source node, `0`.
2.  From the current node, iterate through all its neighbors.
3.  For each neighbor:
   - Add the neighbor to the current `path`.
   -  Recursively call the DFS function with the neighbor as the new current node.
   -  After the recursive call returns, **backtrack** by removing the neighbor from the `path`. This is a crucial step that allows us to explore other branches from the current node.
4.  If the current node is the target node (`n - 1`), it means we have found a valid path. We add a copy of the current `path` to our list of results and return.

### Code
```java
import java.util.ArrayList;
import java.util.List;

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
            path.remove(path.size() - 1); // Backtrack
        }
    }
}
```

## Example Walkthrough
Let's trace the algorithm with the input `graph = [[1, 2], [3], [3], []]`. The target node is `n - 1 = 3`.

1.  `dfs(graph, 0, [0], result)`
    -   Node 0 is not the target. Its neighbors are `1` and `2`.
2.  Explore neighbor `1`:
    -   Add `1` to path. `path` is now `[0, 1]`.
    -   Call `dfs(graph, 1, [0, 1], result)`.
        -   Node 1 is not the target. Its neighbor is `3`.
3.  Explore neighbor `3`:
    -   Add `3` to path. `path` is now `[0, 1, 3]`.
    -   Call `dfs(graph, 3, [0, 1, 3], result)`.
        -   Node 3 **is the target**. A copy of `[0, 1, 3]` is added to `result`.
        -   Return.
    -   Backtrack: remove `3` from path. `path` is back to `[0, 1]`.
    -   Node 1 has no more neighbors to explore. Return.
4.  Backtrack: remove `1` from path. `path` is back to `[0]`.
5.  Explore neighbor `2`:
    -   Add `2` to path. `path` is now `[0, 2]`.
    -   Call `dfs(graph, 2, [0, 2], result)`.
        -   Node 2 is not the target. Its neighbor is `3`.
6.  Explore neighbor `3`:
    -   Add `3` to path. `path` is now `[0, 2, 3]`.
    -   Call `dfs(graph, 3, [0, 2, 3], result)`.
        -   Node 3 **is the target**. A copy of `[0, 2, 3]` is added to `result`.
        -   Return.
    -   Backtrack: remove `3` from path. `path` is back to `[0, 2]`.
    -   Node 2 has no more neighbors. Return.
7.  Backtrack: remove `2` from path. `path` is back to `[0]`.
8.  Node 0 has no more neighbors. The initial function call finishes.

The final `result` is `[[0, 1, 3], [0, 2, 3]]`.

## Complexity Analysis
-   **Time Complexity:** O(2^N * N), where N is the number of nodes. In the worst case (a complete graph), there can be an exponential number of paths. For each path found, we spend O(N) time to create a copy of it.
-   **Space Complexity:** O(N). The recursion depth can go up to N, and the `path` list can also store up to N nodes. This does not include the space required for the output list.
