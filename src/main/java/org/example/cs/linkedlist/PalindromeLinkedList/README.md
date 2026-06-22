# Palindrome Linked List

## Problem Statement
Given the `head` of a singly linked list, return `true` if it is a palindrome or `false` otherwise.

## Solution Approach: Reverse Half and Compare (O(1) Space)
This efficient solution uses the "fast and slow pointers" technique to find the middle of the linked list and simultaneously reverses the first half. Then, it compares the reversed first half with the second half of the list.

1.  **Find Middle and Reverse First Half**:
    *   Initialize three pointers: `slow = head`, `fast = head`, and `prev = null`.
    *   Iterate while `fast` and `fast.next` are not `null`:
        *   Move `fast` two steps: `fast = fast.next.next`.
        *   Simultaneously, reverse the `slow` pointer's `next` link:
            *   Store `slow.next` in a temporary variable `next`.
            *   Set `slow.next = prev`.
            *   Update `prev = slow`.
            *   Move `slow = next`.
    *   After this loop, `slow` will be at the start of the second half, and `prev` will be at the head of the reversed first half.

2.  **Handle Odd Length List**:
    *   If `fast` is not `null` after the loop, it means the original linked list had an odd number of nodes. In this case, the `slow` pointer is currently at the middle node, which should be skipped for comparison. So, advance `slow` one step: `slow = slow.next`.

3.  **Compare Halves**:
    *   Now, `prev` points to the head of the reversed first half, and `slow` points to the head of the second half (or the node after the middle for odd-length lists).
    *   Iterate while `prev` is not `null` (or `slow` is not `null`, as they should be of equal length for comparison):
        *   If `prev.val != slow.val`, the list is not a palindrome. Return `false`.
        *   Advance both pointers: `prev = prev.next`, `slow = slow.next`.

4.  **Result**: If the loop completes, it means both halves matched. Return `true`.

## Complexity Analysis
-   **Time Complexity:** O(N), where N is the number of nodes in the linked list. We traverse the list once to find the middle and reverse the first half, and then once again to compare the two halves.
-   **Space Complexity:** O(1), as we only use a few pointers.

## Example
`head = 1 -> 2 -> 2 -> 1`

1.  **Init**: `slow = 1`, `fast = 1`, `prev = null`.
2.  **Loop 1**:
    *   `fast` moves to `2` (second `2`).
    *   `next = 2` (first `2`).
    *   `slow.next = null` (`1 -> null`).
    *   `prev = 1`.
    *   `slow = 2` (first `2`).
    *   State: `prev: null <- 1`, `slow: 2 -> 2 -> 1`, `fast: 2 -> 1`
3.  **Loop 2**:
    *   `fast` moves to `null` (from `1` to `null`). Loop terminates.
    *   State: `prev: null <- 1 <- 2`, `slow: 2 -> 1`, `fast: null`
4.  **Odd Length Check**: `fast` is `null`. List has even length. No `slow = slow.next`.
5.  **Compare**:
    *   `prev = 2`, `slow = 2`. `prev.val == slow.val`.
    *   `prev` moves to `1`, `slow` moves to `1`. `prev.val == slow.val`.
    *   `prev` moves to `null`, `slow` moves to `null`. Loop terminates.
6.  **Result**: `true`.

## Alternate Approach: Using a Stack or Array
A simpler, but less space-efficient, approach involves storing the linked list values and then checking for palindrome property.

1.  **Store Values**: Traverse the linked list and push each node's value onto a `Stack` (or add to an `ArrayList`).
2.  **Compare**: Traverse the linked list again from the `head`. For each node, pop a value from the stack and compare it with the current node's value. If they don't match at any point, it's not a palindrome.
3.  **Result**: If all values match, it's a palindrome.

### Complexity
-   **Time Complexity:** O(N) to traverse and store, O(N) to traverse and compare. Total O(N).
-   **Space Complexity:** O(N) to store all node values in the stack or array.
