# Intersection of Two Linked Lists

## Problem Statement
Given the heads of two singly linked lists `headA` and `headB`, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return `null`.

For example, the two lists `A = [4,1,8,4,5]` and `B = [5,6,1,8,4,5]` intersect at node with value `8`.

## Solution Approach: Two Pointers
This problem can be solved efficiently using a clever two-pointer approach that handles lists of different lengths.

1.  **Initialization**:
    *   Initialize two pointers, `ptrA` to `headA` and `ptrB` to `headB`.

2.  **Traversal and Swapping**:
    *   Iterate while `ptrA` is not equal to `ptrB`:
        *   Move `ptrA` one step: `ptrA = ptrA.next`.
        *   Move `ptrB` one step: `ptrB = ptrB.next`.
        *   **Crucial Step**: If `ptrA` reaches the end of its list (`null`), redirect it to the head of the other list (`headB`).
        *   Similarly, if `ptrB` reaches the end of its list (`null`), redirect it to the head of the other list (`headA`).

3.  **Intersection or Null**:
    *   If the lists intersect, `ptrA` and `ptrB` will eventually meet at the intersection node.
    *   If the lists do not intersect, both pointers will eventually become `null` simultaneously (after traversing both lists twice), and the loop will terminate, returning `null`.

### Why this works:
Consider two lists of lengths `L1` and `L2`. Let `C` be the length of the common part (intersection).
- Pointer A traverses `L1` then `L2`. Total distance: `L1 + L2`.
- Pointer B traverses `L2` then `L1`. Total distance: `L2 + L1`.
Both pointers travel the same total distance. If they intersect, they will meet at the intersection point. If they don't intersect, they will both become `null` at the same time after traversing `L1 + L2` steps.

## Complexity Analysis
-   **Time Complexity:** O(L1 + L2), where L1 and L2 are the lengths of the two linked lists. In the worst case, each pointer traverses both lists once.
-   **Space Complexity:** O(1), as we only use a few pointers.

## Example
`headA = [4,1,8,4,5]`, `headB = [5,0,1,8,4,5]` (intersect at node 8)

1.  **Init**: `ptrA = 4`, `ptrB = 5`.
2.  **Iteration 1**: `ptrA = 1`, `ptrB = 0`.
3.  **Iteration 2**: `ptrA = 8`, `ptrB = 1`.
4.  **Iteration 3**: `ptrA = 4`, `ptrB = 8`.
5.  **Iteration 4**: `ptrA = 5`, `ptrB = 4`.
6.  **Iteration 5**: `ptrA = null` (end of A), `ptrB = 5`.
    *   `ptrA` becomes `headB` (`5`).
    *   `ptrB` becomes `null` (end of B).
    *   `ptrB` becomes `headA` (`4`).
    *   State: `ptrA = 5` (from B), `ptrB = 4` (from A).
7.  **Iteration 6**: `ptrA = 0`, `ptrB = 1`.
8.  **Iteration 7**: `ptrA = 1`, `ptrB = 8`.
9.  **Iteration 8**: `ptrA = 8`, `ptrB = 4`.
10. **Iteration 9**: `ptrA = 4`, `ptrB = 5`.
11. **Iteration 10**: `ptrA = 5`, `ptrB = null`.
    *   `ptrA` becomes `null` (end of B).
    *   `ptrA` becomes `headB` (`5`).
    *   `ptrB` becomes `headA` (`4`).
    *   State: `ptrA = 5` (from B), `ptrB = 4` (from A).
    *   This trace is getting long. Let's simplify the logic.

**Simplified Logic Trace:**
`headA = [4,1,8,4,5]` (length 5)
`headB = [5,0,1,8,4,5]` (length 6)
Intersection at node `8`.

`ptrA` will traverse `A` (5 nodes) then `B` (6 nodes). Total 11 steps.
`ptrB` will traverse `B` (6 nodes) then `A` (5 nodes). Total 11 steps.

They will meet at the intersection node `8`.
- `ptrA` reaches `8` after 3 steps.
- `ptrB` reaches `8` after 4 steps.
- `ptrA` continues to `4`, `5`, `null`, then `5`, `0`, `1`, `8`.
- `ptrB` continues to `4`, `5`, `null`, then `4`, `1`, `8`.
They meet at `8`.

## Alternate Approach: Hash Set
An alternative approach uses a hash set to store all nodes of one list and then checks if any node of the second list is present in the hash set.

1.  **Store List A Nodes**: Traverse `headA` and add every node to a `HashSet<ListNode>`.
2.  **Check List B Nodes**: Traverse `headB`. For each node, check if it exists in the hash set.
3.  **Intersection Found**: If a node from `headB` is found in the hash set, that is the intersection node. Return it.
4.  **No Intersection**: If the end of `headB` is reached without finding any common node, return `null`.

### Complexity
-   **Time Complexity:** O(L1 + L2), where L1 and L2 are the lengths of the two linked lists.
-   **Space Complexity:** O(L1) to store all nodes of the first list in the hash set.

This approach is also efficient in terms of time but uses more space than the two-pointer method.
