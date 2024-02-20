package Database;

import java.sql.*;

public class DbConnection {
    public static Connection dbConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/recommend_sys", "anup", "15akc#");
            System.out.println("Connected to database");
            return conn;
        } catch (Exception e) {
            System.out.println("Failed to connect to database: " + e.getMessage());
        }
        return null;
    }

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Database connection closed");
            }
        } catch (SQLException e) {
            System.out.println("Failed to close database connection: " + e.getMessage());
        }
    }
}
