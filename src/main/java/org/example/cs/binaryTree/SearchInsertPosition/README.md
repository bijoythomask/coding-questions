# Search Insert Position

## Problem Description
Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You must write an algorithm with O(log n) runtime complexity.

## Approach
The solution uses a binary search algorithm to find the insertion position of the target value in the sorted array. It initializes `left` and `right` pointers to the start and end of the array, respectively. In each iteration, it calculates the `mid` index and compares the element at `mid` with the target:

- If `nums[mid]` is equal to the `target`, the function returns `mid`.
- If `nums[mid]` is less than the `target`, the `left` pointer is updated to `mid + 1` to search in the right half of the array.
- If `nums[mid]` is greater than the `target`, the `right` pointer is updated to `mid - 1` to search in the left half of the array.

If the loop completes without finding the target, the `left` pointer will indicate the correct insertion position.

## Complexity
- **Time Complexity**: O(log n) because the binary search algorithm halves the search space in each iteration.
- **Space Complexity**: O(1) because the algorithm uses a constant amount of extra space.

## Sample Input/Output

### Example 1:
- **Input**: `nums = [1,3,5,6]`, `target = 5`
- **Output**: `2`

### Example 2:
- **Input**: `nums = [1,3,5,6]`, `target = 2`
- **Output**: `1`

### Example 3:
- **Input**: `nums = [1,3,5,6]`, `target = 7`
- **Output**: `4`
