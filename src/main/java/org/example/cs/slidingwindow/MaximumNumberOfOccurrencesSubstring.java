package org.example.cs.slidingwindow;

/**
 * Given a string s, return the maximum number of occurrences of any substring under the following rules:
 *
 * The number of unique characters in the substring must be less than or equal to maxLetters.
 * The substring size must be between minSize and maxSize inclusive.
 *
 *
 * Example 1:
 *
 * Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
 * Output: 2
 * Explanation: Substring "aab" has 2 occurrences in the original string.
 * It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).
 * Example 2:
 *
 * Input: s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
 * Output: 2
 * Explanation: Substring "aaa" occur 2 times in the string. It can overlap.
 */
public class MaximumNumberOfOccurrencesSubstring {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int n = s.length();
        int[] count = new int[26];
        int[] freq = new int[n + 1];
        int unique = 0;
        int res = 0;
        int mod = 1000000007;
        int left = 0;
        int right = 0;
        int maxUnique = 0;
        while (right < n) {
            int currChar = s.charAt(right) - 'a';
            if (count[currChar] == 0) {
                unique++;
            }
            count[currChar]++;
            right++;
            while (unique > maxLetters || right - left > maxSize) {
                int leftChar = s.charAt(left) - 'a';
                count[leftChar]--;
                if (count[leftChar] == 0) {
                    unique--;
                }
                left++;
            }
            if (right - left >= minSize) {
                int hash = getHash(s.substring(left, right));
                freq[hash]++;
                maxUnique = Math.max(maxUnique, freq[hash]);
            }
        }
        return maxUnique;
    }

    private int getHash(String s) {
        int hash = 0;
        for (char c : s.toCharArray()) {
            hash = hash * 26 + c - 'a';
        }
        return hash;
    }
}
