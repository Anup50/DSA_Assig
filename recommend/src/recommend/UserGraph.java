package recommend;

import database.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.LinkedList;
import java.util.Queue;
public class UserGraph {
    private Set<Edge> edges; // Set of edges representing connections between users
    private Map<String, Set<String>> followersMap; // Map to store followers for each user

    public UserGraph() {
        edges = new HashSet<>();
        followersMap = new HashMap<>();
    }

    // Add a new user to the graph
    public void addUser(String username) {
        followersMap.put(username, new HashSet<>());
    }

    // Add a connection between two users
    public void addConnection(String follower, String followee) {
        Edge edge = new Edge(follower, followee);
        edges.add(edge);
        followersMap.get(followee).add(follower);
        // Update the 'following' set for the follower
        followersMap.computeIfAbsent(follower, k -> new HashSet<>()).add(followee);
    }
public Set<String> getRecommend(String username) {
    Set<String> recommendations = new HashSet<>();
    Set<String> visitedUsers = new HashSet<>(); // Track visited users

    // Initialize queue with user's followers
    Queue<String> unvisitedUsers = new LinkedList<>(followersMap.getOrDefault(username, new HashSet<>()));

    while (!unvisitedUsers.isEmpty()) {
        String currentUser = unvisitedUsers.poll();

        // Skip if already explored or the given user
        if (visitedUsers.contains(currentUser) || currentUser.equals(username)) {
            continue;
        }

        visitedUsers.add(currentUser);

        // Check if already followed directly before adding to recommendations
        if (!followersMap.getOrDefault(username, new HashSet<>()).contains(currentUser)) {
            recommendations.add(currentUser);
        }

        // Iterate through followers and add to queue if not explored
        Set<String> followers = followersMap.getOrDefault(currentUser, new HashSet<>());
        unvisitedUsers.addAll(followers);
    }

    return recommendations;
}


private void exploreConnections(Set<String> currentUsers, Set<String> recommendations, Map<String, Set<String>> followersMap, String username) {
    
}

    public void loadDataFromDatabase(Connection connection) {
        if (connection == null) {
            System.out.println("Database connection is not established.");
            return;
        }

        try {
            // Query to retrieve users and their connections
            String query = "SELECT follower_username, followee_username FROM Connections";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Clear the existing graph
            edges.clear();
            followersMap.clear();

            // Iterate through the result set and populate the graph
            while (resultSet.next()) {
                String follower = resultSet.getString("follower_username");
                String followee = resultSet.getString("followee_username");

                addUser(follower);
                addUser(followee);
                addConnection(follower, followee);
            }

            // Print the graph to verify users and connections are added
            printGraph();

            System.out.println("Data loaded from the database and graph filled successfully.");

            // Close the result set and statement
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            System.out.println("Error loading data from database: " + e.getMessage());
        }
    }

    // Method to print the graph
    public void printGraph() {
        System.out.println("Nodes:");
        for (String user : followersMap.keySet()) {
            System.out.println(user);
        }

        System.out.println("\nEdges:");
        for (Edge edge : edges) {
            System.out.println(edge.follower + " -> " + edge.followee);
        }
    }

    private static class Edge {
        private String follower;
        private String followee;

        public Edge(String follower, String followee) {
            this.follower = follower;
            this.followee = followee;
        }

        @Override
        public int hashCode() {
            return follower.hashCode() + followee.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Edge)) return false;
            Edge otherEdge = (Edge) obj;
            return this.follower.equals(otherEdge.follower) && this.followee.equals(otherEdge.followee);
        }
    }

    public static void main(String[] args) {
        UserGraph ug = new UserGraph();

        // Establish a database connection
        Connection conn =  DbConnection.dbConnect();

        // Load data from the database and fill the graph with users and connections
        ug.loadDataFromDatabase(conn);

        // Close the database connection
        DbConnection.closeConnection(conn);

        // Test recommendations for a user
        String username = "anup";
        Set<String> recommendations = ug.getRecommend(username);
        System.out.println("Recommendations for " + username + ": " + recommendations);
    }
}
