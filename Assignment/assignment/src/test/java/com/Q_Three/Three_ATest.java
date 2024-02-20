package com.Q_Three;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Three_ATest {

    @Test
    public void testGetMedianScore() {
        Three_A scoreTracker = new Three_A();
        scoreTracker.addScore(85.5);
        scoreTracker.addScore(92.3);
        scoreTracker.addScore(77.8);
        scoreTracker.addScore(90.1);
        double median1 = scoreTracker.getMedianScore();
        assertEquals(87.8, median1, 0.01);

        scoreTracker.addScore(81.2);
        scoreTracker.addScore(88.7);
        double median2 = scoreTracker.getMedianScore();
        assertEquals(87.1, median2, 0.01);
    }

    @Test
    public void testGetMedianScoreEmpty() {
        Three_A scoreTracker = new Three_A();
        double median = scoreTracker.getMedianScore();
        assertEquals(0.0, median, 0.01);
    }
}