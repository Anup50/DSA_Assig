package com.Q_Two;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Two_ATest {

    @Test
    void testCalculateMinMoves() {

        int[] machines2 = {1,0,5};


        assertEquals(3, Two_A.minMoves(machines2));

    }
}
