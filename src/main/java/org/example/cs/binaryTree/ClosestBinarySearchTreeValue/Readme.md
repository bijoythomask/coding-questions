# Closest Binary Search Tree Value

## Problem Statement

Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

## Solution Approach

We can solve this problem by traversing the binary search tree and keeping track of the closest value found so far.

1.  **Initialization**: Initialize a variable `closest` to the value of the root node.

2.  **Traversal**: Start from the root of the BST.
    *   Compare the absolute difference between the current node's value and the target with the absolute difference between `closest` and the target. If the current node is closer, update `closest`.
    *   If the target is less than the current node's value, move to the left child.
    *   If the target is greater than the current node's value, move to the right child.
    *   If the target is equal to the current node's value, we have found the exact value, and we can return it.

3.  **Result**: When the traversal is complete (we reach a null node), the `closest` variable will hold the value of the node in the BST that is closest to the target.

This approach has a time complexity of **O(H)**, where H is the height of the tree, and a space complexity of **O(1)**.

## Example

`root = [4,2,5,1,3], target = 3.714286`

1.  Start at root `4`. `closest = 4`.
2.  `3.714286 < 4`, move to the left child `2`.
3.  `|2 - 3.714286| = 1.714286`, `|4 - 3.714286| = 0.285714`. `closest` remains `4`.
4.  `3.714286 > 2`, move to the right child `3`.
5.  `|3 - 3.714286| = 0.714286`. `closest` remains `4`.
6.  `3.714286 > 3`, move to the right child, which is null.

Traversal ends. The closest value is `4`.

## Alternate Approach: Recursive Traversal

A recursive approach can also be used to solve this problem. A helper function can be defined that takes the current node and the target as input.

1.  **Base Case**: If the current node is null, return the `closest` value found so far.
2.  **Update Closest**: Compare the current node's value with the `closest` value found so far and update `closest` if the current node is closer to the target.
3.  **Recursive Step**:
    *   If the target is less than the current node's value, recursively call the helper function on the left child.
    *   If the target is greater than the current node's value, recursively call the helper function on the right child.
    *   If the target is equal to the current node's value, return the current node's value.

This approach also has a time complexity of **O(H)**, but the space complexity is **O(H)** due to the recursion stack.
