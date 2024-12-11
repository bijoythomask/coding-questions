package org.example.cs.heap;

import java.util.PriorityQueue;

/**
 * You are part of a university admissions office and need to keep track of the kth highest test score from applicants in real-time. This helps to determine cut-off marks for interviews and admissions dynamically as new applicants submit their scores.
 *
 * You are tasked to implement a class which, for a given integer k, maintains a stream of test scores and continuously returns the kth highest test score after a new score has been submitted. More specifically, we are looking for the kth highest score in the sorted list of all scores.
 *
 * Implement the KthLargest class:
 * KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of test scores nums.
 * int add(int val) Adds a new test score val to the stream and returns the element representing the kth largest element in the pool of test scores so far.
 */

public class KthLagrgestInStream {
    public static void main(String[] args) {
        int[] arr = {10, 20, 11, 70, 50, 40, 100, 5};
        int k = 3;
        KthLagrgestInStream kthLagrgestInStream = new KthLagrgestInStream();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(kthLagrgestInStream.kthLargest(arr[i], k));
        }
    }

    private int kthLargest(int num, int k) {
        if (minHeap.size() < k) {
            minHeap.add(num);
        } else {
            if (minHeap.peek() < num) {
                minHeap.poll();
                minHeap.add(num);
            }
        }
        return minHeap.peek();
    }

    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
}
