# Course Schedule

## Problem Statement
There are a total of `numCourses` courses you have to take, labeled from 0 to `numCourses - 1`. You are given an array `prerequisites` where `prerequisites[i] = [ai, bi]` indicates that you must take course `bi` first if you want to take course `ai`.

Return `true` if you can finish all courses. Otherwise, return `false`.

## Solution Approach: DFS Cycle Detection
This problem can be modeled as finding a cycle in a directed graph. The courses are the vertices, and the prerequisites are the directed edges. If the graph contains a cycle, it's impossible to finish all courses; otherwise, it is possible.

This solution uses Depth First Search (DFS) to detect cycles.

1.  **Graph Representation**: An adjacency list is used to represent the graph, where `graph[i]` contains a list of courses that have `i` as a prerequisite.
2.  **Visited Array**: A `visited` array is used to track the state of each course during the traversal:
    *   `0`: Unvisited.
    *   `1`: Visiting (currently in the recursion stack for the active DFS path).
    *   `2`: Visited (has been completely explored, and no cycles were found from it).
3.  **DFS Traversal**:
    *   Iterate through each course from `0` to `numCourses - 1` and start a DFS if the course is unvisited.
    *   In the `dfs` function, mark the current course as `visiting` (state `1`).
    *   For each neighbor of the current course:
        *   If the neighbor is in the `visiting` state, a cycle is detected, and we return `false`.
        *   If the neighbor is unvisited, recursively call `dfs` on it. If the recursive call returns `false`, propagate it up.
    *   If the DFS completes for a node without finding a cycle, mark it as `visited` (state `2`) before returning `true`.
4.  **Result**: If all DFS traversals complete without returning `false`, it means no cycles were found, and we can return `true`.

## Complexity Analysis
-   **Time Complexity:** O(V + E), where V is the number of courses and E is the number of prerequisites. Each vertex and edge is visited once.
-   **Space Complexity:** O(V + E) for storing the adjacency list. The recursion stack can also go up to O(V) in the worst case.

## Example
`numCourses = 4`, `prerequisites = [[1,0],[2,1],[3,2]]`
-   The dependencies are `0 -> 1 -> 2 -> 3`.
-   The DFS will traverse the graph without encountering any node that is currently in the "visiting" state.
-   No cycle is detected.
-   Output: `true`.

## Alternate Approach: BFS (Kahn's Algorithm for Topological Sort)
An alternative is to use Breadth-First Search (BFS) to find a topological sort of the courses.

1.  **Build Graph and In-Degree**: Construct an adjacency list and an `inDegree` array that stores the number of prerequisites for each course.
2.  **Initialize Queue**: Add all courses with an in-degree of `0` to a queue. These are the courses with no prerequisites.
3.  **Process Courses**:
    *   While the queue is not empty, dequeue a course.
    *   For each of its neighbors, decrement their in-degree.
    *   If a neighbor's in-degree becomes `0`, add it to the queue.
4.  **Check for Cycle**: Keep a count of the processed courses. If the count equals `numCourses`, it means a valid topological order was found, and there are no cycles. Otherwise, a cycle exists.
