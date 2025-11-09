# Number of Provinces

## Problem Statement
There are `n` cities. Some of them are connected, while some are not. If city `a` is connected directly with city `b`, and city `b` is connected directly with city `c`, then city `a` is connected indirectly with city `c`.

A **province** is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an `n x n` matrix `isConnected` where `isConnected[i][j] = 1` if the `i-th` city and the `j-th` city are directly connected, and `isConnected[i][j] = 0` otherwise.

Return the total number of **provinces**.

## Solution Approach: Depth-First Search (DFS)
This problem is about finding the number of **connected components** in an undirected graph. The cities represent the vertices, and the connections represent the edges.

The solution uses a Depth-First Search (DFS) traversal to explore and mark all cities belonging to a single province.

1.  **Graph Representation**: The input `isConnected` matrix serves as the adjacency matrix for the graph.
2.  **Visited Array**: A boolean array `visited` of size `n` is used to keep track of cities that have already been visited as part of a province.
3.  **Counting Provinces**:
    *   Initialize a `count` of provinces to `0`.
    *   Iterate through each city from `0` to `n-1`.
    *   If a city `i` has not been visited yet, it means we have found a new province.
        *   Increment the `count`.
        *   Start a DFS traversal from city `i` to find all cities connected to it.
4.  **DFS Traversal**:
    *   The `dfs(city)` function marks the current `city` as visited.
    *   It then iterates through all other cities `j` to find its neighbors (where `isConnected[city][j] == 1`).
    *   If a neighbor `j` has not been visited, it makes a recursive call `dfs(j)`.
5.  **Result**: After the main loop is complete, `count` will hold the total number of provinces.

## Complexity Analysis
-   **Time Complexity:** O(n^2). We traverse the entire `n x n` matrix. The DFS traversal visits each node and edge once. Since the graph is represented by an adjacency matrix, the traversal for each component takes O(n^2) in the worst case.
-   **Space Complexity:** O(n) for the `visited` array and the recursion stack.

## Example
`isConnected = [[1,1,0],[1,1,0],[0,0,1]]`

1.  `count = 0`, `visited = [false, false, false]`
2.  Start loop at `i = 0`. `visited[0]` is `false`.
    *   Increment `count` to `1`.
    *   Call `dfs(0)`.
        *   `visited[0] = true`.
        *   Neighbor of `0` is `1`. `visited[1]` is `false`. Call `dfs(1)`.
            *   `visited[1] = true`.
            *   Neighbor of `1` is `0`. `visited[0]` is `true`. Skip.
            *   Return from `dfs(1)`.
        *   Return from `dfs(0)`.
3.  Loop continues to `i = 1`. `visited[1]` is `true`. Skip.
4.  Loop continues to `i = 2`. `visited[2]` is `false`.
    *   Increment `count` to `2`.
    *   Call `dfs(2)`.
        *   `visited[2] = true`.
        *   No unvisited neighbors. Return.
5.  Loop finishes. Return `count = 2`.

## Alternate Approach: Breadth-First Search (BFS)
The same logic can be applied using a Breadth-First Search (BFS) traversal instead of DFS.

1.  The main loop structure remains the same.
2.  When an unvisited city is found, instead of calling `dfs`, we start a BFS traversal.
3.  **BFS Traversal**:
    *   Create a queue and add the starting city to it.
    *   Mark the city as visited.
    *   While the queue is not empty, dequeue a city.
    *   For each of its unvisited neighbors, mark them as visited and add them to the queue.

This approach also has a time complexity of **O(n^2)** and a space complexity of **O(n)** for the queue and visited array. A Union-Find (Disjoint Set Union) data structure provides another efficient way to solve this problem.
