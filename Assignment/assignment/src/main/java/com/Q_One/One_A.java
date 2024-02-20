package com.Q_One;
import java.util.Arrays;

public class One_A {
    
    
    // Take a 2D array of integers representing the costs of venues and themes as input
    public static int minCost(int[][] costs) {
        // Get the number of venues
        int n = costs.length;
        // Get the number of themes
        int k = costs[0].length;

        // Table to store the minimum cost for each venue and theme
        int[][] memo = new int[n][k];
        
        // Initialize the memoization table with -1 values
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // Initialize the memoization table for the first venue
        for (int j = 0; j < k; j++) {
            memo[0][j] = costs[0][j];
        }

        // Calculate the minimum cost for each venue
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                // Initialize the minimum cost for the current venue and theme to a large value 
                memo[i][j] = Integer.MAX_VALUE;
                // Calculate the minimum cost by considering all previous themes
                for (int prev = 0; prev < k; prev++) {
                    // Skip if the current and previous themes are the same
                    if (j != prev) {
                        // Update the minimum cost if the current combination returns a lower cost
                        memo[i][j] = Math.min(memo[i][j], memo[i - 1][prev] + costs[i][j]);
                    }
                }
            }
        }

        // Find the minimum cost for the last venue
        int minCost = Integer.MAX_VALUE;
        for (int j = 0; j < k; j++) {
            minCost = Math.min(minCost, memo[n - 1][j]);
        }

        // Return the minimum cost
        return minCost;
    }
}
