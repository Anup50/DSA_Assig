package com.Q_Two;

import java.util.ArrayList;
import java.util.List;

public class Two_b {
    public static List<Integer> getPeopleWhoKnowSecret(int n, int[][] intervals, int firstPerson) {
        // Boolean array to track who knows the secret
        boolean[] knowsSecret = new boolean[n];
        // Mark the first person as knowing the secret
        knowsSecret[firstPerson] = true;
        // Iterate through each interval
        for (int[] interval : intervals) {
            // Check each person within the interval
            for (int i = interval[0]; i <= interval[1]; i++) {
                // If someone within the interval knows the secret
                if (knowsSecret[i]) {
                    // Mark all people within the interval as knowing the secret
                    for (int j = interval[0]; j <= interval[1]; j++) {
                        knowsSecret[j] = true;
                    }
                    // Break out of the loop since we've already updated the interval
                    break;
                }
            }
        }

        // List to store people who know the secret
        List<Integer> result = new ArrayList<>();
        // Add people who know the secret to the result list
        for (int i = 0; i < n; i++) {
            if (knowsSecret[i]) {
                result.add(i);
            }
        }
        // Return the list of people who know the secret
        return result;
    }
}
