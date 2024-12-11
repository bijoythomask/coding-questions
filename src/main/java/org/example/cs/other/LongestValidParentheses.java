package org.example.cs.other;

/**
 * Given a string containing just the characters '(' and ')', return the length of the longest valid (well-formed) parentheses
 * substring
 * .
 * Example 1:
 *
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 * Example 2:
 *
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 * Example 3:
 *
 * Input: s = ""
 * Output: 0
 */
public class LongestValidParentheses {

    public int longestValidParentheses(String s) {
        int left = 0, right = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') left++;
            else right++;
            if (left == right) max = Math.max(max, 2 * right);
            else if (right >= left) left = right = 0;
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') left++;
            else right++;
            if (left == right) max = Math.max(max, 2 * left);
            else if (left >= right) left = right = 0;
        }
        return max;
    }

    public static void main(String[] args) {
        LongestValidParentheses longestValidParentheses = new LongestValidParentheses();
        System.out.println(longestValidParentheses.longestValidParentheses("(()"));
        System.out.println(longestValidParentheses.longestValidParentheses(")()())"));
        System.out.println(longestValidParentheses.longestValidParentheses("(())"));
    }

}
