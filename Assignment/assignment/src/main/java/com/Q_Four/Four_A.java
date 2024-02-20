
package com.Q_Four;
import java.util.*;

public class Four_A {
    // Class representing a state with position and keys collected
    static class State {
        int x; // x-coordinate
        int y; // y-coordinate
        int collectedKeys; // Bitmap representing collected keys

        State(int x, int y, int collectedKeys) {
            this.x = x;
            this.y = y;
            this.collectedKeys = collectedKeys;
        }
    }

    // Function to find the shortest path to collect all keys
    public int shortestPathAllKeys(char[][] grid) {
        // defining directions: right, down, left, up
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int m = grid.length; // Number of rows
        int n = grid[0].length; // Number of columns
        int keysCount = countKeys(grid); //total  number  keys
        int allKeys = (1 << keysCount) - 1; // All keys collected bitmap
        int[] start = findStart(grid); // Starting position
        int steps = 0; // Moves taken
        Queue<State> queue = new LinkedList<>(); 
        queue.offer(new State(start[0], start[1], 0)); // Add starting state
        boolean[][][] visited = new boolean[m][n][1 << keysCount]; // Visited states

        // Mark starting state as visited
        visited[start[0]][start[1]][0] = true;

        // BFS
        while (!queue.isEmpty()) {
            steps++; 
            int size = queue.size(); // Number of states in the current level
            for (int i = 0; i < size; i++) {
                State current = queue.poll(); 
                int x = current.x; // Current x-coordinate
                int y = current.y; // Current y-coordinate
                int collectedKeys = current.collectedKeys; // Keys collected so far
                for (int[] dir : dirs) {
                    int newX = x + dir[0]; // New x-coordinate
                    int newY = y + dir[1]; // New y-coordinate
                    // Check if new position is within bounds and is not a wall
                    if (newX < 0 || newX >= m || newY < 0 || newY >= n || grid[newX][newY] == 'W')
                        continue;
                    char cell = grid[newX][newY]; // 1 <= k <= 6, 
                    int newCollectedKeys = ('a' <= cell && cell <= 'f') ? (collectedKeys | (1 << (cell - 'a'))) : collectedKeys;
                    // If all keys are collected, return steps
                    if (newCollectedKeys == allKeys)
                        return steps;
                    // If state is already visited, skip
                    if (visited[newX][newY][newCollectedKeys])
                        continue;
                    // If cell is locked and key is missing, skip
                    if ('A' <= cell && cell <= 'F' && ((collectedKeys >> (cell - 'A')) & 1) == 0)
                        continue;
                    // Add new state to queue and mark as visited
                    queue.offer(new State(newX, newY, newCollectedKeys));
                    visited[newX][newY][newCollectedKeys] = true;
                }
            }
        }

        return -1; // If all keys cannot be collected, return -1
    }

    // to count the total number of keys in the grid
    private int countKeys(char[][] grid) {
        int count = 0; // Initialize count
        for (char[] row : grid) { 
            for (char cell : row) {
                if (cell >= 'a' && cell <= 'f') { // If cell is a key
                    count++; 
                }
            }
        }
        return count; 
    }

    // find starting position
    private int[] findStart(char[][] grid) {
        for (int i = 0; i < grid.length; i++) { 
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 'S') { // If cell is S, it is the starting point
                    return new int[]{i, j}; // Return starting position
                }
            }
        }
        throw new IllegalArgumentException("Start point not found."); //if start point not found
    }

    // Main method for testing
    public static void main(String[] args) {
        Four_A minTime = new Four_A(); 
        char[][] grid = { // Input grid
                { 'S', 'P', 'a', 'P', 'P' },
                { 'W', 'W', 'W', 'P', 'W' },
                { 'b', 'P', 'A', 'P', 'R' }
        };
        System.out.println(minTime.shortestPathAllKeys(grid)); 
    }
}
