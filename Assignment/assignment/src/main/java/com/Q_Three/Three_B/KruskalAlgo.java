package com.Q_Three.Three_B;

import java.util.*;

// Class to represent an edge in the graph
class Edge implements Comparable<Edge> {
    int source, destination, weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}

public class KruskalAlgo {
    // Method to find the minimum spanning tree using Kruskal's algorithm
    public static List<Edge> kruskalMST(List<Edge> edges, int numberOfVertices) {
        // Create a parent array for disjoint set
        int[] parent = new int[numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            parent[i] = i;
        }

        // Initialize variables
        List<Edge> minimumSpanningTree = new ArrayList<>();
        int edgeCount = 0;

        // Create a MinHeapp instance
        MinHeapp minHeap = new MinHeapp(edges.size());
        for (Edge edge : edges) {
            minHeap.insert(edge.weight);
        }

        // Iterate through sorted edges
        while (edgeCount < numberOfVertices - 1 && !minHeap.isEmpty()) {
            int weight = minHeap.extractMin();
            for (Edge edge : edges) {
                if (edge.weight == weight) {
                    int sourceParent = findParent(parent, edge.source);
                    int destinationParent = findParent(parent, edge.destination);

                    // If including this edge does not cause a cycle, add it to the minimum spanning tree
                    if (sourceParent != destinationParent) {
                        minimumSpanningTree.add(edge);
                        edgeCount++;
                        parent[sourceParent] = destinationParent;
                    }
                    break;
                }
            }
        }

        return minimumSpanningTree;
    }

    // Helper method to find the parent of a vertex in the disjoint set
    private static int findParent(int[] parent, int vertex) {
        if (parent[vertex] != vertex) {
            parent[vertex] = findParent(parent, parent[vertex]);
        }
        return parent[vertex];
    }

    public static void main(String[] args) {
        // Example usage
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 10));
        edges.add(new Edge(0, 2, 6));
        edges.add(new Edge(0, 3, 5));
        edges.add(new Edge(1, 3, 15));
        edges.add(new Edge(2, 3, 4));

        List<Edge> minimumSpanningTree = kruskalMST(edges, 4);
        System.out.println("Edges in the minimum spanning tree:");
        for (Edge edge : minimumSpanningTree) {
            System.out.println(edge.source + " - " + edge.destination + " : " + edge.weight);
        }
    }
}
