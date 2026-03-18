package sqlDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class JoinDemo {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/university_db";
        String user = "root";
        String password = "Arathi@#123";

        try {

            // Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create Connection
            Connection conn = DriverManager.getConnection(url, user, password);

            // Create Statement
            Statement stmt = conn.createStatement();

            // INNER JOIN Query
            String innerQuery =
                    "SELECT Students.name, Clubs.club_name " +
                    "FROM Students " +
                    "INNER JOIN Student_Club " +
                    "ON Students.student_id = Student_Club.student_id " +
                    "INNER JOIN Clubs " +
                    "ON Student_Club.club_id = Clubs.club_id";

            ResultSet rs1 = stmt.executeQuery(innerQuery);

            System.out.println("INNER JOIN RESULT:");
            while (rs1.next()) {
                System.out.println(rs1.getString("name") + " - " + rs1.getString("club_name"));
            }

            // LEFT JOIN Query
            String leftQuery =
                    "SELECT Users.name, Subscriptions.plan " +
                    "FROM Users " +
                    "LEFT JOIN Subscriptions " +
                    "ON Users.user_id = Subscriptions.user_id";

            ResultSet rs2 = stmt.executeQuery(leftQuery);

            System.out.println("\nLEFT JOIN RESULT:");
            while (rs2.next()) {
                System.out.println(rs2.getString("name") + " - " + rs2.getString("plan"));
            }

            // RIGHT JOIN Query
            String rightQuery =
                    "SELECT Books.title, Authors.author_name " +
                    "FROM Authors " +
                    "RIGHT JOIN Books " +
                    "ON Authors.author_id = Books.author_id";

            ResultSet rs3 = stmt.executeQuery(rightQuery);

            System.out.println("\nRIGHT JOIN RESULT:");
            while (rs3.next()) {
                System.out.println(rs3.getString("title") + " - " + rs3.getString("author_name"));
            }

            // Close Connection
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
