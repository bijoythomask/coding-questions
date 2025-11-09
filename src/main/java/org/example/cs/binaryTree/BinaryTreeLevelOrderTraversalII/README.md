# Binary Tree Level Order Traversal II

## Problem Statement
Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values. This means traversing the tree from left to right, level by level, but returning the levels in reverse order (from leaf to root).

## Solution Approach
The solution uses a standard level-order traversal with a queue. For each level, we gather all the nodes in a temporary list. The key difference from a standard level-order traversal is that we add this temporary list to the *beginning* of our result list. This effectively reverses the order of the levels, giving us a bottom-up traversal.

1.  Initialize an empty list of lists, `result`, to store the levels.
2.  If the root is null, return the empty `result` list.
3.  Initialize a queue and add the root to it.
4.  While the queue is not empty:
    *   Get the number of nodes at the current level (`size`).
    *   Initialize a new list, `level`, for the current level's values.
    *   Loop `size` times to process all nodes at the current level:
        *   Dequeue a node.
        *   Add its value to the `level` list.
        *   If the node has left or right children, enqueue them.
    *   Add the `level` list to the beginning of the `result` list (`result.add(0, level)`).
5.  Return the `result` list.

## Complexity Analysis
-   **Time Complexity:** O(N), where N is the number of nodes in the tree. Each node is visited exactly once.
-   **Space Complexity:** O(W), where W is the maximum width of the tree. This is the maximum number of nodes that can be in the queue at any one time.

## Example
For the following tree:
```
    3
   / \
  9  20
    /  \
   15   7
```
The bottom-up level order traversal would be:
```
[
   [15, 7],
   [9, 20],
   [3]
]
```

## Alternate Approach: Recursive DFS
A recursive Depth-First Search (DFS) approach can also be used. A helper function would take the node and its level as input.

1.  The helper function is called recursively for the left and right children, incrementing the level.
2.  When a new level is reached for the first time, a new list is added to the result list.
3.  The node's value is added to the list corresponding to its level.
4.  After the traversal is complete, the result list is reversed.

This approach is conceptually similar but uses the call stack for traversal instead of a queue.
