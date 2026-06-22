# Find the Index of the First Occurrence of a String

## Problem Statement
Given two strings `haystack` and `needle`, return the index of the first occurrence of `needle` in `haystack`, or `-1` if `needle` is not part of `haystack`.

## Solution Approach: Brute-Force (Sliding Window with Substring Comparison)
This solution uses a straightforward brute-force approach, often conceptualized as a sliding window. It iterates through all possible starting positions in the `haystack` where the `needle` could potentially match.

1.  **Edge Case**: If `needle` is an empty string, it is considered to occur at index `0`. Return `0`.
2.  **Iterate Through Haystack**:
    *   Use a loop with an index `i` that represents the potential starting position of `needle` in `haystack`.
    *   The loop should run from `0` up to `haystack.length() - needle.length()`. This ensures that there's enough remaining length in `haystack` to accommodate `needle`.
3.  **Substring Comparison**:
    *   For each `i`, extract a substring from `haystack` starting at `i` with a length equal to `needle.length()`.
    *   Compare this extracted substring with `needle`.
    *   If they are equal, a match is found. Return `i`.
4.  **No Match**: If the loop completes without finding any match, it means `needle` is not present in `haystack`. Return `-1`.

## Complexity Analysis
-   **Time Complexity:** O((N - L + 1) * L), where N is the length of `haystack` and L is the length of `needle`. In the worst case (e.g., `haystack = "aaaaa", needle = "aaa"`), the substring comparison happens for almost every possible starting position, and each comparison takes O(L) time.
-   **Space Complexity:** O(L) if `haystack.substring()` creates a new string for each comparison. If the comparison is done character by character without creating new substrings, it would be O(1).

## Example
`haystack = "hello"`, `needle = "ll"`

1.  `needle` is not empty.
2.  **Loop `i` from `0` to `haystack.length() - needle.length()` (i.e., `0` to `5 - 2 = 3`)**:
    *   `i = 0`: `haystack.substring(0, 2)` is `"he"`. `"he" != "ll"`.
    *   `i = 1`: `haystack.substring(1, 3)` is `"el"`. `"el" != "ll"`.
    *   `i = 2`: `haystack.substring(2, 4)` is `"ll"`. `"ll" == "ll"`. Match found. Return `2`.

## Alternate Approach: Knuth-Morris-Pratt (KMP) Algorithm
The KMP algorithm is a highly optimized string-matching algorithm that avoids redundant comparisons by pre-processing the `needle` string to build a "longest proper prefix suffix" (LPS) array.

1.  **Build LPS Array**: The LPS array (also known as the prefix function or failure function) for `needle` stores, for each index `i`, the length of the longest proper prefix of `needle[0...i]` that is also a suffix of `needle[0...i]`. This array helps to know how many characters to skip when a mismatch occurs.
2.  **Pattern Matching**: Use two pointers, one for `haystack` and one for `needle`. When a mismatch occurs, instead of shifting the `needle` by just one position, the LPS array tells us how much to shift to avoid re-checking already matched characters.

### Complexity
-   **Time Complexity:** O(N + L), where N is the length of `haystack` and L is the length of `needle`. Building the LPS array takes O(L), and the matching process takes O(N).
-   **Space Complexity:** O(L) for the LPS array.

KMP is significantly more efficient than the brute-force approach for large strings, especially when the `needle` has repeating patterns.
