package project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DDLExample {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/school_db";
        String username = "root";
        String password = "Arathi@#123"; // change to your MySQL password

        try {
            // Connect to database
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();

            // CREATE TABLE
            String create = "CREATE TABLE students (" +
                            "id INT PRIMARY KEY, " +
                            "name VARCHAR(50), " +
                            "age INT)";
            stmt.executeUpdate(create);
            System.out.println("Table Created Successfully");

            // ALTER TABLE
            String alter = "ALTER TABLE students ADD email VARCHAR(100)";
            stmt.executeUpdate(alter);
            System.out.println("Column Added Successfully");

            // TRUNCATE TABLE
            String truncate = "TRUNCATE TABLE students";
            stmt.executeUpdate(truncate);
            System.out.println("Table Truncated Successfully");

            // DROP TABLE
            String drop = "DROP TABLE students";
            stmt.executeUpdate(drop);
            System.out.println("Table Dropped Successfully");

            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}