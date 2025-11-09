# Populating Next Right Pointers in Each Node

## Problem Statement
Given a perfect binary tree, populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to null. The tree is perfect, meaning all leaves are at the same level and every parent has two children.

## Approach
This solution uses a recursive approach to connect the next pointers. The main method `connect(Node root)` starts the process, and a helper method `connect(Node left, Node right)` connects sibling nodes and adjacent nodes across subtrees. The recursion ensures that all nodes at the same level are connected from left to right.

- If either node is null, the recursion stops.
- The left node's next pointer is set to the right node.
- The method recursively connects the left and right children within the same subtree and across adjacent subtrees.

## Complexity
- **Time Complexity:** O(N), where N is the number of nodes in the tree. Each node is visited once.
- **Space Complexity:** O(H), where H is the height of the tree due to recursion stack.

## Example
Given the following perfect binary tree:

```
        1
      /   \
     2     3
    / \   / \
   4   5 6   7
```
After running the algorithm, the next pointers will be:
- 1.next -> null
- 2.next -> 3
- 3.next -> null
- 4.next -> 5
- 5.next -> 6
- 6.next -> 7
- 7.next -> null

## Alternate Approach: Level Order Traversal

An alternative solution uses a queue-based level order traversal. This approach iterates through the tree level by level, connecting each node to its right neighbor.

1.  Start with the root node in a queue.
2.  While the queue is not empty, get the number of nodes at the current level.
3.  Iterate through the nodes at the current level:
    *   Dequeue a node.
    *   If it's not the last node in the level, set its `next` pointer to the next node in the queue (which is its right neighbor).
    *   Enqueue its left and right children for the next level.

### Complexity
-   **Time Complexity:** O(N), as each node is visited once.
-   **Space Complexity:** O(W), where W is the maximum width of the tree, for the queue. For a perfect binary tree, this is O(N).

This approach is often more intuitive and avoids the complexities of recursive calls across subtrees.

## Usage
The main method demonstrates how to construct a tree, connect the next pointers, and print the results:

```java
Node root = new Node(1);
root.left = new Node(2);
root.right = new Node(3);
root.left.left = new Node(4);
root.left.right = new Node(5);
root.right.left = new Node(6);
root.right.right = new Node(7);
PopulatingNextRightPointersInEachNode.connect(root);
PopulatingNextRightPointersInEachNode.printInOrder(root);
```

## File Location
- Source: PopulatingNextRightPointersInEachNode.java

## License
See project-level license for usage and distribution terms.
