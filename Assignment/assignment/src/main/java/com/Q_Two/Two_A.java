package com.Q_Two;

public class Two_A {

    static int minMoves(int[] machines) {
        // to store the total number of dresses across all machines
        int total = 0;
        // Get the number of machines
        int n = machines.length;
        // Calculate the total number of dresses across all machines
        for (int machine : machines) {
            total += machine;
        }
        // return -1 if the total number of dresses cannot be evenly distributed among machines
        if (total % n != 0) {
            return -1;
        }
        // Calculate the number of dresses each machine should have after equalization
        int avg = total / n;
        // to store the maximum number of moves required
        int moves = 0;
        // to store the difference between actual dresses and the average for each machine
        int diff = 0;

        // move through each machine to equalize the number of dresses
        for (int i = 0; i < n; i++) {
            // Calculate the difference between the actual number of dresses and the average
            diff += machines[i] - avg;
            // Update the maximum number of moves required
            moves = Math.max(moves, Math.abs(diff));
            // Equalize the number of dresses for the current machine
            machines[i] = avg;
        }

        // Return the minimum number of moves required to equalize the dresses
        return moves;
    }

    public static void main(String[] args) {
        
        int[] machines = {1,0,5};
        
        System.out.println("Minimum number of moves to equalize dresses: " + minMoves(machines));
    }
}
