# Convert Sorted Array to Binary Search Tree

## Problem Statement
Given an integer array `nums` where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.

## Solution Approach
The solution uses a recursive approach to build the tree. Since the array is sorted, the middle element can be chosen as the root of the tree. This ensures that the tree remains height-balanced.

1.  A helper function is defined that takes the array and the left and right indices as input.
2.  **Base Case**: If the left index is greater than the right index, it means there are no elements to process, so we return `null`.
3.  **Find Middle**: Calculate the middle index of the current subarray. This middle element will become the root of the current subtree.
4.  **Create Node**: Create a new `TreeNode` with the value of the middle element.
5.  **Recursive Calls**:
    *   Recursively call the helper function for the left half of the array (from `left` to `mid - 1`) to build the left subtree.
    *   Recursively call the helper function for the right half of the array (from `mid + 1` to `right`) to build the right subtree.
6.  **Return Node**: Return the created node, which is the root of the constructed subtree.

The initial call to the helper function will be with the entire array (indices `0` to `nums.length - 1`).

## Complexity Analysis
-   **Time Complexity:** O(N), where N is the number of elements in the array. Each element is visited once to create a node.
-   **Space Complexity:** O(log N) in a balanced tree, which is the height of the tree, due to the recursion stack. In the worst case (a skewed tree), it could be O(N).

## Example
Given `nums = [-10, -3, 0, 5, 9]`:

1.  The middle element is `0`. This becomes the root.
2.  The left subtree is built from `[-10, -3]`. The middle element is `-3`.
3.  The right subtree is built from `[5, 9]`. The middle element is `9`.
4.  This process continues until all elements are placed in the tree.

The resulting tree would be:
```
      0
     / \
   -3   9
   /   /
 -10  5
```

## Alternate Approach: Iterative with Stacks
An iterative approach can also be used with stacks to mimic the recursion.

1.  Create a stack to store ranges (left and right indices) and another stack to store the nodes.
2.  Push the initial range (`0` to `nums.length - 1`) and a new root node onto the stacks.
3.  While the stacks are not empty, pop a range and a node.
4.  Calculate the middle index and set the node's value.
5.  If the left half of the range is valid, create a new node for the left child, push the left range and the new node onto the stacks.
6.  If the right half of the range is valid, create a new node for the right child, push the right range and the new node onto the stacks.

This approach avoids deep recursion but is generally more complex to implement.
