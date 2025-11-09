# Rotate String

## Problem Statement
Given two strings `s` and `goal`, return `true` if `s` can be transformed into `goal` by shifting its leftmost character to the rightmost position any number of times. Otherwise, return `false`.

## Solution Approach: String Concatenation and Substring Check
This problem can be solved efficiently by leveraging the property that if `goal` is a rotation of `s`, then `goal` must be a substring of `s` concatenated with itself (`s + s`).

1.  **Length Check**: First, check if the lengths of `s` and `goal` are equal. If they are not, `goal` cannot be a rotation of `s`, so return `false`.
2.  **Empty String Check**: If both `s` and `goal` are empty strings, they are considered rotations of each other. Return `true`.
3.  **Concatenation and Substring**: Concatenate `s` with itself to form `s + s`. Then, check if `goal` is a substring of `s + s`.
    *   If `goal` is found as a substring, it means `goal` can be formed by rotating `s`. Return `true`.
    *   Otherwise, return `false`.

### Why this works:
Consider `s = "abcde"`.
`s + s = "abcdeabcde"`.
Any rotation of `s` (e.g., "bcdea", "cdeab", "deabc", "eabcd") will be present as a substring within `s + s`. For example, "cdeab" starts at index 2 of "abcdeabcde".

## Complexity Analysis
-   **Time Complexity:** O(N^2), where N is the length of string `s` (and `goal`).
    *   Concatenating `s + s` takes O(N) time.
    *   The `contains` (or `indexOf`) operation for strings in Java (and many other languages) typically uses a brute-force approach or a variant, which can take up to O(N * M) time, where N is the length of the text being searched and M is the length of the pattern. In our case, `s + s` has length `2N` and `goal` has length `N`, so it's O(2N * N) = O(N^2).
-   **Space Complexity:** O(N) to store the concatenated string `s + s`.

## Example
`s = "abcde"`, `goal = "cdeab"`

1.  **Length Check**: `s.length() = 5`, `goal.length() = 5`. Lengths are equal.
2.  **Concatenation**: `s + s = "abcdeabcde"`.
3.  **Substring Check**: Is `"cdeab"` a substring of `"abcdeabcde"`? Yes.
4.  **Result**: `true`.

## Alternate Approach: Brute-Force Rotation
A more explicit, but potentially less efficient, approach involves actually performing rotations and checking for equality.

1.  **Length Check**: Same as above, if lengths differ, return `false`.
2.  **Iterate Rotations**:
    *   Loop `N` times (where `N` is the length of `s`). Each iteration represents one rotation.
    *   In each iteration, perform one left rotation on `s`. This means moving the first character to the end.
    *   After each rotation, compare the modified `s` with `goal`. If they are equal, return `true`.
3.  **No Match**: If `N` rotations are performed and no match is found, return `false`.

### Complexity
-   **Time Complexity:** O(N^2). Performing a single rotation (e.g., using `substring` and concatenation) takes O(N) time. We do this `N` times.
-   **Space Complexity:** O(N) for creating new strings during rotation.

This approach is generally less optimized than the concatenation method due to repeated string manipulations.
