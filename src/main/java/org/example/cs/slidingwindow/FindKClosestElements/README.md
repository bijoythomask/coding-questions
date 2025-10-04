# FindKClosestElements

## Problem Description
Given a **sorted** integer array `arr`, two integers `k` and `x`, return the `k` closest integers to `x` in the array. The result should also be sorted in ascending order.

An integer `a` is closer to `x` than an integer `b` if:
- `|a - x| < |b - x|`, or
- `|a - x| == |b - x|` and `a < b`

## Approach
### Approach 1: Binary Search to Find Left Bound (Optimal)

The most efficient approach is to find the optimal starting index of the result window. Since the result will be a contiguous block of `k` elements, our goal is to find the best starting index `i` for the subarray `arr[i...i+k-1]`.

We can use binary search on the possible starting indices, which range from `0` to `n-k`.

1.  Set a search range for the start of the window, `low = 0` and `high = arr.length - k`.
2.  In each step of the binary search, calculate `mid`. We are comparing the window starting at `mid` (`arr[mid...mid+k-1]`) with the window starting at `mid+1` (`arr[mid+1...mid+k]`).
3.  To decide whether to move left or right, we only need to compare the element we would *drop* (`arr[mid]`) versus the element we would *gain* (`arr[mid+k]`).
    -   If `x` is closer to `arr[mid+k]` than to `arr[mid]`, it means our optimal window is likely to the right. So, we update `low = mid + 1`.
    -   Otherwise, `x` is closer to `arr[mid]`, which means the current window starting at `mid` is better, or the optimal window is to the left. So, we update `high = mid`.
4.  The loop terminates when `low == high`, which gives us the optimal starting index for our window.
5.  Collect the `k` elements from that starting index.

This approach has a time complexity of **O(log(n-k) + k)**.

### Approach 2: Two Pointers (Sliding Window)

A more intuitive approach is to think of a window spanning the entire array and shrinking it down to size `k`.

1.  Initialize two pointers, `low = 0` and `high = arr.length - 1`.
2.  While the window size (`high - low + 1`) is greater than `k`:
    -   Compare the distance of the leftmost element (`arr[low]`) from `x` with the distance of the rightmost element (`arr[high]`) from `x`.
    -   If the leftmost element is farther from `x` than the rightmost element (`x - arr[low] > arr[high] - x`), shrink the window from the left by incrementing `low`.
    -   Otherwise, shrink the window from the right by decrementing `high`.
3.  Once the loop finishes, the window `[low, high]` contains the `k` closest elements.

This approach has a time complexity of **O(n-k)**.

## Sample Input/Output
### Example 1:
- **Input:** `arr = [1,2,3,4,5]`, `k = 4`, `x = 3`
- **Output:** `[1,2,3,4]`
- **Explanation:** The distances from 3 are: |1-3|=2, |2-3|=1, |3-3|=0, |4-3|=1, |5-3|=2. The four closest are 1, 2, 3, and 4.

### Example 2:
- **Input:** `arr = [1,2,3,4,5]`, `k = 4`, `x = -1`
- **Output:** `[1,2,3,4]`
- **Explanation:** Even though `x` is outside the array, the first four elements are the closest.

### Example 3:
- **Input:** `arr = [1,1,1,10,10,10]`, `k = 1`, `x = 9`
- **Output:** `[10]`
- **Explanation:** `|1-9|=8` and `|10-9|=1`. 10 is closer.
