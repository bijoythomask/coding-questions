package org.example.cs.heap.KthLagrgestInStream;

import java.util.PriorityQueue;

public class KthLagrgestInStream {
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private int k;
    public KthLagrgestInStream(int k, int[] nums) {
        this.k = k;
        for (int num : nums) add(num);
    }
    public int add(int val) {
        minHeap.offer(val);
        if (minHeap.size() > k) minHeap.poll();
        return minHeap.peek();
    }
    public static void main(String[] args) {
        KthLagrgestInStream kth = new KthLagrgestInStream(3, new int[]{4,5,8,2});
        System.out.println(kth.add(3)); // 4
        System.out.println(kth.add(5)); // 5
        System.out.println(kth.add(10)); // 5
        System.out.println(kth.add(9)); // 8
        System.out.println(kth.add(4)); // 8
    }
}
