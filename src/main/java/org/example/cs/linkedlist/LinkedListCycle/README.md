# Linked List Cycle

## Problem Statement
Given the `head` of a linked list, return `true` if there is a cycle in the linked list. Otherwise, return `false`.

A cycle in a linked list means that some node in the list can be reached again by continuously following the `next` pointers. Internally, `pos` is used to denote the index of the node that tail's `next` pointer is connected to. Note that `pos` is not passed as a parameter.

## Solution Approach: Floyd's Cycle-Finding Algorithm (Fast and Slow Pointers)
This problem is a classic application of Floyd's Cycle-Finding Algorithm, also known as the "tortoise and hare" algorithm. It uses two pointers, one moving faster than the other, to detect a cycle.

1.  **Initialization**:
    *   Initialize a `slow` pointer to the `head` of the linked list.
    *   Initialize a `fast` pointer to `head.next`. (Some implementations start both at `head`, then the loop condition changes slightly).

2.  **Traversal**:
    *   Move the `slow` pointer one step at a time (`slow = slow.next`).
    *   Move the `fast` pointer two steps at a time (`fast = fast.next.next`).
    *   Continue this process as long as `fast` and `fast.next` are not `null`. This check is crucial to avoid `NullPointerExceptions` if there is no cycle.

3.  **Cycle Detection**:
    *   If the `slow` pointer and `fast` pointer ever meet (i.e., `slow == fast`), it means there is a cycle in the linked list. Return `true`.

4.  **No Cycle**:
    *   If the loop finishes because `fast` or `fast.next` becomes `null`, it means the `fast` pointer reached the end of the list, and therefore, there is no cycle. Return `false`.

## Complexity Analysis
-   **Time Complexity:** O(N), where N is the number of nodes in the linked list. In the worst case (no cycle or a cycle at the very end), the fast pointer traverses the entire list. If there's a cycle, the fast and slow pointers will meet within the cycle.
-   **Space Complexity:** O(1), as we only use a few pointers.

## Example
Given a linked list: `3 -> 2 -> 0 -> -4`
                       `^_________|` (tail's next points to node with value 2)

1.  **Initialization**: `head = 3`. `slow = 3`, `fast = 2`.
2.  **Iteration 1**:
    *   `slow` moves to `2`.
    *   `fast` moves to `0` (from `2` to `0`, then `0` to `-4`).
    *   `slow != fast`.
3.  **Iteration 2**:
    *   `slow` moves to `0`.
    *   `fast` moves to `2` (from `-4` to `2`, then `2` to `0`).
    *   `slow != fast`.
4.  **Iteration 3**:
    *   `slow` moves to `-4`.
    *   `fast` moves to `-4` (from `0` to `-4`, then `-4` to `2`).
    *   `slow == fast` (both point to node `-4`). Cycle detected. Return `true`.

## Alternate Approach: Hash Set
An alternative approach uses a hash set to keep track of visited nodes.

1.  **Initialization**: Create an empty `HashSet<ListNode>`.
2.  **Traversal**: Iterate through the linked list from the `head`.
3.  **Check for Cycle**: For each node encountered:
    *   If the node is already in the hash set, a cycle is detected. Return `true`.
    *   Otherwise, add the node to the hash set.
4.  **No Cycle**: If the end of the list (`null`) is reached without finding a duplicate node in the hash set, there is no cycle. Return `false`.

### Complexity
-   **Time Complexity:** O(N), as each node is visited and added to the hash set at most once.
-   **Space Complexity:** O(N), as in the worst case (no cycle), all N nodes are stored in the hash set.

While this approach is conceptually simpler, the fast and slow pointer method is generally preferred in interviews due to its O(1) space complexity.
