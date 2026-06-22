# Assign Cookies

## Problem Statement
Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie.

Each child `i` has a greed factor `g[i]`, which is the minimum size of a cookie that the child will be content with; and each cookie `j` has a size `s[j]`. If `s[j] >= g[i]`, we can assign the cookie `j` to the child `i`, and the child `i` will be content. Your goal is to maximize the number of your content children and output the maximum number.

## Solution Approach: Greedy with Two Pointers
This problem can be efficiently solved using a greedy approach combined with two pointers after sorting both the children's greed factors and the cookie sizes. The intuition behind the greedy strategy is to try to satisfy the child with the smallest greed factor using the smallest possible cookie that can satisfy them.

1.  **Sort Inputs**:
    *   Sort the `g` array (greed factors) in ascending order.
    *   Sort the `s` array (cookie sizes) in ascending order.

2.  **Two Pointers**:
    *   Initialize a pointer `child_idx` to `0` for the `g` array.
    *   Initialize a pointer `cookie_idx` to `0` for the `s` array.

3.  **Iterate and Assign**:
    *   Loop while `child_idx` is within the bounds of `g` and `cookie_idx` is within the bounds of `s`:
        *   If the current cookie `s[cookie_idx]` is greater than or equal to the current child's greed factor `g[child_idx]`:
            *   This cookie can satisfy this child. Assign the cookie to the child by incrementing `child_idx`. This child is now content.
        *   In either case (whether the cookie was assigned or not), move to the next cookie by incrementing `cookie_idx`. We always try to use the smallest available cookie.

4.  **Result**: The final value of `child_idx` represents the maximum number of content children.

## Complexity Analysis
-   **Time Complexity:** O(N log N + M log M), where N is the number of children and M is the number of cookies. This is dominated by the sorting step. The two-pointer iteration takes O(N + M) time.
-   **Space Complexity:** O(1) if the sorting algorithm used is in-place. Otherwise, it depends on the sorting algorithm's space complexity.

## Example
`g = [1, 2, 3]`, `s = [1, 1]`

1.  **Sort**: `g = [1, 2, 3]`, `s = [1, 1]` (already sorted).
2.  **Init Pointers**: `child_idx = 0`, `cookie_idx = 0`.
3.  **Iteration**:
    *   `child_idx = 0` (`g[0] = 1`), `cookie_idx = 0` (`s[0] = 1`).
    *   `s[0] (1) >= g[0] (1)` is true. Increment `child_idx` to `1`.
    *   Increment `cookie_idx` to `1`.
    *   `child_idx = 1` (`g[1] = 2`), `cookie_idx = 1` (`s[1] = 1`).
    *   `s[1] (1) >= g[1] (2)` is false.
    *   Increment `cookie_idx` to `2`.
    *   `cookie_idx` is now `2`, which is out of bounds for `s`. Loop terminates.

4.  **Result**: Return `child_idx = 1`.

## Alternate Approach: Brute Force
A brute-force approach would involve trying all possible assignments of cookies to children and finding the maximum number of content children.

1.  Generate all permutations of assigning cookies to children.
2.  For each permutation, count how many children are content.
3.  Keep track of the maximum count.

### Complexity
-   **Time Complexity:** Extremely high, involving factorials (N! or M!). This is not practical for any reasonably sized input.
-   **Space Complexity:** Depends on the implementation, but generally high to store permutations.

This approach is highly inefficient and serves mainly as a theoretical contrast to the greedy solution.
