# Merge K Sorted Lists

## Problem Statement
You are given an array of `k` linked-lists `lists`, where each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

## Solution Approach: Min-Heap (Priority Queue)
This problem is a classic example of a **k-way merge**, and a **min-heap** is the most efficient data structure for this task. The heap will help us to always keep track of the smallest node among the heads of all `k` lists.

1.  **Data Structure**: We use a `PriorityQueue` configured as a min-heap. The heap will store `ListNode` objects, and it will be ordered by the `val` of the nodes.

2.  **Initialization**:
    *   Create a `dummy` head node for the resulting merged list and a `current` pointer that will be used to build the new list.
    *   Iterate through the input `lists` array. For each non-null list head, add it to the min-heap.

3.  **Build the Merged List**:
    *   Loop while the min-heap is not empty.
    *   In each iteration, `poll()` the node with the smallest value from the heap. Let this be `smallestNode`.
    *   Append `smallestNode` to our result list by setting `current.next = smallestNode`.
    *   Advance the `current` pointer: `current = current.next`.
    *   If `smallestNode` has a `next` node in its original list, `offer()` that `next` node to the heap. This is the key step that brings the next element from the list we just took from into consideration.

4.  **Return the Result**:
    *   After the loop, the `dummy.next` will point to the head of the fully merged, sorted linked list.

## Complexity Analysis
-   **Time Complexity:** O(N log K), where N is the total number of nodes in all lists, and K is the number of lists.
    -   The heap will contain at most K nodes.
    -   Each of the N nodes will be added (`offer`) and removed (`poll`) from the heap exactly once, with each operation costing O(log K).
-   **Space Complexity:** O(K) to store at most K nodes in the priority queue.

## Example
`lists = [[1,4,5], [1,3,4], [2,6]]`

1.  **Heap Init**: Add the heads of the three lists to the min-heap. Heap: `{1, 1, 2}`.
2.  **Merge Loop**:
    *   Poll `1` (from list 1). Add it to result. Offer its next node, `4`. Heap: `{1, 2, 4}`.
    *   Poll `1` (from list 2). Add it to result. Offer its next node, `3`. Heap: `{2, 3, 4}`.
    *   Poll `2` (from list 3). Add it to result. Offer its next node, `6`. Heap: `{3, 4, 6}`.
    *   Poll `3`. Add it to result. Offer its next node, `4`. Heap: `{4, 4, 6}`.
    *   Poll `4`. Add it to result. Offer its next node, `5`. Heap: `{4, 5, 6}`.
    *   Poll `4`. Add it to result. Its list has no more nodes. Heap: `{5, 6}`.
    *   ...and so on, until the heap is empty.
3.  **Final Result**: `1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6`

## Related Problem: Merge Two Sorted Lists
The provided Java code in this folder solves a simpler version of this problem: merging just two sorted lists. This is often a subproblem in other approaches to the K-lists problem (the "Divide and Conquer" approach). The recursive solution provided in the file has a time complexity of O(M+N) and space complexity of O(M+N) for the recursion stack, where M and N are the lengths of the two lists.
