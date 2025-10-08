# Longest Repeating Character Replacement

## Problem Description
You are given a string `s` and an integer `k`. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most `k` times.

The goal is to find the length of the longest substring containing the same letter that you can get after performing the operations.

## Approach
The solution uses a sliding window approach to solve this problem efficiently. A window slides over the string, and we keep track of the frequency of each character within that window.

Here's a step-by-step breakdown of the algorithm:
1.  Initialize an array `count` of size 26 to store the frequency of each uppercase English letter.
2.  Initialize `maxCount` to 0, which will store the count of the most frequent character in the current window.
3.  Initialize `left` to 0, which is the left pointer of the sliding window.
4.  Initialize `result` to 0, which will store the length of the longest valid substring found so far.
5.  Iterate through the string with the `right` pointer from left to right:
    -  Increment the count of the character at the `right` pointer and update `maxCount`.
    -  Check if the current window is valid. A window is valid if the number of characters that need to be replaced is less than or equal to `k`. The number of characters to be replaced is `(right - left + 1) - maxCount`.
    -  If the window is not valid, shrink the window from the left by decrementing the count of the character at the `left` pointer and incrementing `left`.
    -  Update `result` with the maximum length of the valid window found so far, which is `right - left + 1`.
6.  Return `result`.

This approach ensures that we find the longest possible substring by expanding the window to the right and shrinking it from the left only when necessary, thus maintaining the sliding window properties and achieving a time complexity of O(N), where N is the length of the string.

## Sample Input/Output

### Example 1:
-   **Input:** `s = "AABABBA"`, `k = 1`
-   **Output:** `4`
-   **Explanation:** The longest substring we can form is of length 4. For instance, consider the substring "AABA". The window size is 4, and the most frequent character 'A' appears 3 times. The number of characters to replace is `4 - 3 = 1`, which is within our limit `k`. By replacing one 'B' with 'A', we get "AAAA".
