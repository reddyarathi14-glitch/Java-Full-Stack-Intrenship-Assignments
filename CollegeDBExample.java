package project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CollegeDBExample {

    public static void main(String[] args) {
        
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "Arathi@#123";

        try {
            // Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create Connection
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();

            // Create Database
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS CollegeDB");
            stmt.executeUpdate("USE CollegeDB");

            // Create Students Table
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS Students (" +
                "StudentID INT PRIMARY KEY," +
                "Name VARCHAR(50) NOT NULL," +
                "Age INT," +
                "Course VARCHAR(50)," +
                "AdmissionDate DATE)"
            );

            // Create Employees Table
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS Employees (" +
                "EmpID INT PRIMARY KEY," +
                "Name VARCHAR(50) NOT NULL," +
                "Salary DECIMAL(10,2) CHECK (Salary > 10000)," +
                "Email VARCHAR(100) UNIQUE)"
            );

            // Alter Table Add Column
            stmt.executeUpdate("ALTER TABLE Students ADD Email VARCHAR(100)");

            // Modify Column
            stmt.executeUpdate("ALTER TABLE Students MODIFY Age INT NOT NULL");

            // Drop Column
            stmt.executeUpdate("ALTER TABLE Students DROP COLUMN Email");

            // Add Constraint
            stmt.executeUpdate("ALTER TABLE Students ADD CONSTRAINT chk_age CHECK (Age >= 18)");

            // Truncate Table
            stmt.executeUpdate("TRUNCATE TABLE Students");

            // Rename Table
            stmt.executeUpdate("RENAME TABLE Students TO Student_Details");

            // Drop Table
            stmt.executeUpdate("DROP TABLE Student_Details");

            // Drop Database
            stmt.executeUpdate("DROP DATABASE CollegeDB");

            System.out.println("All operations executed successfully!");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}