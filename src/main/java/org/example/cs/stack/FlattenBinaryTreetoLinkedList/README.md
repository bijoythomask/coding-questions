# Flatten Binary Tree to Linked List

## Problem Statement
Given the `root` of a binary tree, flatten the tree into a "linked list":
- The "linked list" should use the `right` child pointer to point to the next node in the list and the `left` child pointer should always be `null`.
- The nodes in the "linked list" should appear in the same order as a **pre-order traversal** of the binary tree.

## Solution Approach: Iterative (Morris-like Traversal)
This solution flattens the tree in-place using an iterative approach that resembles a modified Morris traversal. It processes the tree from top to bottom, left to right (pre-order style).

1.  **Iterate Through Nodes**: Start from the `root` and continue as long as `root` is not `null`.
2.  **Check for Left Child**: If the current `root` has a `left` child:
    *   **Find Rightmost in Left Subtree**: Traverse down the `left` subtree to find its rightmost node. Let's call this `rightMost`. This `rightMost` node is the predecessor of the current `root`'s `right` child in pre-order traversal.
    *   **Connect Subtrees**: Set `rightMost.right = root.right`. This connects the original `right` subtree of `root` to the end of its `left` subtree.
    *   **Move Left Subtree**: Set `root.right = root.left`. This effectively moves the entire `left` subtree to become the new `right` subtree of `root`.
    *   **Nullify Left Child**: Set `root.left = null`. The `left` child pointer should always be `null` in the flattened list.
3.  **Advance Root**: Move `root = root.right`. This moves to the next node in the flattened structure.

This process effectively "splices" the left subtree into the right subtree's position and then attaches the original right subtree to the end of the moved left subtree.

## Complexity Analysis
-   **Time Complexity:** O(N), where N is the number of nodes in the binary tree. Each node is visited at most a constant number of times (once by the main `root` pointer, and at most once by the `rightMost` finder).
-   **Space Complexity:** O(1), as the flattening is done in-place without using extra data structures like a stack or recursion stack.

## Example
Given the tree:
```
        1
       / \
      2   5
     / \   \
    3   4   6
```

1.  **`root = 1`**:
    *   `root.left` is `2`.
    *   Find rightmost in left subtree (`2`): `4`.
    *   `4.right = 5`. (Connects `4` to `5`)
    *   `1.right = 2`. (Moves `2` to `1`'s right)
    *   `1.left = null`.
    *   Tree becomes: `1 -> 2 -> 3 -> 4 -> 5 -> 6` (conceptually)
    *   `root` moves to `2`.
2.  **`root = 2`**:
    *   `root.left` is `3`.
    *   Find rightmost in left subtree (`3`): `3`.
    *   `3.right = 4`. (Connects `3` to `4`)
    *   `2.right = 3`. (Moves `3` to `2`'s right)
    *   `2.left = null`.
    *   `root` moves to `3`.
3.  **`root = 3`**: No left child. `root` moves to `4`.
4.  **`root = 4`**: No left child. `root` moves to `5`.
5.  **`root = 5`**: No left child. `root` moves to `6`.
6.  **`root = 6`**: No left child. `root` moves to `null`.
7.  Loop ends.

Final flattened list: `1 -> 2 -> 3 -> 4 -> 5 -> 6`

## Alternate Approach: Recursive Post-Order Traversal
A common recursive approach uses a post-order traversal (Right, Left, Root) to flatten the tree.

1.  **Global `prev` pointer**: Maintain a global `prev` (or `lastNode`) pointer, initialized to `null`.
2.  **Recursive Function**:
    *   Recursively call `flatten` on `root.right`.
    *   Recursively call `flatten` on `root.left`.
    *   After the recursive calls return (meaning the right and left subtrees are flattened):
        *   Set `root.right = prev`.
        *   Set `root.left = null`.
        *   Update `prev = root`.

This approach processes nodes in reverse pre-order (rightmost first), effectively building the flattened list from the end towards the root.

### Complexity
-   **Time Complexity:** O(N), as each node is visited once.
-   **Space Complexity:** O(H), where H is the height of the tree, due to the recursion stack. In the worst case (skewed tree), this can be O(N).
