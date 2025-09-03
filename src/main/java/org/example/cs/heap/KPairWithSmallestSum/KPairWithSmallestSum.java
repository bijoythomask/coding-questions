package org.example.cs.heap.KPairWithSmallestSum;

import java.util.PriorityQueue;

public class KPairWithSmallestSum {
    public int[] kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] + a[1] - b[0] - b[1]);
        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            for (int j = 0; j < Math.min(nums2.length, k); j++) {
                pq.offer(new int[]{nums1[i], nums2[j]});
            }
        }
        int[] res = new int[k * 2];
        int idx = 0;
        while (k-- > 0 && !pq.isEmpty()) {
            int[] pair = pq.poll();
            res[idx++] = pair[0];
            res[idx++] = pair[1];
        }
        return res;
    }
    public static void main(String[] args) {
        KPairWithSmallestSum solver = new KPairWithSmallestSum();
        int[] nums1 = {1,7,11};
        int[] nums2 = {2,4,6};
        int[] res = solver.kSmallestPairs(nums1, nums2, 3);
        for (int i = 0; i < res.length; i += 2) System.out.println(res[i] + ", " + res[i+1]);
    }
}
