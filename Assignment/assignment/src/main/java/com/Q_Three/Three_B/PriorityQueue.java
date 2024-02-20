package com.Q_Three.Three_B;

import java.util.NoSuchElementException;

public class PriorityQueue {

    private MinHeapp minHeap;

    public PriorityQueue(int capacity) {
        minHeap = new MinHeapp(capacity);
    }

    public void enqueue(int priority) {
        minHeap.insert(priority);
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue is empty");
        }
        return minHeap.extractMin();
    }

    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue is empty");
        }
        return minHeap.peekMin();
    }

    public boolean isEmpty() {
        return minHeap.isEmpty();
    }

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
