# Breadth First Search (BFS)

## Algorithm Description
Breadth First Search (BFS) is a graph traversal algorithm that explores all the neighbor nodes at the present depth prior to moving on to the nodes at the next depth level. It starts at a selected node (root) and explores the graph layer by layer.

BFS is often used to find the shortest path in an unweighted graph.

## Solution Approach: Iterative BFS with a Queue
The standard implementation of BFS is iterative and uses a queue to manage the order of nodes to visit.

1.  **Graph Representation**: The graph is typically represented using an adjacency list, where `graph[u]` contains a list of neighbors of node `u`.
2.  **Queue**: A queue is used to store the nodes to be visited. It follows a First-In, First-Out (FIFO) order, which is key to the level-by-level traversal.
3.  **Visited Set**: A `visited` set or boolean array is used to keep track of nodes that have already been visited to avoid processing them again and to prevent infinite loops in graphs with cycles.

### Algorithm Steps:
1.  **Initialization**:
    *   Create a `queue` and add the `startNode` to it.
    *   Create a `visited` set and add the `startNode` to it.
2.  **Main Loop**: While the `queue` is not empty:
    *   Dequeue a `node` from the front of the queue.
    *   Process the `node` (e.g., print it, check its value).
    *   For each `neighbor` of the current `node`:
        *   If the `neighbor` has not been visited, add it to the `visited` set and enqueue it.

## Complexity Analysis
-   **Time Complexity:** O(V + E), where V is the number of vertices and E is the number of edges. Each vertex and edge is visited once.
-   **Space Complexity:** O(V) in the worst case. The space is required for the `visited` set and the `queue`. In the worst case, the queue could hold all vertices (e.g., in a star graph).

## Example
Given the following graph represented by an adjacency list:
`graph = [[1, 2], [3], [3], []]`
And starting the BFS from node `0`:

1.  `queue = [0]`, `visited = {0}`
2.  Dequeue `0`. Process `0`.
    *   Neighbors of `0` are `1` and `2`.
    *   Enqueue `1`. `visited = {0, 1}`.
    *   Enqueue `2`. `visited = {0, 1, 2}`.
    *   `queue` is now `[1, 2]`.
3.  Dequeue `1`. Process `1`.
    *   Neighbor of `1` is `3`.
    *   Enqueue `3`. `visited = {0, 1, 2, 3}`.
    *   `queue` is now `[2, 3]`.
4.  Dequeue `2`. Process `2`.
    *   Neighbor of `2` is `3`. `3` is already visited.
    *   `queue` is now `[3]`.
5.  Dequeue `3`. Process `3`.
    *   `3` has no unvisited neighbors.
    *   `queue` is now empty.
6.  Traversal is complete.

The traversal order would be `0 -> 1 -> 2 -> 3`.

## Alternate Approach
While BFS is naturally iterative, a recursive version can be implemented, but it's generally not recommended as it's less intuitive and can be inefficient. The standard and most effective way to implement BFS is with a queue. For graph problems, choosing between iterative BFS (with a queue) and recursive/iterative DFS (with a stack) is the more common decision point.
