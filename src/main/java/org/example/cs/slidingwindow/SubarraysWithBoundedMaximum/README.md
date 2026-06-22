# SubarraysWithBoundedMaximum

## Problem Description
Given an integer array `nums` and two integers `left` and `right`, return the number of contiguous non-empty subarrays such that the maximum element in each subarray is at least `left` and at most `right`.

A subarray is a contiguous part of the array.

## Approach: Counting with Inclusion-Exclusion
The solution uses an elegant mathematical trick based on the principle of inclusion-exclusion. The problem asks for the number of subarrays where the maximum element is in the range `[left, right]`. This can be rephrased as:

(Number of subarrays with maximum element ≤ `right`) - (Number of subarrays with maximum element < `left`)

This simplifies the problem into finding the count of subarrays with a maximum element up to a certain bound.

### Implementation
A helper function, `count(nums, bound)`, is created to count all contiguous subarrays where every element is less than or equal to `bound`.

It works in a single pass:
1.  It maintains a `current_streak` counter.
2.  As it iterates through the array, if an element is within the `bound`, the `current_streak` is incremented. The number of new valid subarrays ending at this position is equal to this new streak length, which is added to the total answer.
3.  If an element is greater than the `bound`, it breaks all possible subarrays ending at this position, so the `current_streak` is reset to `0`.

The main function then simply calls `count(nums, right) - count(nums, left - 1)` to get the final result.

```java
public int numSubarrayBoundedMax(int[] nums, int left, int right) {
    return count(nums, right) - count(nums, left - 1);
}

private int count(int[] nums, int bound) {
    int ans = 0, cur = 0;
    for (int x : nums) {
        cur = x <= bound ? cur + 1 : 0;
        ans += cur;
    }
    return ans;
}
```

### Complexity
- **Time Complexity:** O(n), as we iterate through the array a constant number of times.
- **Space Complexity:** O(1), as we only use a few variables to store counts.

## Examples

### Example 1
-   **Input:** `nums = [2, 1, 4, 3]`, `left = 2`, `right = 3`
-   **Output:** `3`
-   **Explanation:**
    1.  `count(nums, 3)`: Counts subarrays with max ≤ 3. The total count is `4`. The subarrays are:
        - `[2]`
        - `[1]`
        - `[2, 1]`
        - `[3]`
    2.  `count(nums, 1)` (for `left - 1`): Counts subarrays with max ≤ 1. The total count is `1`. The only subarray is:
        - `[1]`
    3.  **Result:** `4 - 1 = 3`. The valid subarrays are:
        - `[2]`
        - `[2, 1]`
        - `[3]`

### Example 2
-   **Input:** `nums = [2, 9, 2, 5, 6]`, `left = 2`, `right = 8`
-   **Output:** `7`
-   **Explanation:**
    1.  `count(nums, 8)` returns `7`. The subarrays are:
        - `[2]`
        - `[2]`
        - `[5]`
        - `[6]`
        - `[2, 5]`
        - `[5, 6]`
        - `[2, 5, 6]`
    2.  `count(nums, 1)` returns `0`.
    3.  **Result:** `7 - 0 = 7`.

## Alternate Approach: Two Pointers
Another efficient O(n) solution involves a single pass with two pointers. The approach is as follows:
1.  Initialize three variables: `result` to store the total count, `start` to mark the beginning of a valid window (initialized to -1), and `end` to mark the end of a valid window (initialized to -1).
2.  Iterate through the array with index `i`:
    - If `nums[i]` is greater than `right`, it invalidates all subarrays ending at `i`. We update `start` to `i`, effectively starting a new window.
    - If `nums[i]` is greater than or equal to `left`, it can be a maximum in a valid subarray. We update `end` to `i`.
    - The number of valid subarrays ending at `i` is the size of the current valid window, which is `end - start`. We add this to our `result`.
3.  After the loop, `result` will hold the total count of valid subarrays.

```java
public int numSubarrayBoundedMaxTwoPointers(int[] nums, int left, int right) {
    int result = 0;
    int start = -1; // Marks the boundary of the last number > right
    int end = -1;   // Marks the boundary of the last number >= left

    for (int i = 0; i < nums.length; i++) {
        if (nums[i] > right) {
            start = i;
        }
        if (nums[i] >= left) {
            end = i;
        }
        result += end - start;
    }
    return result;
}
```