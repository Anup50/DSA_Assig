package com.Q_Three;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Three_A {
    private List<Double> scores;

    public Three_A() {
        scores = new ArrayList<>();
    }

    // to add a score to the list
    public void addScore(double score) {
        scores.add(score);
    }

    public double getMedianScore() {
        // Create a copy of the scores list to avoid modifying the original list
        List<Double> sortedScores = new ArrayList<>(scores);
        // Sort the copied list in ascending order
        Collections.sort(sortedScores);
        // Get the size of the list
        int size = sortedScores.size();
        // If the list is empty, return 0
        if (size == 0) {
            return 0;
        } 
        // If the size of the list is even
        else if (size % 2 == 0) {
            // Calculate the index of the middle elements
            int mid = size / 2;
            // Return the average of the two middle elements
            return (sortedScores.get(mid - 1) + sortedScores.get(mid)) / 2.0;
        } 
        // If the size of the list is odd
        else {
            // Return the middle element
            return sortedScores.get(size / 2);
        }
    }
    public static void main(String[] args) {
        // Create an instance of Three_A
        Three_A scores = new Three_A();

        // Add some scores to the list
        scores.addScore(85.5);
        scores.addScore(90.0);
        scores.addScore(75.2);
        scores.addScore(92.3);
        scores.addScore(82.6);

        // Calculate and print the median score
        double median = scores.getMedianScore();
        System.out.println("Median Score: " + median);
    }
}
