# Unique Binary Search Trees II

## Problem Statement
Given an integer `n`, return all the structurally unique BST's (binary search trees), which have exactly `n` nodes of unique values from 1 to `n`. Return the answer in any order.

## Solution Approach
The solution uses a recursive approach to construct all unique Binary Search Trees (BSTs). The core of the solution is a helper function that generates all possible BSTs for a given range of numbers, `[start, end]`.

For each number `i` in the range `[start, end]`, we consider it as the root of a BST. The properties of a BST dictate that all values in the left subtree must be smaller than the root, and all values in the right subtree must be larger.

Therefore, for a root `i`:
- The left subtree will be formed from the numbers in the range `[start, i - 1]`.
- The right subtree will be formed from the numbers in the range `[i + 1, end]`.

We make recursive calls to generate all possible unique left subtrees and all possible unique right subtrees. Then, we combine each left subtree with each right subtree to form all possible BSTs with `i` as the root.

The base case for the recursion is when `start > end`, which signifies an empty range. In this case, we return a list containing a single `null` element, representing an empty tree. This `null` is crucial for the construction of trees with missing left or right children.

The main function initializes the process by calling the recursive helper with the range `[1, n]`.

## Complexity Analysis
-   **Time Complexity:** The number of unique BSTs is given by the Catalan number, C(n). The complexity is roughly O(n * C(n)), which is exponential.
-   **Space Complexity:** O(n * C(n)) to store the generated trees. The recursion stack depth is O(n).

## Example
**Input:** `n = 3`
**Output:** `[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]`

## Alternate Approach: Dynamic Programming with Memoization

The recursive solution can be optimized by using memoization to store the results for subproblems that have already been solved.

1.  **Memoization Cache**: Use a 2D array or a hash map to store the list of generated trees for each `(start, end)` range.
2.  **Recursive Helper**: Before computing the trees for a range, check if the result is already in the cache.
    *   If it is, return the cached result.
    *   If not, compute the result as in the recursive approach and store it in the cache before returning.

This optimization avoids recomputing the same subproblems multiple times, which can significantly improve performance, although the overall time and space complexity remain exponential due to the nature of the problem.
