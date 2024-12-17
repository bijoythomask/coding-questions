package org.example.cs.slidingwindow;

/**
 * You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.
 *
 * Return the length of the longest substring containing the same letter you can get after performing the above operations.
 *
 * Example 1:
 *
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 * Example 2:
 *
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 * There may exists other ways to achieve this answer too.
 */
public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int maxCount = 0;
        int start = 0;
        int maxLength = 0;

        for (int end = 0; end < s.length(); end++) {
            int currChar = s.charAt(end) - 'A';
            maxCount = Math.max(maxCount, ++count[currChar]);
            while (end - start + 1 - maxCount > k) {
                int startChar = s.charAt(start) - 'A';
                count[startChar]--;
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement longestRepeatingCharacterReplacement = new LongestRepeatingCharacterReplacement();
        System.out.println(longestRepeatingCharacterReplacement.characterReplacement("ABAB", 2)); // Output: 4
        System.out.println(longestRepeatingCharacterReplacement.characterReplacement("AABABBA", 1)); // Output: 4
    }
}
