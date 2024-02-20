package com.Q_One;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class One_ATest {
    @Test
    void testMinCost() {
        // Test case 1
        int[][] costs1 = {
                {1, 5, 3},
                {2, 9, 4}
        };
        int expectedMinCost1 = 5;
        assertEquals(expectedMinCost1, One_A.minCost(costs1));

        // Test case 2
        int[][] costs2 = {
                {1, 3, 2},
                {4, 6, 8},
                {3, 1, 5}
        };
        int expectedMinCost2 = 7;
        assertEquals(expectedMinCost2, One_A.minCost(costs2));

    }


}
