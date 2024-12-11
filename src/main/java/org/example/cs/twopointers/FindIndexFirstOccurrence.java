package org.example.cs.twopointers;

/**
 *
 28. Find the Index of the First Occurrence in a String
 Easy
 Topics
 Companies
 Given two strings needle and haystack, return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 Example 1:
 Input: haystack = "sadbutsad", needle = "sad"
 Output: 0
 Explanation: "sad" occurs at index 0 and 6.
 The first occurrence is at index 0, so we return 0.
 Example 2:
 Input: haystack = "leetcode", needle = "leeto"
 Output: -1
 Explanation: "leeto" did not occur in "leetcode", so we return -1.
 */
public class FindIndexFirstOccurrence {
    public static int findIndexFirstOccurrence(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                int j = 0;
                while (j < needle.length() && i + j < haystack.length() && haystack.charAt(i + j) == needle.charAt(j)) {
                    j++;
                }
                if (j == needle.length()) return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findIndexFirstOccurrence("sadbutsad", "sad")); // Output: 0
        System.out.println(findIndexFirstOccurrence("leetcode", "leeto")); // Output: -1
    }
}
