# UniqueBinarySearchTreesII

## Problem Description
Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n. Return the answer in any order.

### Example 1:
Input: n = 3
Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]

### Example 2:
Input: n = 1
Output: [[1]]

## Approach
The solution uses a recursive approach to construct all unique Binary Search Trees (BSTs). The core of the solution is a helper function that generates all possible BSTs for a given range of numbers, `[start, end]`.

For each number `i` in the range `[start, end]`, we consider it as the root of a BST. The properties of a BST dictate that all values in the left subtree must be smaller than the root, and all values in the right subtree must be larger.

Therefore, for a root `i`:
- The left subtree will be formed from the numbers in the range `[start, i - 1]`.
- The right subtree will be formed from the numbers in the range `[i + 1, end]`.

We make recursive calls to generate all possible unique left subtrees and all possible unique right subtrees. Then, we combine each left subtree with each right subtree to form all possible BSTs with `i` as the root.

The base case for the recursion is when `start > end`, which signifies an empty range. In this case, we return a list containing a single `null` element, representing an empty tree. This `null` is crucial for the construction of trees with missing left or right children.

The main function initializes the process by calling the recursive helper with the range `[1, n]`.

## Sample Input/Output
### Example 1:
**Input:** `n = 3`
**Output:** `[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]`

### Example 2:
**Input:** `n = 1`
**Output:** `[[1]]`
