# Meeting Rooms II

## Problem Statement
Given an array of meeting time `intervals` where `intervals[i] = [starti, endi]`, return the minimum number of conference rooms required.

## Solution Approach: Min-Heap (Priority Queue)
This problem can be solved by tracking the end times of meetings currently in progress. A min-heap is the perfect data structure to manage this, as it always gives us quick access to the meeting that will finish the soonest.

The core idea is to process meetings sorted by their start times. For each meeting, we check if a room has become free.

1.  **Sort by Start Time**: First, sort the `intervals` array based on the meeting start times. This allows us to process meetings in chronological order.

2.  **Min-Heap for End Times**: Initialize a min-heap (PriorityQueue) to store the end times of the meetings that are currently occupying a room.

3.  **Iterate Through Meetings**:
    *   For each meeting `[start, end]` in the sorted intervals:
    *   Check the heap: If the heap is not empty and the earliest end time (`heap.peek()`) is less than or equal to the current meeting's `start` time, it means a room has become free. We can reuse this room, so we `poll()` the old end time from the heap.
    *   Add the current meeting's `end` time to the heap. This represents allocating a room (either a newly freed one or a new one) for the current meeting.

4.  **Result**: The minimum number of rooms required is the maximum size the heap ever reached during this process. Since we add an end time for every meeting, the final size of the heap at the end of the iteration represents the total number of rooms allocated.

## Complexity Analysis
-   **Time Complexity:** O(N log N). The sorting step dominates the complexity. The heap operations (add and poll) take O(log N) time, and we do this for N meetings.
-   **Space Complexity:** O(N) in the worst case, where all N meetings overlap, and all their end times are stored in the heap simultaneously.

## Example
`intervals = [[0, 30], [5, 10], [15, 20]]`

1.  **Sort**: `[[0, 30], [5, 10], [15, 20]]` is already sorted by start time.
2.  **Heap**: Initialize an empty min-heap.
3.  **Process `[0, 30]`**:
    *   Heap is empty.
    *   Add `30` to the heap. Heap: `{30}`. Rooms needed: 1.
4.  **Process `[5, 10]`**:
    *   `start = 5`. Heap peek is `30`. `5 < 30`, so no room is free.
    *   Add `10` to the heap. Heap: `{10, 30}`. Rooms needed: 2.
5.  **Process `[15, 20]`**:
    *   `start = 15`. Heap peek is `10`. `15 >= 10`, so a room is free.
    *   Poll `10` from the heap. Heap: `{30}`.
    *   Add `20` to the heap. Heap: `{20, 30}`. Rooms needed: 2.
6.  **End of meetings**. The final size of the heap is 2.

The minimum number of rooms required is 2.

## Alternate Approach: Two Pointers
A very clever alternative avoids the explicit use of a heap.

1.  Create two separate arrays: one for all `start` times and one for all `end` times.
2.  Sort both the `start` and `end` arrays.
3.  Initialize `rooms = 0` and an `end_pointer = 0`.
4.  Iterate through the `start` array with a `start_pointer`.
5.  For each `start[start_pointer]`:
    *   If it is less than `end[end_pointer]`, it means a new meeting has started before the earliest-ending meeting has finished. We need a new room, so increment `rooms`.
    *   If `start[start_pointer]` is greater than or equal to `end[end_pointer]`, it means a meeting has ended, freeing up a room. We can reuse it, so we just increment the `end_pointer`.
6.  The maximum value `rooms` reaches is the answer. This is slightly different from the code provided, but the logic is the same. The code in the file correctly returns the final `rooms` count. This approach also has a time complexity of **O(N log N)** and space of **O(N)**.
