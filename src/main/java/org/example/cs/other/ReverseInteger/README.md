# Reverse Integer

## Problem Statement
Given a signed 32-bit integer `x`, return `x` with its digits reversed. If reversing `x` causes the value to go outside the signed 32-bit integer range `[-2^31, 2^31 - 1]`, then return `0`.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

## Solution Approach: Pop and Push Digits with Overflow Check
The most common and efficient way to reverse an integer is to repeatedly "pop" the last digit off the original number and "push" it onto a new `reversed` number. Crucially, we must check for integer overflow *before* each push operation.

1.  **Initialization**:
    *   Initialize `reversed = 0`.

2.  **Iterate and Reverse**:
    *   Loop while `x` is not `0`:
        *   **Extract Last Digit (Pop)**: Get the last digit of `x` using the modulo operator: `digit = x % 10`.
        *   **Remove Last Digit**: Update `x` by integer division: `x /= 10`.
        *   **Overflow Check (Crucial Step)**: Before multiplying `reversed` by 10 and adding `digit`, check if this operation would cause an overflow.
            *   If `reversed > Integer.MAX_VALUE / 10` OR (`reversed == Integer.MAX_VALUE / 10` AND `digit > 7`), then an overflow will occur for positive numbers. Return `0`. (Note: `7` is the last digit of `Integer.MAX_VALUE = 2147483647`).
            *   If `reversed < Integer.MIN_VALUE / 10` OR (`reversed == Integer.MIN_VALUE / 10` AND `digit < -8`), then an underflow will occur for negative numbers. Return `0`. (Note: `-8` is the last digit of `Integer.MIN_VALUE = -2147483648`).
        *   **Append Digit (Push)**: Update `reversed = reversed * 10 + digit`.

3.  **Return Result**: After the loop finishes, `reversed` will contain the reversed integer (or `0` if an overflow occurred).

## Complexity Analysis
-   **Time Complexity:** O(log |x|), where |x| is the absolute value of `x`. The number of iterations is proportional to the number of digits in `x`.
-   **Space Complexity:** O(1), as we only use a few integer variables.

## Example
`x = 123`

1.  **Init**: `reversed = 0`.
2.  **Loop 1**:
    *   `digit = 123 % 10 = 3`.
    *   `x = 123 / 10 = 12`.
    *   Overflow check: `0` is within bounds.
    *   `reversed = 0 * 10 + 3 = 3`.
3.  **Loop 2**:
    *   `digit = 12 % 10 = 2`.
    *   `x = 12 / 10 = 1`.
    *   Overflow check: `3` is within bounds.
    *   `reversed = 3 * 10 + 2 = 32`.
4.  **Loop 3**:
    *   `digit = 1 % 10 = 1`.
    *   `x = 1 / 10 = 0`.
    *   Overflow check: `32` is within bounds.
    *   `reversed = 32 * 10 + 1 = 321`.
5.  **Loop ends** (`x` is `0`).
6.  **Result**: `321`.

## Alternate Approach: String Conversion
An alternative approach involves converting the integer to a string, reversing the string, and then converting it back to an integer.

1.  **Convert to String**: Convert `x` to a string. Handle the sign separately if `x` is negative.
2.  **Reverse String**: Use a `StringBuilder` or `StringBuffer` to reverse the string.
3.  **Convert Back to Integer**: Parse the reversed string back to a `long` to temporarily handle potential overflow.
4.  **Overflow Check**: Check if the `long` value is within the `Integer.MIN_VALUE` and `Integer.MAX_VALUE` range. If it is, cast it to `int` and return. Otherwise, return `0`.

### Complexity
-   **Time Complexity:** O(log |x|) for string conversion and reversal.
-   **Space Complexity:** O(log |x|) to store the string representation of the number.

This approach is generally simpler to implement but might be slightly less performant due to string operations and uses more space.
