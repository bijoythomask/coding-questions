# DuplicateII

## Problem Description
Given an integer array `nums` and an integer `k`, return `true` if there are two **distinct indices** `i` and `j` in the array such that `nums[i] == nums[j]` and `abs(i - j) <= k`.

## Approach
The problem requires us to find if a duplicate element exists within a certain distance `k`. A highly efficient way to solve this is by using a `HashMap` to store the most recent index of each number we encounter.

The algorithm works as follows:
1.  Initialize an empty `HashMap<Integer, Integer>` to map each number to its last seen index.
2.  Iterate through the `nums` array with the index `i`.
3.  For each number `nums[i]`:
    -   Check if the number already exists as a key in the `HashMap`.
    -   If it does, retrieve its last seen index, `lastIndex`.
    -   Calculate the distance: `i - lastIndex`.
    -   If this distance is less than or equal to `k`, we have found a valid pair, and we can immediately return `true`.
4.  If the number is not in the map or the distance condition was not met, update the map with the current number and its index: `map.put(nums[i], i)`. This ensures we always have the most recent index for the next time we see this number.
5.  If the loop completes without finding such a pair, it means no duplicates exist within the distance `k`, so we return `false`.

This approach is efficient because it processes the array in a single pass, giving it a time complexity of O(n) and a space complexity of O(n) in the worst case (if all elements are unique).

## Sample Input/Output
### Example 1:
- **Input:** `nums = [1, 2, 3, 1]`, `k = 3`
- **Output:** `true`
- **Explanation:** The number `1` appears at index `0` and index `3`. The absolute difference `abs(0 - 3) = 3`, which is less than or equal to `k`.

### Example 2:
- **Input:** `nums = [1, 0, 1, 1]`, `k = 1`
- **Output:** `true`
- **Explanation:** The number `1` appears at index `2` and index `3`. The absolute difference `abs(2 - 3) = 1`, which is less than or equal to `k`.

### Example 3:
- **Input:** `nums = [1, 2, 3, 1, 2, 3]`, `k = 2`
- **Output:** `false`
- **Explanation:** While there are duplicates, none of them have an index difference of 2 or less. For the number `1`, the difference is `3`. For `2`, it's `3`. For `3`, it's `3`.
