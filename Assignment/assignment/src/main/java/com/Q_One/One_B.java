package com.Q_One;

import java.util.PriorityQueue;

public class One_B {
    public int minBuildTime(int[] engines, int splitTime) {
        // Create a priority queue to hold the engines, with min-heap
        PriorityQueue<Integer> engineQueue = new PriorityQueue<>();
        // Add engines to the priority queue
        for (int engine : engines) {
            engineQueue.offer(engine);
        }
        // Continue combining engines until there's only one engine left
        while (engineQueue.size() > 1) {
            // Remove the smallest two engines from the queue
            int fastestWorkerEngine = engineQueue.poll();
            int secondFastestWorkerEngine = engineQueue.poll();
            // The two workers team up to split and build the next engine;
            // add the combined time back to the priority queue.
            // The time is the time of the second worker plus the split time.
            engineQueue.offer(secondFastestWorkerEngine + splitTime);
        }
        // The remaining engine in the priority queue requires the longest time to
        // complete.
        // This will be the total time required to build all the engines.
        return engineQueue.poll();
    }
}
