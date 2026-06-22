# Remove Duplicates from Sorted Array

## Problem Statement
Given an integer array `nums` sorted in non-decreasing order, remove the duplicates [in-place](https://en.wikipedia.org/wiki/In-place_algorithm) such that each unique element appears only once. The relative order of the elements should be kept the same.

Return the number of unique elements in `nums`.

Consider the number of unique elements in `nums` to be `k`. To get accepted, you need to do the following things:
- Change the array `nums` such that the first `k` elements of `nums` contain the unique elements in the order they were present in `nums` initially. The remaining elements of `nums` are not important as well as the size of `nums`.
- Return `k`.

## Solution Approach: Two Pointers
This problem is a classic application of the two-pointer technique for in-place array modification. Since the array is sorted, duplicate elements will always be adjacent. We can use one pointer to iterate through the array and another to keep track of the position for the next unique element.

1.  **Initialization**:
    *   Handle edge case: If the array is empty, return `0`.
    *   Initialize a `write_pointer` (or `unique_count_pointer`) to `1`. This pointer will track the position where the next unique element should be placed. The first element `nums[0]` is always unique by definition (as it's the first element).

2.  **Iterate and Overwrite**:
    *   Initialize a `read_pointer` to `1`.
    *   Iterate `read_pointer` from `1` to `nums.length - 1`.
    *   For each element `nums[read_pointer]`:
        *   Compare `nums[read_pointer]` with the element just before the `write_pointer` (`nums[write_pointer - 1]`).
        *   If `nums[read_pointer]` is **different** from `nums[write_pointer - 1]`:
            *   It means we've found a new unique element.
            *   Copy `nums[read_pointer]` to `nums[write_pointer]`.
            *   Increment `write_pointer`.
        *   If `nums[read_pointer]` is **the same** as `nums[write_pointer - 1]`, it's a duplicate. We simply skip it, and the `write_pointer` does not advance.

3.  **Result**: After the `read_pointer` has traversed the entire array, the `write_pointer` will hold the count of unique elements. This `write_pointer` value is `k`.

## Complexity Analysis
-   **Time Complexity:** O(N), where N is the number of elements in `nums`. The `read_pointer` iterates through the array once.
-   **Space Complexity:** O(1), as the modification is done in-place without using any extra data structures.

## Example
`nums = [0, 0, 1, 1, 1, 2, 2, 3, 3, 4]`

1.  **Initialization**: `write_pointer = 1`. (The first unique element `0` is at `nums[0]`).
2.  **Iteration**:
    *   `read_pointer = 1`, `nums[1] = 0`. `nums[1] == nums[0]`. Skip.
    *   `read_pointer = 2`, `nums[2] = 1`. `nums[2] != nums[0]`.
        *   `nums[write_pointer]` (`nums[1]`) becomes `1`.
        *   `write_pointer` becomes `2`.
        *   `nums` is now `[0, 1, 1, 1, 1, 2, 2, 3, 3, 4]` (conceptually).
    *   `read_pointer = 3`, `nums[3] = 1`. `nums[3] == nums[1]`. Skip.
    *   `read_pointer = 4`, `nums[4] = 1`. `nums[4] == nums[1]`. Skip.
    *   `read_pointer = 5`, `nums[5] = 2`. `nums[5] != nums[1]`.
        *   `nums[write_pointer]` (`nums[2]`) becomes `2`.
        *   `write_pointer` becomes `3`.
        *   `nums` is now `[0, 1, 2, 1, 1, 2, 2, 3, 3, 4]` (conceptually).
    *   ... and so on.

3.  **Result**: The loop finishes. `write_pointer` will be `5`. Return `5`.
    The array `nums` will be `[0, 1, 2, 3, 4, _, _, _, _, _]` (the elements after index 4 don't matter).

## Alternate Approach: Using a Hash Set
While less efficient for sorted arrays, a hash set can be used to find unique elements in an unsorted array.

1.  **Initialization**: Create an empty `HashSet<Integer>`.
2.  **Populate Hash Set**: Iterate through `nums`. For each element, add it to the hash set.
3.  **Reconstruct Array**: Create a new array or iterate through the hash set to populate the beginning of `nums`.
4.  **Return Size**: The size of the hash set is `k`.

### Complexity
-   **Time Complexity:** O(N) on average to iterate and add to the hash set.
-   **Space Complexity:** O(N) in the worst case, if all elements are unique.

This approach is more general (works for unsorted arrays) but sacrifices the O(1) space complexity that the two-pointer method achieves for sorted arrays.
