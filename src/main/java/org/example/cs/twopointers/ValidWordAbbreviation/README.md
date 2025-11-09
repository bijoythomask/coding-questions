# Valid Word Abbreviation

## Problem Statement
Given a non-empty string `word` and an abbreviation `abbr`, return whether the string matches the given abbreviation.

A string can be abbreviated by replacing any number of non-adjacent, non-empty substrings with their lengths. The lengths should not have leading zeros.

For example, a string such as "substitution" could be abbreviated as (but not limited to):
- "s10n" ("s ubstitutio n")
- "sub4u4" ("sub stit u tion")
- "12" ("substitution")
- "su3i1u2on" ("su bst i t u ti on")
- "substitution" (no substrings replaced)

The following are not valid abbreviations:
- "s55n" ("s ubsti tutio n", the replaced substrings are adjacent)
- "s010n" (has leading zeros)

## Solution Approach: Two Pointers
This problem can be efficiently solved using a two-pointer approach, one for the `word` string and one for the `abbr` string. We iterate through both strings simultaneously, comparing characters or processing numbers in the abbreviation.

1.  **Initialization**:
    *   Initialize `i` to `0` (pointer for `word`).
    *   Initialize `j` to `0` (pointer for `abbr`).

2.  **Iterate and Compare**:
    *   Loop while `i < word.length()` and `j < abbr.length()`:
        *   **If `abbr.charAt(j)` is a digit**:
            *   **Check for Leading Zero**: If `abbr.charAt(j)` is `'0'`, it's an invalid abbreviation. Return `false`.
            *   **Parse Number**: Extract the full number from `abbr` starting at `j`. Store it in `num`. Advance `j` past all digits.
            *   **Advance `word` pointer**: Increment `i` by `num`. This simulates skipping `num` characters in the `word`.
        *   **If `abbr.charAt(j)` is a character**:
            *   **Compare Characters**: If `word.charAt(i)` is not equal to `abbr.charAt(j)`, then there's a mismatch. Return `false`.
            *   **Advance both pointers**: Increment `i` and `j` by `1`.

3.  **Final Check**:
    *   After the loop, both pointers `i` and `j` must have reached the end of their respective strings for a valid match.
    *   Return `true` if `i == word.length()` AND `j == abbr.length()`. Otherwise, return `false`.

## Complexity Analysis
-   **Time Complexity:** O(L), where L is the length of the `word` (or `abbr`, whichever is longer). In the worst case, we iterate through both strings once.
-   **Space Complexity:** O(1), as we only use a few variables for pointers and number parsing.

## Example
`word = "substitution"`, `abbr = "s10n"`

1.  **Initialization**: `i = 0`, `j = 0`.
2.  **Iteration**:
    *   `j = 0`, `abbr.charAt(0) = 's'`. `word.charAt(0) = 's'`. Match. `i = 1`, `j = 1`.
    *   `j = 1`, `abbr.charAt(1) = '1'`. Is a digit. Not '0'.
        *   Parse number: `num = 10`. `j` advances to `3`.
        *   `i` advances by `10`. `i = 1 + 10 = 11`.
    *   `j = 3`, `abbr.charAt(3) = 'n'`. `word.charAt(11) = 'n'`. Match. `i = 12`, `j = 4`.
3.  **Final Check**: `i = 12` (end of `word`), `j = 4` (end of `abbr`). Both reached end. Return `true`.

## Alternate Approach: Recursive Backtracking
A recursive approach can also be used, though it might be less efficient due to recursion overhead.

1.  **Base Cases**:
    *   If both `word` and `abbr` pointers reach their ends, return `true`.
    *   If one pointer reaches its end but the other doesn't, return `false`.
2.  **Recursive Step**:
    *   If `abbr.charAt(j)` is a digit:
        *   Handle leading zero.
        *   Parse the number `num`.
        *   Recursively call with `word_pointer + num` and `abbr_pointer + length_of_num`.
    *   If `abbr.charAt(j)` is a character:
        *   Compare `word.charAt(i)` and `abbr.charAt(j)`.
        *   If they match, recursively call with `word_pointer + 1` and `abbr_pointer + 1`.
        *   If they don't match, return `false`.

### Complexity
-   **Time Complexity:** O(L) in the best case, but can be higher due to recursion overhead.
-   **Space Complexity:** O(L) for the recursion stack.

This approach is generally more complex to implement correctly and less performant than the iterative two-pointer method.
