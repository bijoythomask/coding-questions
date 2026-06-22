# Closest Binary Search Tree Value

## Problem Statement

Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

## Solution Approach

The problem asks us to find the node value in a Binary Search Tree (BST) that is closest to a given target value.

We can solve this by traversing the BST and keeping track of the closest value found so far.

1.  **Initialization**: Start with the root of the BST. Initialize a variable, let's say `closest_val`, to the value of the root node. This variable will store the value of the node that is currently closest to the target.

2.  **Traversal**: Traverse the BST from the root down. At each node, compare its value with the target.
    *   If the current node's value is closer to the target than `closest_val`, update `closest_val` to the current node's value.
    *   The distance can be calculated using `abs(node.val - target)`.

3.  **BST Property**: To efficiently find the closest value, we can leverage the property of the BST.
    *   If the `target` is less than the current node's value, it means the closest value is likely in the left subtree (or it could be the current node itself). So, we move to the left child.
    *   If the `target` is greater than the current node's value, we move to the right child.
    *   If the `target` is equal to the current node's value, we have found the exact value, and the difference is 0, which is the minimum possible. We can stop and return the current node's value.

4.  **Termination**: The traversal stops when we reach a `null` node (a leaf's child).

5.  **Return Value**: After the traversal is complete, `closest_val` will hold the value of the BST node that is closest to the target.

This iterative approach is efficient because it prunes half of the tree at each step, making the time complexity O(H) where H is the height of the tree. In the worst case (a skewed tree), this can be O(N), where N is the number of nodes. The space complexity is O(1) for the iterative approach.

## Example

Let's consider the following BST and a target value of `3.714286`.

```
    4
   / \
  2   5
 / \
1   3
```

1.  **Start at the root (4)**.
    *   `closest_val` is initialized to `4`.
    *   `abs(4 - 3.714286) = 0.285714`.
    *   The target `3.714286` is less than `4`, so we move to the left child.

2.  **Move to node 2**.
    *   `abs(2 - 3.714286) = 1.714286`.
    *   `1.714286` is greater than `0.285714`, so `closest_val` remains `4`.
    *   The target `3.714286` is greater than `2`, so we move to the right child.

3.  **Move to node 3**.
    *   `abs(3 - 3.714286) = 0.714286`.
    *   `0.714286` is greater than `0.285714`, so `closest_val` remains `4`.
    *   The target `3.714286` is greater than `3`, so we would move to the right child, but it's `null`.

4.  **End of traversal**.

The closest value found is `4`.
