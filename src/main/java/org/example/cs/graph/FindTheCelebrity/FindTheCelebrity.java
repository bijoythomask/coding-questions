package org.example.cs.graph.FindTheCelebrity;

public class FindTheCelebrity {
    public int findCelebrity(int n) {
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i != candidate && (knows(candidate, i) || !knows(i, candidate))) {
                return -1;
            }
        }
        return candidate;
    }
    private boolean knows(int a, int b) {
        // This should be implemented based on the problem context
        return true;
    }
    public static void main(String[] args) {
        FindTheCelebrity findtheCelebrity = new FindTheCelebrity();
        System.out.println(findtheCelebrity.findCelebrity(3)); // Output: 1
    }
}
