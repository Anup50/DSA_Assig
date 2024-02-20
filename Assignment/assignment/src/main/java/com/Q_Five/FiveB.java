package com.Q_Five;
import java.util.*;

public class FiveB{

    public static List<Integer> findAffectedDevices(int[][] edges, int device_with_powerfailure) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();

        // Build the graph and calculate in-degree of each node
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(to);
            inDegree.put(to, inDegree.getOrDefault(to, 0) + 1);
        }

        // Perform DFS starting from the device with power failure node
        List<Integer> result = new ArrayList<>();
        dfs(graph, inDegree, device_with_powerfailure, device_with_powerfailure, result);

        return result;
    }

    private static void dfs(Map<Integer, List<Integer>> graph, Map<Integer, Integer> inDegree, int node, int device_with_powerfailure ,
                            List<Integer> result) {
        // If the current node has no incoming edges other than from the node representing the device with power failure node,
        // add it to the result
        if (inDegree.getOrDefault(node, 0) == 1 && graph.get(device_with_powerfailure).contains(node)) {
            result.add(node);
            // Add child nodes recursively
            addChildren(graph, node, result);
        }

        // Recursively explore the children of the current node
        if (graph.containsKey(node)) {
            for (int child : graph.get(node)) {
                dfs(graph, inDegree, child, device_with_powerfailure , result);
            }
        }
    }
    private static void addChildren(Map<Integer, List<Integer>> graph, int node, List<Integer> result) {
        if (graph.containsKey(node)) {
            for (int child : graph.get(node)) {
                result.add(child);
                addChildren(graph, child, result); // Recursively add children of children
            }
        }
    }
    public static void main(String[] args) {
        int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 1, 6 }, { 2, 4 }, { 4, 6 }, { 4, 5 }, { 5, 7 } };
        int device_with_powerfailure= 4;

        List<Integer> uniqueParents = findAffectedDevices(edges, device_with_powerfailure);

        System.out.println("When there is power failure on device " + device_with_powerfailure+ ", the following devices are affected:");
        for (int node : uniqueParents) {
            System.out.println(node);
        }
    }
}
