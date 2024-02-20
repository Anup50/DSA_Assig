package com.Q_Four;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Four_ATest {

    @Test
    void shortestPathAllKeys() {
        Four_A solver = new Four_A();

        char[][] grid = {
                {'S', 'P', 'a', 'P', 'P'},
                {'W', 'W', 'W', 'P', 'W'},
                {'b', 'P', 'A', 'P', 'B'}
        };
        int expectedSteps = 8;
        assertEquals(expectedSteps, solver.shortestPathAllKeys(grid));
    }

    @Test
    void allKeysNotCollectable() {
        Four_A solver = new Four_A();

        char[][] grid = {
                {'S', 'a', 'P', 'b', 'P'},
                {'W', 'W', 'W', 'W', 'W'},
                {'W', 'c', 'P', 'd', 'W'},
                {'W', 'W', 'P', 'W', 'W'},
                {'W', 'W', 'P', 'P', 'P'}
        };
        int expectedSteps = -1;//can't collect all the keys as 2nd row is all walls
        assertEquals(expectedSteps, solver.shortestPathAllKeys(grid));
    }

    @Test
    void C() {
        Four_A solver = new Four_A();

        char[][] grid = {
                {'S', 'P', 'P', 'P', 'P'},
                {'W', 'W', 'W', 'W', 'W'},
                {'W', 'P', 'W', 'P', 'W'},
                {'W', 'W', 'W', 'W', 'W'},
                {'W', 'W', 'W', 'S', 'a'}
        };
        int expectedSteps = -1; // Can't collect all keys
        assertEquals(expectedSteps, solver.shortestPathAllKeys(grid));
    }
}
