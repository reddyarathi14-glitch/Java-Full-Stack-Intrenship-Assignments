package project1;

import java.sql.*;

public class MetroDatabase {

    static final String URL = "jdbc:mysql://localhost:3306/";
    static final String USER = "root";
    static final String PASSWORD = "Arathi@#123"; // replace with your MySQL password

    public static void main(String[] args) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to MySQL server
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = con.createStatement();

            // Create Database
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS metro_db");
            System.out.println("Database created!");

            // Use database
            stmt.execute("USE metro_db");

            // Create Stations table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Stations (" +
                    "station_id INT PRIMARY KEY," +
                    "station_name VARCHAR(100)," +
                    "location VARCHAR(100)," +
                    "platforms INT," +
                    "opening_year INT)");

            // Create Trains table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Trains (" +
                    "train_id INT PRIMARY KEY," +
                    "train_name VARCHAR(100)," +
                    "capacity INT," +
                    "station_id INT," +
                    "FOREIGN KEY (station_id) REFERENCES Stations(station_id))");

            System.out.println("Tables created!");

            // Insert Stations
            stmt.executeUpdate("INSERT INTO Stations VALUES (1,'Central','City Center',6,2010)");
            stmt.executeUpdate("INSERT INTO Stations VALUES (2,'North','North Zone',4,2012)");
            stmt.executeUpdate("INSERT INTO Stations VALUES (3,'South','South Zone',5,2015)");

            // Insert Trains
            stmt.executeUpdate("INSERT INTO Trains VALUES (101,'Blue Line',800,1)");
            stmt.executeUpdate("INSERT INTO Trains VALUES (102,'Red Line',750,2)");

            // Display Stations
            System.out.println("\nStations:");
            ResultSet rs = stmt.executeQuery("SELECT * FROM Stations");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("station_id") + " " +
                        rs.getString("station_name") + " " +
                        rs.getString("location") + " " +
                        rs.getInt("platforms") + " " +
                        rs.getInt("opening_year")
                );
            }

            // Display Trains
            System.out.println("\nTrains:");
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM Trains");

            while (rs2.next()) {
                System.out.println(
                        rs2.getInt("train_id") + " " +
                        rs2.getString("train_name") + " " +
                        rs2.getInt("capacity") + " " +
                        rs2.getInt("station_id")
                );
            }

            con.close();

            System.out.println("\nEverything executed successfully in Eclipse!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}