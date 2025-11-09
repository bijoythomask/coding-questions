# High Five

## Problem Statement
Given a list of scores of different students, `items`, where `items[i] = [IDi, scorei]`, return the average score of each student's **top five scores** in the order of each student's id.

The average score is calculated using **integer division**.

## Solution Approach: HashMap with Min-Heap
This problem requires us to process scores for each student and find the average of their top five. A `HashMap` is a natural choice to group scores by student ID. To efficiently find the top five scores for each student, a **min-heap (PriorityQueue)** is an excellent tool.

1.  **Data Structure**: We use a `HashMap<Integer, PriorityQueue<Integer>>`.
    *   The **key** is the student's `ID`.
    *   The **value** is a `PriorityQueue` that will store the scores for that student. We will configure this as a min-heap.

2.  **Processing Scores**:
    *   Iterate through each `item` in the input `items` array.
    *   For each student `ID`, retrieve their corresponding priority queue from the map. If it doesn't exist, create a new one.
    *   Add the current `score` to the priority queue.
    *   **Maintain Heap Size**: After adding a score, if the size of the priority queue for that student exceeds 5, `poll()` the smallest element. This ensures the heap always contains the top five scores seen so far for that student. Because it's a min-heap, the smallest of the top scores is always at the head, ready to be removed.

3.  **Calculating Averages**:
    *   After processing all scores, iterate through the `map`.
    *   For each student `ID`, iterate through their priority queue (which now contains their top five scores) and calculate the sum.
    *   Compute the average using integer division (`sum / 5`).
    *   Store the `[ID, average]` pair in the result array.

## Complexity Analysis
-   **Time Complexity:** O(N log K), where N is the number of scores in `items`, and K is the number of top scores to consider (in this case, K=5). For each score, we perform an `add` operation on a heap of size K, which takes O(log K) time.
-   **Space Complexity:** O(M * K), where M is the number of unique students. We need to store up to K scores for each of the M students.

## Example
`items = [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]`

1.  **Process scores for student 1**:
    *   Scores: 91, 92, 60, 65, 87, 100.
    *   The min-heap of size 5 for student 1 will end up containing `{87, 91, 92, 100, 65}` -> after polling -> `{87, 91, 92, 100, 87}`. The final heap will be `{87, 91, 92, 100, 65}` -> poll 60, 65. Final heap: `{87, 91, 92, 100, 87}`. Let's re-check.
    *   Scores for 1: 91, 92, 60, 65, 87, 100.
    *   Heap after all adds and polls: `{87, 91, 92, 100, 100}`. No, that's not right.
    *   Let's trace the heap for student 1:
        *   add 91: `{91}`
        *   add 92: `{91, 92}`
        *   add 60: `{60, 91, 92}`
        *   add 65: `{60, 65, 91, 92}`
        *   add 87: `{60, 65, 87, 91, 92}`
        *   add 100: `{60, 65, 87, 91, 92, 100}`. Size is 6, poll smallest: `60`. Heap is now `{65, 87, 91, 92, 100}`.
    *   Sum = `65 + 87 + 91 + 92 + 100 = 435`. Average = `435 / 5 = 87`.

2.  **Process scores for student 2**:
    *   Top 5 scores will be `{76, 77, 93, 97, 100}`.
    *   Sum = `76 + 77 + 93 + 97 + 100 = 443`. Average = `443 / 5 = 88`.

3.  **Result**: `[[1, 87], [2, 88]]`

## Alternate Approach: Sorting
An alternative to using a heap is to store all scores for each student in a list and then sort that list.

1.  **Group Scores**: Use a `HashMap<Integer, List<Integer>>` to store all scores for each student.
2.  **Sort and Calculate**:
    *   Iterate through the map.
    *   For each student, sort their list of scores in descending order.
    *   Take the first 5 elements, calculate the sum, and then the average.

This approach has a time complexity of **O(N + M * L log L)**, where L is the average number of scores per student. For each of the M students, we sort their list of L scores. This can be less efficient than the heap-based approach if students have many scores.
