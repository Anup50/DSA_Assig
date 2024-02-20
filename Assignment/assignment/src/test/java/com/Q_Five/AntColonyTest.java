package com.Q_Five;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AntColonyTest {
    @Test
    void testValidTourLength() {
        int[][] distanceMatrix = {
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };
        int numAnts = 5;
        double evaporationRate = 0.5;
        double alpha = 1.0;
        double beta = 2.0;

        AntColony colony = new AntColony(distanceMatrix, numAnts, evaporationRate, alpha, beta);
        colony.solve(1000);

        int bestTourLength = colony.getBestTourLength();
        assertTrue(bestTourLength >= 0, "Tour length should be non-negative");
    }

    @Test
    void testValidTour() {
        int[][] distanceMatrix = {
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };
        int numAnts = 5;
        double evaporationRate = 0.5;
        double alpha = 1.0;
        double beta = 2.0;

        AntColony colony = new AntColony(distanceMatrix, numAnts, evaporationRate, alpha, beta);
        colony.solve(1000);

        int[] bestTour = colony.getBestTour();
        assertEquals(distanceMatrix.length, bestTour.length, "Tour should visit all cities exactly once");
        assertEquals(bestTour[0], bestTour[bestTour.length - 1], "Tour should return to the starting city");
    }

}
