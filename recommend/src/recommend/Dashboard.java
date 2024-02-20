
package recommend;
import database.DbConnection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.Connection;
import java.util.Set;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import recommend.UserGraph;

public class Dashboard extends javax.swing.JFrame {
    String username;
    UserGraph userGraph; // Declare UserGraph variable

public Dashboard(String username) {
    initComponents();
    this.username = username;
    System.out.println(username);
    userGraph = new UserGraph(); // Instantiate UserGraph
    setRecordsToTable(userTable, username);
    usernamelbl.setText("Welcome back, " + username);
    // Get and set recommended followers to the table
    Set<String> recommendedFollowers = getRecommendedFollowers(username);
    if (recommendedFollowers != null) {
        for (String follower : recommendedFollowers) {
            ((DefaultTableModel) userTable.getModel()).addRow(new Object[]{follower});
        }
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        suser = new javax.swing.JTextField();
        sfollowbtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        postlbl = new javax.swing.JTextField();
        post = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        usernamelbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Username"
            }
        ));
        jScrollPane1.setViewportView(userTable);

        suser.setText("search user");

        sfollowbtn.setText("follow");
        sfollowbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sfollowbtnActionPerformed(evt);
            }
        });

        jLabel1.setText("WE RECOMMEND YOU FOLLOW");

        postlbl.setText("POST HERE");
        postlbl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postlblActionPerformed(evt);
            }
        });

        post.setText("POST");
        post.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                postActionPerformed(evt);
            }
        });

        usernamelbl.setText("jLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(suser, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sfollowbtn)
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(usernamelbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(postlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(post)
                .addGap(134, 134, 134))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(suser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sfollowbtn)
                    .addComponent(jLabel2)
                    .addComponent(usernamelbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(postlbl)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(post)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void sfollowbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sfollowbtnActionPerformed
    // Get the username of the user to follow from the input field
    String to_follow = suser.getText();
    
    // Get the username of the current user (you need to implement this)
    String currentUser = username; // Implement this method

    // Check if the user to follow is not the same as the current user
    if (!to_follow.equals(currentUser)) {
        // Save the follow relationship to the database
        saveFollowRelationship(currentUser, to_follow); // Implement this method
    } else {
        // Handle the case where the user tries to follow themselves
        System.out.println("You cannot follow yourself.");
    }
    }//GEN-LAST:event_sfollowbtnActionPerformed

    private void postActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postActionPerformed
    // Get the content of the post and the username of the poster
    String postContent = postlbl.getText();
    String posterUsername = username;

    // Call the method to save the post to the database
    savePostToDatabase(posterUsername, postContent);
    }//GEN-LAST:event_postActionPerformed

    private void postlblActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_postlblActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_postlblActionPerformed

    // Method to save the post to the database
private void savePostToDatabase(String posterUsername, String postContent) {
    // Define your SQL query to insert the post
    String query = "INSERT INTO Posts (username, content) VALUES (?, ?)";

    try (Connection conn = DbConnection.dbConnect();
         PreparedStatement statement = conn.prepareStatement(query)) {
        // Set the parameters for the SQL query
        statement.setString(1, posterUsername);
        statement.setString(2, postContent);

        // Execute the SQL query to insert the post
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Post saved successfully.");
            JOptionPane.showMessageDialog(null, "posetd successfully");
        } else {
            System.out.println("Failed to save the post.");
        }
    } catch (Exception e) {
        System.out.println("Error saving the post: " + e.getMessage());
    }
}

    // Method to save the follow relationship to the database
private void saveFollowRelationship(String followerUsername, String followeeUsername) {


// Define your SQL query to insert the follow relationship
String query = "INSERT INTO Connections (follower_username, followee_username) VALUES (?, ?)";

try (Connection conn = DbConnection.dbConnect();
     PreparedStatement statement = conn.prepareStatement(query)) {
    // Set the parameters for the SQL query
    statement.setString(1, followerUsername);
    statement.setString(2, followeeUsername);

    // Execute the SQL query to insert the follow relationship
    int rowsInserted = statement.executeUpdate();
    if (rowsInserted > 0) {
        System.out.println("Follow relationship saved successfully.");
        JOptionPane.showMessageDialog(null, "You now follow"+ followeeUsername);
    } else {
        System.out.println("Failed to save follow relationship.");
    }
} catch (Exception e) {
    System.out.println("Error saving follow relationship: " + e.getMessage());
    JOptionPane.showMessageDialog(null, "You already follow"+ followeeUsername);
}


}

    
    private void setRecordsToTable(JTable userTable, String username) {
        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        model.setRowCount(0); // Clear existing table data
        
        Set<String>recommendedFollowers = userGraph.getRecommend(username);
        System.out.println("Recommendations for " + username + ": " + recommendedFollowers);

        if (recommendedFollowers != null) {
            for (String follower : recommendedFollowers) {
                model.addRow(new Object[]{follower});
            }
        }
    }
    
public static Set<String> getRecommendedFollowers(String username) {
        Set<String> recommendedFollowers = null;
        try (Connection conn = DbConnection.dbConnect();) {
            // Create an instance of UserGraph
            UserGraph userGraph = new UserGraph();

            // Load data from the database and fill the graph
            userGraph.loadDataFromDatabase(conn);

            // Get recommended followers for the specified user
            recommendedFollowers = userGraph.getRecommend(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recommendedFollowers;
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard("test user").setVisible(true);
                System.out.println();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton post;
    private javax.swing.JTextField postlbl;
    private javax.swing.JButton sfollowbtn;
    private javax.swing.JTextField suser;
    private javax.swing.JTable userTable;
    private javax.swing.JLabel usernamelbl;
    // End of variables declaration//GEN-END:variables
}
