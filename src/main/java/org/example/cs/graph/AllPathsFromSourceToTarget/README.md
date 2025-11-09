# All Paths From Source to Target

## Problem Statement
Given a directed acyclic graph (DAG) of `n` nodes labeled from `0` to `n - 1`, the task is to find all possible paths from the source node `0` to the target node `n - 1`.

The graph is provided as an adjacency list, where `graph[i]` contains a list of all nodes that can be visited from node `i`.

## Solution Approach: Depth-First Search (DFS)
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

## Complexity Analysis
-   **Time Complexity:** O(2^N * N), where N is the number of nodes. In the worst case (a complete graph), there can be an exponential number of paths. For each path found, we spend O(N) time to create a copy of it.
-   **Space Complexity:** O(N). The recursion depth can go up to N, and the `path` list can also store up to N nodes. This does not include the space required for the output list.

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

## Alternate Approach: Breadth-First Search (BFS)
While DFS is more natural for this problem, a BFS-based solution is also possible. Instead of storing single nodes in the queue, we would store the entire path taken to reach that node.

1.  **Initialization**:
    *   Create a `queue` that will store paths (e.g., `Queue<List<Integer>>`).
    *   Create an initial path `[0]` and add it to the queue.
2.  **Main Loop**: While the `queue` is not empty:
    *   Dequeue a `path`.
    *   Get the last node in the path, `currentNode`.
    *   If `currentNode` is the target, add the `path` to the results.
    *   For each `neighbor` of `currentNode`:
        *   Create a new path by copying the current `path` and adding the `neighbor`.
        *   Enqueue the `newPath`.

This BFS approach also finds all paths. However, it can be less memory-efficient than DFS because it stores multiple full paths in the queue simultaneously. The time complexity remains similar to the DFS approach.
