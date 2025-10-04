package org.example.cs.heap.SlidingWindowMaximum;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[0];
        int n = nums.length;
        int[] res = new int[n - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && deque.peek() < i - k + 1) deque.poll();
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) deque.pollLast();
            deque.offer(i);
            if (i >= k - 1) res[i - k + 1] = nums[deque.peek()];
        }
        return res;
    }
    public static void main(String[] args) {
        SlidingWindowMaximum solver = new SlidingWindowMaximum();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int[] res = solver.maxSlidingWindow(nums, 3);
        for (int r : res) System.out.print(r + " "); // Output: 3 3 5 5 6 7
    }
}
