# Search Insert Position

## Problem Statement
Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You must write an algorithm with O(log n) runtime complexity.

## Solution Approach
The solution uses a binary search algorithm to find the insertion position of the target value in the sorted array. It initializes `left` and `right` pointers to the start and end of the array, respectively. In each iteration, it calculates the `mid` index and compares the element at `mid` with the target:

- If `nums[mid]` is equal to the `target`, the function returns `mid`.
- If `nums[mid]` is less than the `target`, the `left` pointer is updated to `mid + 1` to search in the right half of the array.
- If `nums[mid]` is greater than the `target`, the `right` pointer is updated to `mid - 1` to search in the left half of the array.

If the loop completes without finding the target, the `left` pointer will indicate the correct insertion position.

## Complexity
- **Time Complexity**: O(log n) because the binary search algorithm halves the search space in each iteration.
- **Space Complexity**: O(1) because the algorithm uses a constant amount of extra space.

## Example

### Example 1:
- **Input**: `nums = [1,3,5,6]`, `target = 5`
- **Output**: `2`

### Example 2:
- **Input**: `nums = [1,3,5,6]`, `target = 2`
- **Output**: `1`

### Example 3:
- **Input**: `nums = [1,3,5,6]`, `target = 7`
- **Output**: `4`

## Alternate Approach: Linear Scan

A simpler, though less efficient, approach is to perform a linear scan of the array.

1.  **Iterate Through the Array**: Loop through the array from the beginning.
2.  **Find Insertion Point**:
    *   If the current element is equal to the target, return the current index.
    *   If the current element is greater than the target, the target should be inserted at the current index, so return the current index.
3.  **Target is Largest**: If the loop completes without finding an insertion point, it means the target is greater than all elements in the array. In this case, the insertion index is the length of the array.

This approach has a time complexity of **O(n)**, which does not meet the problem's requirement of O(log n) but is a valid way to solve the problem.
