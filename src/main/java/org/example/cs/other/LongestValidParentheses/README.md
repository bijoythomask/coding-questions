# Longest Valid Parentheses

## Problem Statement
Given a string containing just the characters `'('` and `')'`, find the length of the longest valid (well-formed) parentheses substring.

## Solution Approach: Two-Pass Linear Scan (O(1) Space)
This solution is a clever approach that achieves O(N) time complexity with O(1) space complexity by performing two linear scans.

The core idea is that a valid parentheses substring will always have an equal number of opening and closing parentheses. We can count these.

1.  **First Pass (Left to Right)**:
    *   Initialize `left = 0`, `right = 0`, `max_len = 0`.
    *   Iterate through the string from left to right.
    *   If `s.charAt(i) == '('`, increment `left`.
    *   If `s.charAt(i) == ')'`, increment `right`.
    *   If `left == right`, it means we have found a valid parentheses substring. Its length is `2 * right`. Update `max_len = Math.max(max_len, 2 * right)`.
    *   If `right > left`, it means we have more closing parentheses than opening ones. This indicates an invalid sequence, and any valid substring cannot include this point. Reset `left = 0`, `right = 0`.

2.  **Second Pass (Right to Left)**:
    *   Reset `left = 0`, `right = 0`.
    *   Iterate through the string from right to left.
    *   If `s.charAt(i) == '('`, increment `left`.
    *   If `s.charAt(i) == ')'`, increment `right`.
    *   If `left == right`, it means we have found a valid parentheses substring. Its length is `2 * left`. Update `max_len = Math.max(max_len, 2 * left)`.
    *   If `left > right`, it means we have more opening parentheses than closing ones. This indicates an invalid sequence. Reset `left = 0`, `right = 0`.

3.  **Return Result**: After both passes, `max_len` will hold the length of the longest valid parentheses substring.

### Why two passes?
The first pass handles cases like `(()`. Here, `left` becomes `2`, `right` becomes `1`. `right > left` is false, so `left` and `right` are not reset. When `left == right` (for `()`), `max_len` is updated. But for `((()`, the `max_len` would be `0` after the first pass. The second pass (right to left) correctly identifies `()` as a valid substring. For `()())`, the first pass correctly finds `4` for `()()`. For `(()`, the first pass finds `2` for `()`. The second pass for `(()` (from right to left) would be `) -> right=1`, `( -> left=1`. `left==right`, `max_len = max(0, 2*1) = 2`.

## Complexity Analysis
-   **Time Complexity:** O(N), where N is the length of the string. We perform two linear scans of the string.
-   **Space Complexity:** O(1), as we only use a few integer variables.

## Example
`s = ")()())"`

1.  **First Pass (Left to Right)**:
    *   `i=0, s[0]=')'`: `right=1`. `right > left`. Reset `left=0, right=0`. `max_len=0`.
    *   `i=1, s[1]='('`: `left=1`.
    *   `i=2, s[2]=')'`: `right=1`. `left==right`. `max_len=max(0, 2*1)=2`.
    *   `i=3, s[3]='('`: `left=2`.
    *   `i=4, s[4]=')'`: `right=2`. `left==right`. `max_len=max(2, 2*2)=4`.
    *   `i=5, s[5]=')'`: `right=3`. `right > left`. Reset `left=0, right=0`. `max_len=4`.
    *   End of first pass. `max_len = 4`.

2.  **Second Pass (Right to Left)**:
    *   Reset `left=0, right=0`. `max_len=4`.
    *   `i=5, s[5]=')'`: `right=1`.
    *   `i=4, s[4]=')'`: `right=2`.
    *   `i=3, s[3]='('`: `left=1`.
    *   `i=2, s[2]=')'`: `right=3`. `left < right`.
    *   `i=1, s[1]='('`: `left=2`. `left < right`.
    *   `i=0, s[0]=')'`: `right=4`. `left < right`.
    *   End of second pass. `max_len = 4`.

Result: `4`.

## Alternate Approach 1: Using a Stack
A common approach uses a stack to keep track of the indices of opening parentheses.

1.  **Initialization**:
    *   Initialize `max_len = 0`.
    *   Create a stack and push `-1` onto it (this acts as a base for calculating lengths).
2.  **Iterate Through String**:
    *   For each character `s.charAt(i)`:
        *   If `s.charAt(i) == '('`: Push `i` onto the stack.
        *   If `s.charAt(i) == ')'`:
            *   Pop from the stack.
            *   If the stack becomes empty, it means the current `')'` doesn't have a matching `'('` within the current valid sequence. Push `i` onto the stack to mark a new base.
            *   If the stack is not empty, it means we found a valid pair. The length of this valid substring is `i - stack.peek()`. Update `max_len = Math.max(max_len, i - stack.peek())`.

### Complexity
-   **Time Complexity:** O(N), as we iterate through the string once.
-   **Space Complexity:** O(N) in the worst case (e.g., `((((((`), to store indices in the stack.

## Alternate Approach 2: Dynamic Programming
Dynamic programming can also solve this problem.

1.  **`dp` Array**: Create a `dp` array of size `N`, where `dp[i]` stores the length of the longest valid parentheses substring ending at index `i`.
2.  **Base Cases**: `dp[0] = 0`.
3.  **Recurrence Relation**:
    *   If `s.charAt(i) == '('`, then `dp[i] = 0`.
    *   If `s.charAt(i) == ')'`:
        *   If `s.charAt(i-1) == '('`: `dp[i] = (i >= 2 ? dp[i-2] : 0) + 2`. (e.g., `...()`)
        *   If `s.charAt(i-1) == ')'` and `s.charAt(i - dp[i-1] - 1) == '('`:
            `dp[i] = dp[i-1] + 2 + (i - dp[i-1] >= 2 ? dp[i - dp[i-1] - 2] : 0)`. (e.g., `...((()))`)
4.  **Result**: The maximum value in the `dp` array is the answer.

### Complexity
-   **Time Complexity:** O(N), as we iterate through the string once.
-   **Space Complexity:** O(N) for the `dp` array.
