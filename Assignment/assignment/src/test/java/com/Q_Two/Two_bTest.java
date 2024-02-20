package com.Q_Two;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class Two_bTest {

    @Test
    void testGetPeopleWhoKnowSecret_SingleInterval() {
        int n = 5;
        int[][] intervals = {{0, 2}, {1, 3}, {2, 4}};
        int firstPerson = 0;

        List<Integer> result = Two_b.getPeopleWhoKnowSecret(n, intervals, firstPerson);

        assertEquals(List.of(0, 1, 2, 3, 4), result);
    }

    @Test
    void testGetPeopleWhoKnowSecret_OverlappingIntervals() {
        int n = 5;
        int[][] intervals = {{0, 2}, {1, 3}, {2, 4}};
        int firstPerson = 2;

        List<Integer> result = Two_b.getPeopleWhoKnowSecret(n, intervals, firstPerson);

        assertEquals(List.of(0, 1, 2, 3, 4), result);
    }

    @Test
    void testGetPeopleWhoKnowSecret_SingleIntervalDifferentPerson() {
        int n = 5;
        int[][] intervals = {{0, 2}};
        int firstPerson = 1;

        List<Integer> result = Two_b.getPeopleWhoKnowSecret(n, intervals, firstPerson);

        assertEquals(List.of(0, 1, 2), result);
    }

    @Test
    void testGetPeopleWhoKnowSecret_EmptyIntervals() {
        int n = 5;
        int[][] intervals = {};
        int firstPerson = 0;

        List<Integer> result = Two_b.getPeopleWhoKnowSecret(n, intervals, firstPerson);

        assertEquals(List.of(0), result);
    }
}
