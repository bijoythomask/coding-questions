package org.example.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Q: You are given two integer arrays nums1 and nums2 sorted in non-decreasing order, and two integers k and m.
 * Define a pair (u,v) which consists of one element from the first array and one element from the second array.
 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 * Example 1:
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3, m = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence:
 */
public class KPairWithSmallestSum {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return result;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] + a[1]) - (b[0] + b[1]));

        for (int i = 0; i < nums1.length && i < k; i++) {
            pq.offer(new int[]{nums1[i], nums2[0], 0});
        }

        while (k-- > 0 && !pq.isEmpty()) {
            int[] cur = pq.poll();
            result.add(Arrays.asList(cur[0], cur[1]));
            if (cur[2] == nums2.length - 1) continue;
            pq.offer(new int[]{cur[0], nums2[cur[2] + 1], cur[2] + 1});
        }

        return result;
    }

    public static void main(String[] args) {
        KPairWithSmallestSum kPairWithSmallestSum = new KPairWithSmallestSum();
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        int k = 3;
        List<List<Integer>> result = kPairWithSmallestSum.kSmallestPairs(nums1, nums2, k);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }
}
