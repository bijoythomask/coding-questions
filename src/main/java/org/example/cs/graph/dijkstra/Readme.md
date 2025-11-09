# Dijkstra's Algorithm for Single-Source Shortest Path

## 1. Problem Description

Dijkstra's algorithm solves the **single-source shortest path problem** for a weighted directed graph where all edge weights are non-negative.

Given a graph and a source vertex, the goal is to find the shortest path from the source vertex to all other vertices in the graph.

## 2. Approach: Using a Priority Queue

Dijkstra's algorithm works by maintaining a set of visited nodes and a set of unvisited nodes. It continuously identifies the unvisited node with the smallest known distance from the source and marks it as visited.

The most efficient way to implement this is by using a **Priority Queue**.

### Key Components:
-   **Graph Representation**: An adjacency list is used to store the graph, where `graph[u]` contains a list of pairs `{v, w}`, indicating an edge from node `u` to node `v` with weight `w`.
-   **Distance Array (`dist`)**: A `dist` array of size `n` (number of nodes) stores the shortest distance found so far from the source to every other node. It is initialized with infinity for all nodes except the source, which is set to 0.
-   **Priority Queue (`pq`)**: The priority queue stores pairs of `{distance, node}`. It is a min-priority queue, ordered by `distance`, ensuring that we can always access the node with the smallest distance from the source in logarithmic time.

### Algorithm Steps:
1.  **Initialization**:
    -   Create a `dist` array and initialize all its values to infinity, except for the source node, which is set to `0`.
    -   Create a priority queue and push the source node into it with a distance of `0`, i.e., `{0, source_node}`.
2.  **Main Loop**:
    -   While the priority queue is not empty, extract the node with the minimum distance. Let this be `{d, u}`, where `d` is the distance and `u` is the node.
    -   If `d` is greater than `dist[u]`, it means we have already found a shorter path to `u`, so we can skip this entry. This check is important for handling duplicate entries in the priority queue.
3.  **Relaxation**:
    -   For each neighbor `v` of the current node `u` with edge weight `w`:
        -   If the path through `u` to `v` is shorter than the currently known distance to `v` (i.e., `dist[u] + w < dist[v]`), we have found a better path.
        -   Update `dist[v]` to this new shorter distance: `dist[v] = dist[u] + w`.
        -   Push the new, shorter path information `{dist[v], v}` into the priority queue.
4.  **Termination**:
    -   The algorithm terminates when the priority queue is empty. The `dist` array now contains the shortest path distances from the source to all other nodes.

## 3. Complexity Analysis

-   **Time Complexity**: **O(E log V)**
    -   `V` is the number of vertices (nodes), and `E` is the number of edges.
    -   Each vertex is added to the priority queue at most once for each time its distance is updated. Since we only update distances when we find a shorter path, this happens at most `E` times in total across all vertices.
    -   Each operation on the priority queue (insertion and extraction) takes `O(log V)` time.
-   **Space Complexity**: **O(V + E)**
    -   `O(V)` for the `dist` array.
    -   `O(E)` for the adjacency list representation of the graph.
    -   `O(V)` in the worst case for the priority queue.

## 4. Alternate Approach: Using a Simple Array

A simpler, but less efficient, implementation of Dijkstra's algorithm uses an array to find the minimum distance node in each step instead of a priority queue.

### Algorithm Steps:
1.  **Initialization**:
    -   Create a `dist` array and initialize all values to infinity, with `dist[source] = 0`.
    -   Create a boolean `visited` array of size `V`, initialized to `false`.
2.  **Main Loop**: Loop `V` times:
    -   Find the unvisited node `u` with the smallest `dist` value. This requires a linear scan through the `dist` array.
    -   Mark `u` as visited (`visited[u] = true`).
    -   For each neighbor `v` of `u`, perform the **relaxation** step: if `dist[u] + weight < dist[v]`, update `dist[v] = dist[u] + weight`.

### Complexity Analysis of Alternate Approach:
-   **Time Complexity**: **O(V^2)**. The main loop runs `V` times, and inside it, finding the minimum distance unvisited node takes `O(V)` time.
-   **Space Complexity**: **O(V + E)** for the graph, `dist` array, and `visited` array.

This `O(V^2)` approach is easier to implement but is only suitable for dense graphs where `E` is close to `V^2`. For sparse graphs, the `O(E log V)` priority queue implementation is significantly faster.

## 5. Java Implementation

```java
import java.util.*;

class Dijkstra {

    // Represents an edge in the graph
    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    // Represents a pair for the priority queue: {distance, node}
    static class Pair implements Comparable<Pair> {
        int distance;
        int node;

        Pair(int distance, int node) {
            this.distance = distance;
            this.node = node;
        }

        @Override
        public int compareTo(Pair other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static int[] dijkstra(int n, List<List<Edge>> adj, int source) {
        // 1. Initialization
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(0, source));

        // 2. Main Loop
        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int d = current.distance;
            int u = current.node;

            // Optimization: If we've already found a shorter path, skip
            if (d > dist[u]) {
                continue;
            }

            // 3. Relaxation
            for (Edge edge : adj.get(u)) {
                int v = edge.to;
                int weight = edge.weight;

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Pair(dist[v], v));
                }
            }
        }

        return dist;
    }
}
```

## 6. Example Walkthrough

Let's trace the algorithm with an example graph, with `source = 0`.
-   **Nodes**: 0, 1, 2, 3, 4
-   **Edges**: (0,1,2), (0,2,4), (1,2,1), (1,3,7), (2,4,3), (3,4,1)

**Initialization**:
-   `dist` = `[0, ∞, ∞, ∞, ∞]`
-   `pq` = `[{0, 0}]`

**Iteration 1**:
-   Extract `{0, 0}` from `pq`.
-   Neighbors of 0 are 1 and 2.
    -   Path to 1: `dist[0] + 2 = 2`. Update `dist[1] = 2`. Push `{2, 1}` to `pq`.
    -   Path to 2: `dist[0] + 4 = 4`. Update `dist[2] = 4`. Push `{4, 2}` to `pq`.
-   `dist` = `[0, 2, 4, ∞, ∞]`, `pq` = `[{2, 1}, {4, 2}]`

**Iteration 2**:
-   Extract `{2, 1}` from `pq`.
-   Neighbors of 1 are 2 and 3.
    -   Path to 2: `dist[1] + 1 = 3`. This is `< dist[2]` (which is 4). Update `dist[2] = 3`. Push `{3, 2}` to `pq`.
    -   Path to 3: `dist[1] + 7 = 9`. Update `dist[3] = 9`. Push `{9, 3}` to `pq`.
-   `dist` = `[0, 2, 3, 9, ∞]`, `pq` = `[{3, 2}, {4, 2}, {9, 3}]`

... and so on, until the `pq` is empty. The final `dist` array will be `[0, 2, 3, 9, 6]`.
