# Find the Celebrity

## Problem Statement
Suppose you are at a party with `n` people (labeled from `0` to `n - 1`), and among them, there may exist one celebrity. The definition of a celebrity is that all the other `n - 1` people know the celebrity, but the celebrity does not know any of them.

You are given a helper function `knows(a, b)` which returns `true` if person `a` knows person `b`, and `false` otherwise. Your task is to find the celebrity. If there is no celebrity, return `-1`.

## Solution Approach: Two-Pass Algorithm
This problem can be solved efficiently in two passes. The first pass identifies a single potential celebrity candidate, and the second pass verifies if this candidate is indeed a celebrity.

### Pass 1: Find a Candidate
A key observation is that if `knows(a, b)` is `true`, then `a` cannot be a celebrity (because a celebrity knows no one). If `knows(a, b)` is `false`, then `b` cannot be a celebrity (because everyone must know the celebrity).

We can use this logic to eliminate `n-1` people and find one single candidate.

1.  Initialize `candidate = 0`.
2.  Iterate from `i = 1` to `n - 1`.
3.  In each iteration, call `knows(candidate, i)`.
    *   If it returns `true`, it means our current `candidate` knows someone, so they cannot be a celebrity. We update our `candidate` to `i`, as `i` is now a more likely candidate.
    *   If it returns `false`, it means `i` is not known by our `candidate`, so `i` cannot be the celebrity. We keep our current `candidate`.

After this first pass, we are left with a single `candidate`. This person is the *only possible* celebrity.

### Pass 2: Verify the Candidate
The first pass does not guarantee that the candidate is a celebrity. We must now verify two conditions:
1.  The candidate does not know anyone else.
2.  Everyone else knows the candidate.

1.  Iterate from `i = 0` to `n - 1`.
2.  For each `i` (where `i` is not the candidate):
    *   Check if `knows(candidate, i)` is `true` (the candidate knows someone) OR if `knows(i, candidate)` is `false` (someone doesn't know the candidate).
    *   If either of these conditions is met, our candidate is not a celebrity. Return `-1`.
3.  If the loop completes without returning, the candidate is a valid celebrity. Return `candidate`.

## Complexity Analysis
-   **Time Complexity:** O(n). The first pass makes `n-1` calls to `knows`, and the second pass makes approximately `2(n-1)` calls. This results in a linear time complexity.
-   **Space Complexity:** O(1), as we only use a few variables to store the candidate and loop counters.

## Example
Let's say `n = 3` and `knows(1, 0)`, `knows(1, 2)`, `knows(0, 1)`.
Celebrity is `1`.

**Pass 1:**
- `candidate = 0`.
- `i = 1`: `knows(0, 1)` is `true`. `candidate` becomes `1`.
- `i = 2`: `knows(1, 2)` is `true`. `candidate` becomes `2`.
- Final candidate from Pass 1 is `2`.

**Pass 2 (verifying candidate `2`):**
- `i = 0`: `knows(2, 0)` is `false` (let's assume). `knows(0, 2)` is `false`. The condition `!knows(i, candidate)` is met. So `2` is not a celebrity. Return `-1`.

This shows the importance of the verification step. If the celebrity was `1`, the first pass might still result in a different candidate. The correct logic will always find the right one if one exists.

## Alternate Approach: Brute-Force
A less optimal, brute-force approach would be to check for each person if they are a celebrity.

1.  Iterate through each person `i` from `0` to `n-1`.
2.  For each person `i`, assume they are a celebrity and check if they satisfy the conditions:
    *   Check if `knows(i, j)` is `false` for all `j != i`.
    *   Check if `knows(j, i)` is `true` for all `j != i`.
3.  If both conditions hold, `i` is the celebrity. Return `i`.
4.  If the outer loop finishes without finding a celebrity, return `-1`.

This approach has a time complexity of **O(n^2)** because for each of the `n` people, we iterate through all other `n` people.
