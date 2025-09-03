package org.example.cs.slidingwindow;

import java.util.HashMap;

public class MaximumNumberOfOccurrencesSubstring {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        HashMap<String, Integer> count = new HashMap<>();
        int res = 0;
        for (int i = 0; i <= s.length() - minSize; i++) {
            String sub = s.substring(i, i + minSize);
            if (uniqueLetters(sub) <= maxLetters) {
                count.put(sub, count.getOrDefault(sub, 0) + 1);
                res = Math.max(res, count.get(sub));
            }
        }
        return res;
    }
    private int uniqueLetters(String s) {
        boolean[] seen = new boolean[26];
        int count = 0;
        for (char c : s.toCharArray()) {
            if (!seen[c - 'a']) {
                seen[c - 'a'] = true;
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        MaximumNumberOfOccurrencesSubstring solver = new MaximumNumberOfOccurrencesSubstring();
        System.out.println(solver.maxFreq("aababcaab", 2, 3, 4)); // 2
    }
}
