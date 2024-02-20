package com.Q_Three.Three_B;

import java.util.NoSuchElementException;

public class PriorityQueue {

    
    private MinHeapp minHeap;

    // Constructor to initialize the priority queue with a given capacity
    public PriorityQueue(int capacity) {
        minHeap = new MinHeapp(capacity);
    }

    // Enqueue operation to insert a priority into the priority queue
    public void enqueue(int priority) {
        minHeap.insert(priority); // to insert the priority into the min heap to maintain the priority order.
    }

    // Dequeue operation to remove and return the highest priority element
    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue is empty");
        }
        return minHeap.extractMin(); 
    }

    // Peek to return the highest priority element without removing it
    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue is empty");
        }
        return minHeap.peekMin(); 
    }

    // Check if the priority queue is empty
    public boolean isEmpty() {
        return minHeap.isEmpty();
    }

    // Get the current size of the priority queue
    public int size() {
        return minHeap.size();
    }

   
    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue(10);
        priorityQueue.enqueue(3);
        priorityQueue.enqueue(2);
        priorityQueue.enqueue(1);
        priorityQueue.enqueue(5);
        priorityQueue.enqueue(4);

       
        System.out.println("Priority Queue Elements:");
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.dequeue());
        }
    }
}
