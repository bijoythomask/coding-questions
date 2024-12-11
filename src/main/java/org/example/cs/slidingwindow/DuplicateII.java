package org.example.cs.slidingwindow;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
 */
public class DuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] == nums[j] && Math.abs(i-j) <= k) {
                    return true;
                }
            }
        }
        return false;
    }

    //implementation using sliding window
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            //if the size of the set is greater than k, remove the element at i-k-1
            if (set.size() > k) {
                set.remove(nums[i-k-1]);
            }
            //if the set already contains the element, return true
            if (!set.add(nums[i])) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        DuplicateII duplicateII = new DuplicateII();
        int[] nums = {1, 2, 3, 1};
        int k = 3;
        System.out.println(duplicateII.containsNearbyDuplicate(nums, k));
    }
}
