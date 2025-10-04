package org.example.cs.heap.MeetingRoomsII;

import java.util.PriorityQueue;

public class MeetingRoomsII {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        java.util.Arrays.sort(start);
        java.util.Arrays.sort(end);
        int rooms = 0, endPtr = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (start[i] < end[endPtr]) rooms++;
            else endPtr++;
        }
        return rooms;
    }
    public static void main(String[] args) {
        MeetingRoomsII solver = new MeetingRoomsII();
        int[][] intervals = {{0,30},{5,10},{15,20}};
        System.out.println(solver.minMeetingRooms(intervals)); // Output: 2
    }
}
