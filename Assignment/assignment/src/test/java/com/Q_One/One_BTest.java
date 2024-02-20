package com.Q_One;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class One_BTest {

    @Test
    void testMinBuildTime() {
        One_B one_b = new One_B(); // Create an instance of the One_B class
        int[] blocks = {3,4,5,2};
        int splitTime = 2;
        int expectedMinTime = 9;
        assertEquals(expectedMinTime, one_b.minBuildTime(blocks, splitTime));
    }
}
