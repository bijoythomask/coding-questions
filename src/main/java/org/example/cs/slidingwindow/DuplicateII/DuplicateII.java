package org.example.cs.slidingwindow.DuplicateII;

import java.util.HashMap;

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

    public static void main(String[] args) {
        DuplicateII solver = new DuplicateII();
        int[] nums = {1, 2, 3, 1};
        System.out.println(solver.containsNearbyDuplicate(nums, 3)); // true
    }
}
