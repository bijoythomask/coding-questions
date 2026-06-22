# Validate Binary Search Tree

## Problem Statement
Given the root of a binary tree, determine if it is a valid binary search tree (BST).

A valid BST is defined as follows:
- The left subtree of a node contains only nodes with keys **less than** the node's key.
- The right subtree of a node contains only nodes with keys **greater than** the node's key.
- Both the left and right subtrees must also be binary search trees.

## Solution Approach: Recursive with Range
The solution uses a recursive helper function that validates each node against a valid range `(min, max)`.

1.  **Helper Function**: A `validate(node, min, max)` function is defined to check if the subtree rooted at `node` is a valid BST within the given range.
2.  **Base Case**: If the `node` is `null`, it is a valid BST, so return `true`.
3.  **Validation Check**:
    *   Check if the current `node.val` is within the `(min, max)` range. If `node.val <= min` or `node.val >= max`, it violates the BST property, so return `false`.
4.  **Recursive Calls**:
    *   For the left child, the valid range becomes `(min, node.val)`. Recursively call `validate(node.left, min, node.val)`.
    *   For the right child, the valid range becomes `(node.val, max)`. Recursively call `validate(node.right, node.val, max)`.
5.  **Result**: The function returns `true` only if the current node and both its left and right subtrees are valid. The initial call is `validate(root, Long.MIN_VALUE, Long.MAX_VALUE)`.

## Complexity Analysis
-   **Time Complexity:** O(N), where N is the number of nodes in the tree, as each node is visited once.
-   **Space Complexity:** O(H), where H is the height of the tree, due to the recursion stack.

## Example
**Input:** `root = [5,1,4,null,null,3,6]`
**Output:** `false`
**Explanation:** The root node's value is 5, but its right child is 4. The `validate` function for the right child of 5 would be called with `validate(4, 5, Long.MAX_VALUE)`. Since `4 <= 5`, this check fails, and `false` is returned.

## Alternate Approach: In-Order Traversal
A valid BST, when traversed in-order, will produce a sequence of values that is strictly increasing.

1.  **In-Order Traversal**: Perform an in-order traversal of the tree (Left, Root, Right).
2.  **Track Previous Node**: Keep track of the value of the previously visited node.
3.  **Check for Order**: For each node visited, compare its value with the value of the previous node. If the current node's value is less than or equal to the previous node's value, the tree is not a valid BST.
4.  **Update Previous**: After visiting a node, update the `previous` value to the current node's value.

This can be done either recursively or iteratively with a stack. This approach requires O(H) space for the recursion stack or O(N) to store the traversal results.
