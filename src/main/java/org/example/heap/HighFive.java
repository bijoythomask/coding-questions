package org.example.heap;

import java.util.*;

/**
 * Given a list of scores of different students, return the average score of each student's top five scores in the order of each student's id.
 *
 * Each entry items[i] has items[i][0] the student's id, and items[i][1] the student's score. The average score is calculated using integer division.
 *
 * Implement the HighFive class:
 *
 * HighFive() Initializes the object with an empty list of scores.
 * void add(int id, int score) Updates the list with the score of the student with id = id.
 * double[][] getTopFive() Returns a list of lists of size 2, where each list contains the id and the average score of the student with the top five average scores from the high five.
 * The average score should be integer division of the sum of the top five scores and that student's number of top five scores.
 */
public class HighFive {
    private Map<Integer, PriorityQueue<Integer>> scores;

    public HighFive() {
        scores = new HashMap<>();
    }

    public void add(int id, int score) {
        scores.putIfAbsent(id, new PriorityQueue<>());
        PriorityQueue<Integer> pq = scores.get(id);
        pq.add(score);
        if (pq.size() > 5) {
            pq.poll();
        }
    }

    public double[][] getTopFive() {
        List<double[]> result = new ArrayList<>();
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : scores.entrySet()) {
            int id = entry.getKey();
            PriorityQueue<Integer> pq = entry.getValue();
            int sum = 0;
            for (int score : pq) {
                sum += score;
            }
            double average = sum / 5.0;
            result.add(new double[]{id, average});
        }
        return result.toArray(new double[result.size()][]);
    }
}