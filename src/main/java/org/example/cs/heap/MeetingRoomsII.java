package org.example.cs.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
253. Meeting Rooms II
Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.

 */
public class MeetingRoomsII {
    public static void main(String[] args) {
        MeetingRoomsII meetingRoomsII = new MeetingRoomsII();
        int[][] intervals = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println(meetingRoomsII.minMeetingRooms(intervals)); // 2
    }

    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        // Sort the intervals by start time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        // Use a min heap to track the end time of meetings
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        // Add the first meeting's end time to the heap
        heap.add(intervals[0][1]);

        // Iterate over the remaining intervals
        for (int i = 1; i < intervals.length; i++) {
            // If the room due to free up the earliest is free, remove it from the heap
            if (intervals[i][0] >= heap.peek()) {
                heap.poll();
            }

            // Add the current meeting's end time to the heap
            heap.add(intervals[i][1]);
        }

        // The size of the heap is the number of rooms required
        return heap.size();
    }

}
