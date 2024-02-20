package com.Q_Three.Three_B;

public class MinHeapp {

    private int[] heap;
    private int size;
    private int capacity;

    public MinHeapp(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

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

        if (left < size && heap[left] < heap[minIndex]) {
            minIndex = left;
        }

        if (right < size && heap[right] < heap[minIndex]) {
            minIndex = right;
        }

        if (index != minIndex) {
            swap(index, minIndex);
            heapifyDown(minIndex);
        }
    }

    public void insert(int value) {
        if (size >= capacity) {
            throw new IllegalStateException("Heap is full");
        }
        heap[size] = value;
        heapifyUp(size);
        size++;
    }

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

    public int peekMin() {
        if (size <= 0) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

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

        System.out.println("Min Heap Elements:");
        while (!minHeap.isEmpty()) {
            System.out.println(minHeap.extractMin());
        }
    }
}
