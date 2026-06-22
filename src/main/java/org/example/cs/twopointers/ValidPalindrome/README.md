# Valid Palindrome

## Problem Statement
A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.

Given a string `s`, return `true` if it is a palindrome, or `false` otherwise.

## Solution Approach: Two Pointers
This problem can be efficiently solved using the two-pointer technique. We use one pointer starting from the beginning of the string and another from the end. We move these pointers inwards, skipping non-alphanumeric characters and comparing the alphanumeric characters in a case-insensitive manner.

1.  **Initialization**:
    *   Initialize a `left` pointer to `0` (start of the string).
    *   Initialize a `right` pointer to `s.length() - 1` (end of the string).
    *   Convert the entire string to lowercase once to handle case-insensitivity efficiently.

2.  **Iterate and Compare**:
    *   Loop while `left < right`:
        *   **Skip Non-Alphanumeric (Left)**: While `left < right` and the character at `s.charAt(left)` is not a letter or a digit, increment `left`.
        *   **Skip Non-Alphanumeric (Right)**: While `left < right` and the character at `s.charAt(right)` is not a letter or a digit, decrement `right`.
        *   **Compare Alphanumeric**: If `left < right` (meaning both pointers have found alphanumeric characters):
            *   If `s.charAt(left)` is not equal to `s.charAt(right)`, then the string is not a palindrome. Return `false`.
            *   Otherwise, the characters match. Move both pointers inwards: `left++`, `right--`.

3.  **Result**: If the loop completes without returning `false`, it means all comparable alphanumeric characters matched. Return `true`.

## Complexity Analysis
-   **Time Complexity:** O(N), where N is the length of the string. In the worst case, both pointers traverse the entire string once.
-   **Space Complexity:** O(N) if the string is converted to lowercase (creating a new string). If `Character.toLowerCase()` is used on individual characters, it's O(1).

## Example
`s = "A man, a plan, a canal: Panama"`

1.  **Lowercase**: `s = "a man, a plan, a canal: panama"`
2.  **Initialization**: `left = 0`, `right = 29`.
3.  **Iteration**:
    *   `left = 0` (`a`), `right = 29` (`a`). Match. `left = 1`, `right = 28`.
    *   `left = 1` (` `), skip. `left = 2` (`m`).
    *   `right = 28` (`m`). Match. `left = 3`, `right = 27`.
    *   ...
    *   Eventually, `left` and `right` will meet or cross.
4.  **Result**: `true`.

## Alternate Approach: Preprocessing and Comparison
An alternative approach involves first creating a "cleaned" string containing only alphanumeric characters in lowercase, and then checking if this cleaned string is a palindrome.

1.  **Preprocessing**:
    *   Create a `StringBuilder` or `StringBuffer`.
    *   Iterate through the original string `s`.
    *   For each character, check if it's alphanumeric. If it is, convert it to lowercase and append it to the `StringBuilder`.
2.  **Palindrome Check**:
    *   Convert the `StringBuilder` to a `String` (let's call it `cleaned_s`).
    *   Initialize `left = 0`, `right = cleaned_s.length() - 1`.
    *   Loop while `left < right`:
        *   If `cleaned_s.charAt(left) != cleaned_s.charAt(right)`, return `false`.
        *   `left++`, `right--`.
    *   Return `true`.

### Complexity
-   **Time Complexity:** O(N) for preprocessing and O(N) for comparison. Total O(N).
-   **Space Complexity:** O(N) to store the `cleaned_s` string.

This approach is often easier to read and write but uses additional space for the cleaned string.
