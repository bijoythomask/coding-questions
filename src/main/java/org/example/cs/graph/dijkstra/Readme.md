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

## 3. Java Implementation

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

    public static void main(String[] args) {
        int n = 5; // Number of nodes
        int source = 0;

        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Example Graph
        adj.get(0).add(new Edge(1, 2));
        adj.get(0).add(new Edge(2, 4));
        adj.get(1).add(new Edge(2, 1));
        adj.get(1).add(new Edge(3, 7));
        adj.get(2).add(new Edge(4, 3));
        adj.get(3).add(new Edge(4, 1));

        int[] shortestDistances = dijkstra(n, adj, source);

        System.out.println("Shortest distances from source node " + source + ":");
        for (int i = 0; i < n; i++) {
            System.out.println("Node " + i + ": " + (shortestDistances[i] == Integer.MAX_VALUE ? "Infinity" : shortestDistances[i]));
        }
        // Expected Output:
        // Node 0: 0
        // Node 1: 2
        // Node 2: 3
        // Node 3: 9
        // Node 4: 6
    }
}
```

## 4. Example Walkthrough

Let's trace the algorithm with the example graph from the code, with `source = 0`.

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

**Iteration 3**:
-   Extract `{3, 2}` from `pq`.
-   Neighbor of 2 is 4.
    -   Path to 4: `dist[2] + 3 = 6`. Update `dist[4] = 6`. Push `{6, 4}` to `pq`.
-   `dist` = `[0, 2, 3, 9, 6]`, `pq` = `[{4, 2}, {6, 4}, {9, 3}]`

**Iteration 4**:
-   Extract `{4, 2}` from `pq`.
-   `d=4` is greater than `dist[2]=3`. We skip this.

**Iteration 5**:
-   Extract `{6, 4}` from `pq`.
-   Node 4 has no outgoing edges.
-   `dist` remains `[0, 2, 3, 9, 6]`.

**Iteration 6**:
-   Extract `{9, 3}` from `pq`.
-   Neighbor of 3 is 4.
    -   Path to 4: `dist[3] + 1 = 10`. This is not `< dist[4]` (which is 6). No update.

**Termination**:
-   The `pq` is now empty. The final `dist` array is `[0, 2, 3, 9, 6]`.

## 5. Complexity Analysis

-   **Time Complexity**: **O(E log V)**
    -   `V` is the number of vertices (nodes), and `E` is the number of edges.
    -   Each vertex is added to the priority queue at most once for each time its distance is updated. Since we only update distances when we find a shorter path, this happens at most `E` times in total across all vertices.
    -   Each operation on the priority queue (insertion and extraction) takes `O(log V)` time.
-   **Space Complexity**: **O(V + E)**
    -   `O(V)` for the `dist` array.
    -   `O(E)` for the adjacency list representation of the graph.
    -   `O(V)` in the worst case for the priority queue.
