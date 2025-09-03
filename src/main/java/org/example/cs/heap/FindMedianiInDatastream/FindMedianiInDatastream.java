package org.example.cs.heap.FindMedianiInDatastream;

import java.util.Collections;
import java.util.PriorityQueue;

public class FindMedianiInDatastream {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }
    private static class MedianFinder {
        private final PriorityQueue<Integer> minHeap;
        private final PriorityQueue<Integer> maxHeap;
        public MedianFinder() {
            minHeap = new PriorityQueue<>();
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        }
        public void addNum(int num) {
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
            if (maxHeap.size() < minHeap.size()) {
                maxHeap.add(minHeap.poll());
            }
        }
        public double findMedian() {
            if (maxHeap.isEmpty()) return 0;
            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            } else if (!minHeap.isEmpty()) {
                return (maxHeap.peek() + minHeap.peek()) * 0.5;
            } else {
                return 0;
            }
        }
    }
}
