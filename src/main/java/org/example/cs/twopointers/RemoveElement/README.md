# Remove Element

## Problem Statement
Given an integer array `nums` and an integer `val`, remove all occurrences of `val` in `nums` [in-place](https://en.wikipedia.org/wiki/In-place_algorithm). The order of the elements may be changed.

Return the number of elements in `nums` which are not equal to `val`.

Consider the number of elements in `nums` which are not equal to `val` be `k`. To get accepted, you need to do the following things:
- Change the array `nums` such that the first `k` elements of `nums` contain the elements which are not equal to `val`. The remaining elements of `nums` are not important as well as the size of `nums`.
- Return `k`.

## Solution Approach: Two Pointers (Read and Write)
This problem is a classic example of an in-place modification using the two-pointer technique. We use one pointer to iterate through the array (read pointer) and another pointer to keep track of where to place the non-`val` elements (write pointer).

1.  **Initialization**:
    *   Initialize a `write_pointer` (or `k_pointer`) to `0`. This pointer will track the position where the next non-`val` element should be placed.

2.  **Iterate and Overwrite**:
    *   Iterate through the array with a `read_pointer` from `0` to `nums.length - 1`.
    *   For each element `nums[read_pointer]`:
        *   If `nums[read_pointer]` is **not equal** to `val`:
            *   Copy `nums[read_pointer]` to `nums[write_pointer]`.
            *   Increment `write_pointer`.
        *   If `nums[read_pointer]` **is equal** to `val`, we simply skip it. The `write_pointer` does not advance, effectively overwriting this element later with a non-`val` element.

3.  **Result**: After the `read_pointer` has traversed the entire array, the `write_pointer` will hold the count of elements that are not equal to `val`. This `write_pointer` value is `k`.

## Complexity Analysis
-   **Time Complexity:** O(N), where N is the number of elements in `nums`. The `read_pointer` iterates through the array once.
-   **Space Complexity:** O(1), as the modification is done in-place without using any extra data structures.

## Example
`nums = [3, 2, 2, 3]`, `val = 3`

1.  **Initialization**: `write_pointer = 0`.
2.  **Iteration**:
    *   `read_pointer = 0`, `nums[0] = 3`. `nums[0] == val`. Skip.
    *   `read_pointer = 1`, `nums[1] = 2`. `nums[1] != val`.
        *   `nums[write_pointer]` (`nums[0]`) becomes `2`.
        *   `write_pointer` becomes `1`.
        *   `nums` is now `[2, 2, 2, 3]` (conceptually, `nums[0]` is updated).
    *   `read_pointer = 2`, `nums[2] = 2`. `nums[2] != val`.
        *   `nums[write_pointer]` (`nums[1]`) becomes `2`.
        *   `write_pointer` becomes `2`.
        *   `nums` is now `[2, 2, 2, 3]` (conceptually, `nums[1]` is updated).
    *   `read_pointer = 3`, `nums[3] = 3`. `nums[3] == val`. Skip.

3.  **Result**: The loop finishes. `write_pointer` is `2`. Return `2`.
    The array `nums` will be `[2, 2, _, _]` (the last two elements don't matter).

## Alternate Approach: Two Pointers (Swap with End)
This approach is useful when the number of elements to remove is small.

1.  **Initialization**:
    *   Initialize `left_pointer` to `0`.
    *   Initialize `right_pointer` to `nums.length - 1`.

2.  **Iterate and Swap**:
    *   Loop while `left_pointer <= right_pointer`:
        *   If `nums[left_pointer]` is equal to `val`:
            *   Swap `nums[left_pointer]` with `nums[right_pointer]`.
            *   Decrement `right_pointer`. (The element at `right_pointer` might also be `val`, so `left_pointer` does not advance yet).
        *   Else (`nums[left_pointer]` is not equal to `val`):
            *   Increment `left_pointer`.

3.  **Result**: The `left_pointer` will be the count of elements not equal to `val`. This `left_pointer` value is `k`.

### Complexity
-   **Time Complexity:** O(N). In the worst case, each element is visited once.
-   **Space Complexity:** O(1).

This approach can be more efficient in terms of assignments when there are many elements to remove.
