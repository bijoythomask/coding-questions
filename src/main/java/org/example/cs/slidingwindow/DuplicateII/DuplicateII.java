package org.example.cs.slidingwindow.DuplicateII;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) {
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public boolean containsNearbyDuplicateTwoPointers(int[] nums, int k) {
        Set<Integer> window = new HashSet<>();
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            if (right - left > k) {
                window.remove(nums[left]);
                left++;
            }
            if (!window.add(nums[right])) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        DuplicateII solver = new DuplicateII();
        int[] nums = {1, 2, 3, 1};
        System.out.println("HashMap approach: " + solver.containsNearbyDuplicate(nums, 3)); // true
        System.out.println("Two-pointer approach: " + solver.containsNearbyDuplicateTwoPointers(nums, 3)); // true
    }
}
