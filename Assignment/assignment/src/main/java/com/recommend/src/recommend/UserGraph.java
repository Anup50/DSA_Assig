package recommend;

import database.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    // Get recommendations for a given user including indirect connections
public Set<String> getRecommend(String username) {
    Set<String> recommendations = new HashSet<>();

    // Get direct followers of the given user
    Set<String> directFollowers = followersMap.getOrDefault(username, new HashSet<>());

    // Add users followed by the direct followers of the given user
    for (String directFollower : directFollowers) {
        Set<String> followersOfFollower = followersMap.getOrDefault(directFollower, new HashSet<>());
        recommendations.addAll(followersOfFollower);
    }

    // Add indirect followers of the given user
    for (String directFollower : directFollowers) {
        Set<String> indirectFollowers = new HashSet<>();

        // Get the followers of the direct followers (indirect connections)
        for (Map.Entry<String, Set<String>> entry : followersMap.entrySet()) {
            String follower = entry.getKey();
            Set<String> followers = entry.getValue();
            if (!directFollowers.contains(follower) && !follower.equals(username) && followers.contains(directFollower)) {
                indirectFollowers.add(follower);
            }
        }

        // Add indirect followers to recommendations
        recommendations.addAll(indirectFollowers);
    }

    // Remove the given user and its direct followers from recommendations
    recommendations.remove(username);
    recommendations.removeAll(directFollowers);

    return recommendations;
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
        String username = "ayam";
        Set<String> recommendations = ug.getRecommend(username);
        System.out.println("Recommendations for " + username + ": " + recommendations);
    }
}
