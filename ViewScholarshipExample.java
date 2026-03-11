package project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class ViewScholarshipExample {

    public static void main(String[] args) {

        try {

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/UniversityDB",
                    "root",
                    "Arathi@#123");

            Statement stmt = con.createStatement();

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS students(" +
                    "student_id INT PRIMARY KEY," +
                    "name VARCHAR(50)," +
                    "department VARCHAR(50)," +
                    "marks INT," +
                    "attendance INT)");

            stmt.executeUpdate("INSERT INTO students VALUES" +
                    "(1,'Ravi','CSE',85,90)," +
                    "(2,'Meena','ECE',78,88)," +
                    "(3,'Arun','CSE',92,95)," +
                    "(4,'Kiran','ME',70,80)," +
                    "(5,'Pooja','ECE',88,91)," +
                    "(6,'Rahul','CSE',60,75)," +
                    "(7,'Sneha','IT',95,96)," +
                    "(8,'Asha','IT',82,87)," +
                    "(9,'Vikram','ME',76,90)," +
                    "(10,'Divya','CSE',89,92)");

            stmt.executeUpdate("CREATE VIEW eligible_scholarship_students AS " +
                    "SELECT * FROM students WHERE marks>80 AND attendance>85");

            ResultSet rs = stmt.executeQuery("SELECT * FROM eligible_scholarship_students");

            System.out.println("Eligible Scholarship Students:");

            while(rs.next()) {

                System.out.println(
                        rs.getInt("student_id")+" "+
                        rs.getString("name")+" "+
                        rs.getString("department")+" "+
                        rs.getInt("marks")+" "+
                        rs.getInt("attendance"));
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
