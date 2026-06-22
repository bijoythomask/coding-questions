# Network Delay Time

## Problem Statement
You are given a network of `n` nodes, labeled from `1` to `n`. You are also given `times`, a list of travel times as directed edges `times[i] = (ui, vi, wi)`, where `ui` is the source node, `vi` is the target node, and `wi` is the time it takes for a signal to travel from source to target.

We will send a signal from a given node `k`. Return the time it takes for all `n` nodes to receive the signal. If it is impossible for all `n` nodes to receive the signal, return `-1`.

## Solution Approach: Dijkstra's Algorithm
This problem is equivalent to finding the shortest path from the source node `k` to all other nodes in the graph. The "time it takes for all nodes to receive the signal" is the length of the longest shortest-path from `k` to any other node. If any node is unreachable, it's impossible.

Dijkstra's algorithm is a perfect fit for this, as it finds the shortest paths from a single source in a weighted graph with non-negative edge weights.

1.  **Graph Representation**: An adjacency list is the most efficient way to represent the graph, but the provided solution iterates through the `times` array directly in each step, which is less efficient but still works.
2.  **Distances Array**: A `dist` array is initialized to store the shortest time from the source `k` to every other node. It's initialized with infinity for all nodes, and `dist[k]` is set to `0`.
3.  **Priority Queue**: A min-priority queue is used to store `[node, time]` pairs. It's ordered by `time`, ensuring we always process the node that is currently closest to the source.
4.  **Algorithm Steps**:
    *   Add the starting node `[k, 0]` to the priority queue.
    *   While the queue is not empty, extract the node with the minimum time. Let this be `[u, d]`.
    *   If we've already found a shorter path to `u` (i.e., `d > dist[u]`), skip it.
    *   For each neighbor `v` of `u` with travel time `w`:
        *   If a new, shorter path is found (`dist[u] + w < dist[v]`), update `dist[v]` to the new shorter time and add `[v, dist[v]]` to the priority queue.
5.  **Find the Result**: After the algorithm finishes, the `dist` array contains the shortest times from `k` to all other nodes.
    *   Iterate through the `dist` array. The result is the maximum value in the array.
    *   If any distance is still infinity, it means that node is unreachable. In this case, return `-1`.

## Complexity Analysis
-   **Time Complexity:** O(E log V), where V is the number of nodes (`n`) and E is the number of `times`. The priority queue operations dominate the runtime. *Note: The provided Java solution has a less optimal implementation where it iterates the entire `times` array in the loop, making its complexity closer to O(V*E). A standard Dijkstra with an adjacency list is O(E log V).*
-   **Space Complexity:** O(V + E) to store the graph (if using an adjacency list) and the distances. The priority queue can hold up to V nodes.

## Example
`times = [[2,1,1],[2,3,1],[3,4,1]]`, `n = 4`, `k = 2`

1.  `dist = [inf, inf, 0, inf, inf]` (using 1-based indexing for nodes)
2.  `pq = [[2, 0]]`
3.  Pop `[2, 0]`. Neighbors of 2 are 1 and 3.
    *   `dist[1] = 1`. Push `[1, 1]` to pq.
    *   `dist[3] = 1`. Push `[3, 1]` to pq.
    *   `dist` is now `[inf, 1, 0, 1, inf]`
4.  Pop `[1, 1]` (or `[3, 1]`, order doesn't matter). No outgoing edges from 1.
5.  Pop `[3, 1]`. Neighbor of 3 is 4.
    *   `dist[4] = dist[3] + 1 = 2`. Push `[4, 2]` to pq.
    *   `dist` is now `[inf, 1, 0, 1, 2]`
6.  Pop `[4, 2]`. No outgoing edges.
7.  Queue is empty. Final `dist` array (for nodes 1-4) is `[1, 0, 1, 2]`.
8.  The maximum value is `2`. All nodes are reachable. Return `2`.

## Alternate Approach: Bellman-Ford Algorithm
The Bellman-Ford algorithm can also solve this problem. It is more general than Dijkstra's as it can handle negative edge weights, but it is slower.

1.  **Initialize Distances**: Same as Dijkstra's, `dist` array with infinity, `dist[k] = 0`.
2.  **Relax Edges**: Repeat `n-1` times:
    *   For each edge `(u, v, w)` in `times`, relax the edge: `dist[v] = min(dist[v], dist[u] + w)`.
3.  **Find Result**: After `n-1` iterations, find the maximum distance in the `dist` array. Check for unreachable nodes as before.

The time complexity of Bellman-Ford is **O(V * E)**, which is generally slower than Dijkstra's for this problem since edge weights are non-negative.
