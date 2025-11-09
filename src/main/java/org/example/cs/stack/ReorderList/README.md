# Reorder List

## Problem Statement
You are given the `head` of a singly linked-list. The list can be represented as:
`L0 → L1 → … → Ln - 1 → Ln`

Reorder the list to be on the following form:
`L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …`

You may not modify the values in the list's nodes. Only nodes themselves may be changed.

## Solution Approach: Three-Step Process (Find Middle, Reverse, Merge)
This efficient solution reorders the linked list in three main steps:

1.  **Find the Middle of the Linked List**:
    *   Use the "fast and slow pointers" technique. Initialize `slow = head` and `fast = head`.
    *   Move `slow` one step at a time and `fast` two steps at a time.
    *   When `fast` reaches the end (or `fast.next` is null), `slow` will be at the middle of the list.
    *   The node just before `slow` (let's call it `prev_slow`) will be the end of the first half.

2.  **Reverse the Second Half**:
    *   Break the list into two halves by setting `prev_slow.next = null`.
    *   Take the second half (starting from `slow`) and reverse it. This can be done iteratively using three pointers (`prev`, `curr`, `next_node`).

3.  **Merge the Two Halves**:
    *   Now we have `head` (the first half) and `reversed_second_half_head` (the reversed second half).
    *   Merge them by alternating nodes:
        *   Take a node from the first half.
        *   Take a node from the reversed second half.
        *   Link them: `first_half_node -> second_half_node -> next_first_half_node -> next_second_half_node ...`
    *   Continue until one of the halves is exhausted.

## Complexity Analysis
-   **Time Complexity:** O(N), where N is the number of nodes in the linked list. Each of the three steps (finding middle, reversing, merging) involves traversing the list or a portion of it once.
-   **Space Complexity:** O(1), as we only use a few pointers.

## Example
`head = 1 → 2 → 3 → 4 → 5`

1.  **Find Middle**:
    *   `slow` points to `3`. `fast` points to `null`.
    *   The list is split into `1 → 2 → 3` and `4 → 5`.
    *   `slow.next` (which is `4`) is set to `null` to break the link.
    *   First half: `1 → 2 → 3`. Second half: `4 → 5`.

2.  **Reverse Second Half**:
    *   `4 → 5` becomes `5 → 4`.

3.  **Merge**:
    *   `first = 1 → 2 → 3`, `second = 5 → 4`
    *   `1 → 5`
    *   `1 → 5 → 2`
    *   `1 → 5 → 2 → 4`
    *   `1 → 5 → 2 → 4 → 3`
    *   The `second` list is exhausted.

Final reordered list: `1 → 5 → 2 → 4 → 3`

## Alternate Approach: Using a Stack
An alternative approach leverages a stack to store the second half of the linked list, which simplifies the reversal step.

1.  **Find Middle**: Use fast and slow pointers to find the middle of the linked list.
2.  **Push Second Half to Stack**: Iterate from the middle of the list to the end, pushing each node onto a stack.
3.  **Merge with Stack**:
    *   Initialize `current = head`.
    *   While the stack is not empty:
        *   Pop a node (`node_from_stack`) from the stack.
        *   Store `current.next` in a temporary variable.
        *   Set `current.next = node_from_stack`.
        *   Set `node_from_stack.next = temp`.
        *   Advance `current = temp`.
    *   Handle the last node's `next` pointer to be `null` to avoid cycles.

### Complexity
-   **Time Complexity:** O(N), as we traverse the list multiple times.
-   **Space Complexity:** O(N) to store the second half of the list in the stack.

This approach is conceptually simpler for the reversal and merging, but uses O(N) space due to the stack.
