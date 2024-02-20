package com.Q_Three.Three_B;

public class MinHeapp {

    // Array to store the heap elements
    private int[] heap;
    
    // Current number of elements in the heap
    private int size;
    
    // Maximum capacity of the heap
    private int capacity;

    // Constructor to initialize the heap with a given capacity
    public MinHeapp(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    // Get the index of the parent node for a given index
    private int parent(int index) {
        return (index - 1) / 2;
    }

    // Get the index of the left child node for a given index
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    // Get the index of the right child node for a given index
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    // Swap 
    private void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }

    
    private void heapifyUp(int index) {
        while (index > 0 && heap[index] < heap[parent(index)]) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    
    private void heapifyDown(int index) {
        int minIndex = index;
        int left = leftChild(index);
        int right = rightChild(index);

        // Find the smallest element among the node and its children
        if (left < size && heap[left] < heap[minIndex]) {
            minIndex = left;
        }

        if (right < size && heap[right] < heap[minIndex]) {
            minIndex = right;
        }

        // If the smallest element is not the current node, swap and continue heapifying down
        if (index != minIndex) {
            swap(index, minIndex);
            heapifyDown(minIndex);
        }
    }

    // Insert a new element into the heap
    public void insert(int value) {
        if (size >= capacity) {
            throw new IllegalStateException("Heap is full");
        }
        heap[size] = value;
        heapifyUp(size); 
        size++;
    }

    // Extract the minimum element from the heap
    public int extractMin() {
        if (size <= 0) {
            throw new IllegalStateException("Heap is empty");
        }
        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0); 
        return min;
    }

    // Peek the minimum element without removing it from the heap
    public int peekMin() {
        if (size <= 0) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap[0];
    }

    // Check if the heap is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Get the current size of the heap
    public int size() {
        return size;
    }

    
    public static void main(String[] args) {
        MinHeapp minHeap = new MinHeapp(10);
        minHeap.insert(3);
        minHeap.insert(2);
        minHeap.insert(1);
        minHeap.insert(5);
        minHeap.insert(4);

        // Display the elements of the MinHeap
        System.out.println("Min Heap Elements:");
        while (!minHeap.isEmpty()) {
            System.out.println(minHeap.extractMin());
        }
    }
}
