package org.example.cs.slidingwindow;

import java.util.*;

public class SlidingWindowMedian {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        List<Integer> window = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i >= k) window.remove(Integer.valueOf(nums[i - k]));
            window.add(nums[i]);
            Collections.sort(window);
            if (i >= k - 1) {
                if (k % 2 == 1) result[i - k + 1] = window.get(k / 2);
                else result[i - k + 1] = ((double)window.get(k / 2 - 1) + window.get(k / 2)) / 2;
            }
        }
        return result;
    }
    public static void main(String[] args) {
        SlidingWindowMedian solver = new SlidingWindowMedian();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        double[] res = solver.medianSlidingWindow(nums, 3);
        System.out.println(Arrays.toString(res)); // [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]
    }
}
