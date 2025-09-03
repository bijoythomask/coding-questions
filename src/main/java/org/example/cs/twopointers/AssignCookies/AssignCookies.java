package org.example.cs.twopointers;

import java.util.Arrays;

public class AssignCookies {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0;
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) i++;
            j++;
        }
        return i;
    }
    public static void main(String[] args) {
        AssignCookies solver = new AssignCookies();
        int[] g = {1,2,3};
        int[] s = {1,1};
        System.out.println(solver.findContentChildren(g, s)); // 1
    }
}
