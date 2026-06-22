# Divide Two Integers

## Problem Statement
Given two integers `dividend` and `divisor`, divide two integers without using multiplication, division, and modulo operators.

The integer division should truncate toward zero, which means losing its fractional part. For example, `8.345` would be truncated to `8`, and `-2.733` would be truncated to `-2`.

Return the quotient after dividing `dividend` by `divisor`.

The tests are generated so that the quotient will never be too large to fit in a 32-bit signed integer.

## Solution Approach: Bit Manipulation (Repeated Subtraction with Doubling)
Since we cannot use multiplication, division, or modulo, the core idea is to perform repeated subtraction. However, simple repeated subtraction would be too slow (O(dividend/divisor)). To optimize this, we can use bit manipulation (left shifts) to subtract multiples of the divisor efficiently.

1.  **Handle Edge Case**: The only case where the result can exceed `Integer.MAX_VALUE` is `Integer.MIN_VALUE / -1`. Handle this explicitly by returning `Integer.MAX_VALUE`.
2.  **Determine Sign**: Determine the sign of the result. If `dividend` and `divisor` have different signs, the result is negative; otherwise, it's positive. Store this in a `sign` variable.
3.  **Convert to Positive Long**: Convert both `dividend` and `divisor` to their absolute `long` values. This is crucial to handle `Integer.MIN_VALUE` correctly (as `Math.abs(Integer.MIN_VALUE)` would overflow `int`).
4.  **Repeated Subtraction with Doubling**:
    *   Initialize `result = 0`.
    *   While `dvd >= dvs`:
        *   Initialize `temp_divisor = dvs` and `multiple = 1`.
        *   While `dvd >= (temp_divisor << 1)` (i.e., `dvd` is greater than or equal to `2 * temp_divisor`):
            *   Double `temp_divisor`: `temp_divisor <<= 1`.
            *   Double `multiple`: `multiple <<= 1`.
        *   Subtract the largest possible `temp_divisor` from `dvd`: `dvd -= temp_divisor`.
        *   Add the corresponding `multiple` to `result`: `result += multiple`.
5.  **Apply Sign**: Apply the determined `sign` to the `result`.

## Complexity Analysis
-   **Time Complexity:** O(log(dividend/divisor)). In each step of the inner `while` loop, `temp_divisor` is doubled, effectively halving the remaining `dvd` in a logarithmic fashion.
-   **Space Complexity:** O(1), as we only use a few variables.

## Example
`dividend = 10`, `divisor = 3`

1.  **Edge Case**: Not applicable.
2.  **Sign**: `sign = 1`.
3.  **Long Abs**: `dvd = 10L`, `dvs = 3L`.
4.  **Repeated Subtraction**:
    *   `result = 0`.
    *   **Loop 1**: `dvd (10) >= dvs (3)` is true.
        *   `temp_divisor = 3`, `multiple = 1`.
        *   Inner loop: `dvd (10) >= (3 << 1 = 6)` is true.
            *   `temp_divisor = 6`, `multiple = 2`.
        *   Inner loop: `dvd (10) >= (6 << 1 = 12)` is false. Inner loop ends.
        *   `dvd = 10 - 6 = 4`.
        *   `result = 0 + 2 = 2`.
    *   **Loop 2**: `dvd (4) >= dvs (3)` is true.
        *   `temp_divisor = 3`, `multiple = 1`.
        *   Inner loop: `dvd (4) >= (3 << 1 = 6)` is false. Inner loop ends.
        *   `dvd = 4 - 3 = 1`.
        *   `result = 2 + 1 = 3`.
    *   **Loop 3**: `dvd (1) >= dvs (3)` is false. Outer loop ends.
5.  **Apply Sign**: `sign` is `1`. Return `result = 3`.

## Alternate Approach: Simple Repeated Subtraction
A more straightforward but less efficient approach is to simply subtract the divisor from the dividend repeatedly until the dividend becomes less than the divisor.

1.  **Handle Sign and Abs**: Same as above.
2.  **Repeated Subtraction**:
    *   Initialize `result = 0`.
    *   While `dvd >= dvs`:
        *   `dvd -= dvs`.
        *   `result++`.
3.  **Apply Sign**: Apply the determined `sign` to the `result`.

### Complexity
-   **Time Complexity:** O(dividend/divisor). In the worst case (e.g., `dividend = Integer.MAX_VALUE`, `divisor = 1`), this would be extremely slow.
-   **Space Complexity:** O(1).

This approach is generally too slow for the constraints of this problem but demonstrates the fundamental idea of division as repeated subtraction.
