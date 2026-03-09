package project1;

import java.sql.*;

public class DMLExample {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "Arathi@#123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();

            // Create Database if not exists
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS StudentsDB");
            stmt.execute("USE StudentsDB");

            // Create Table if not exists
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS Students (" +
                "StudentID INT PRIMARY KEY, " +
                "Name VARCHAR(50), " +
                "Age INT)"
            );

            // Insert data
            stmt.executeUpdate("INSERT INTO Students VALUES (1,'Amit',20)");
            stmt.executeUpdate("INSERT INTO Students VALUES (2,'Rahul',19)");

            // Select data
            ResultSet rs = stmt.executeQuery("SELECT * FROM Students");
            while (rs.next()) {
                System.out.println(rs.getInt("StudentID") + " " +
                                   rs.getString("Name") + " " +
                                   rs.getInt("Age"));
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}