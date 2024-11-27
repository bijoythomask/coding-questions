package org.example.heap;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * We have a collection of stones, each stone has a positive integer weight.
 *
 * Each turn, we choose the two heaviest stones and smash them together. Suppose the stones have weights x and y with x <= y.
 * The result of this smash is:
 *
 * If x == y, both stones are totally destroyed;
 * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has a new weight y - x.
 * At the end, there is at most one stone left. Return the weight of this stone (or 0 if there are no stones left.)
 */
public class LastStoneWeight {
    public static void main(String[] args) {
        int[] stones = {2, 7, 4, 1, 8, 1};
        LastStoneWeight lastStoneWeight = new LastStoneWeight();
        System.out.println(lastStoneWeight.lastStoneWeight(stones));
    }

    private int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int stone : stones) {
            maxHeap.add(stone);
        }
        while (maxHeap.size() > 1) {
            int y = maxHeap.poll();
            int x = maxHeap.poll();
            if (x != y) {
                maxHeap.add(y - x);
            }
        }
        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }
}
