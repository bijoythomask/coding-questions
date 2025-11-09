# Depth First Search (DFS)

## Algorithm Description
Depth First Search (DFS) is a graph traversal algorithm that explores as far as possible along each branch before backtracking. It starts at a selected node (root) and explores each branch completely before moving on to the next branch.

The algorithm can be implemented recursively or iteratively.

## Solution Approach: Recursive DFS
The recursive implementation of DFS is the most common and intuitive approach.

1.  **Graph Representation**: The graph is typically represented using an adjacency list, where `graph[u]` contains a list of neighbors of node `u`.
2.  **Visited Set**: A `visited` set or boolean array is used to keep track of nodes that have already been visited to avoid infinite loops in graphs with cycles.
3.  **DFS Function**: A function `dfs(node, visited)` is defined:
    *   **Base Case**: If the `node` has already been visited, return immediately.
    *   **Mark as Visited**: Add the current `node` to the `visited` set.
    *   **Process Node**: Perform the desired operation on the node (e.g., print it, add it to a list).
    *   **Recursive Step**: For each `neighbor` of the current `node`, make a recursive call: `dfs(neighbor, visited)`.

The traversal starts by calling `dfs(startNode, new HashSet<>())`.

## Complexity Analysis
-   **Time Complexity:** O(V + E), where V is the number of vertices and E is the number of edges. Each vertex and edge is visited once.
-   **Space Complexity:** O(V) for the `visited` set. In the worst case, the recursion stack can also go up to O(V) for a skewed graph.

## Example
Given the following graph represented by an adjacency list:
`graph = [[1, 2], [3], [3], []]`
And starting the DFS from node `0`:

1.  `dfs(0)`:
    *   Visit `0`. `visited = {0}`.
    *   Neighbors of `0` are `1` and `2`.
    *   Call `dfs(1)`.
2.  `dfs(1)`:
    *   Visit `1`. `visited = {0, 1}`.
    *   Neighbor of `1` is `3`.
    *   Call `dfs(3)`.
3.  `dfs(3)`:
    *   Visit `3`. `visited = {0, 1, 3}`.
    *   `3` has no neighbors. Return.
4.  Back in `dfs(1)`, all neighbors are visited. Return.
5.  Back in `dfs(0)`, the next neighbor is `2`.
    *   Call `dfs(2)`.
6.  `dfs(2)`:
    *   Visit `2`. `visited = {0, 1, 3, 2}`.
    *   Neighbor of `2` is `3`. `3` is already visited.
    *   Return.
7.  Traversal is complete.

The traversal order would be `0 -> 1 -> 3 -> 2` (or `0 -> 2 -> 3 -> 1`, depending on neighbor order).

## Alternate Approach: Iterative DFS with a Stack
DFS can also be implemented iteratively using a stack to mimic the behavior of the recursion call stack.

1.  **Initialization**:
    *   Create a `stack` and push the `startNode`.
    *   Create a `visited` set and add the `startNode` to it.
2.  **Main Loop**: While the `stack` is not empty:
    *   Pop a `node` from the stack.
    *   Process the `node`.
    *   For each `neighbor` of the `node`:
        *   If the `neighbor` has not been visited, add it to the `visited` set and push it onto the `stack`.

This iterative approach can be useful for very large graphs where recursion might lead to a stack overflow.
