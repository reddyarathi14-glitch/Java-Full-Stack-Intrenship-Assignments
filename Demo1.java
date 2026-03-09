package project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Demo {

    static final String URL = "jdbc:mysql://localhost:3306/mydb";
    static final String USER = "root";
    static final String PASSWORD = "Arathi@#123";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to database!");

            while (true) {
                System.out.println("\n1. Insert");
                System.out.println("2. Update");
                System.out.println("3. Delete");
                System.out.println("4. Exit");
                System.out.print("Choose option: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    // INSERT
                    case 1 -> {
                        System.out.print("Enter ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("New name: ");
                        String name = sc.nextLine();

                        String insertQuery = "INSERT INTO student (id, name) VALUES (?, ?)";
                        try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                            insertStmt.setInt(1, id);
                            insertStmt.setString(2, name);

                            int rowsInserted = insertStmt.executeUpdate();
                            if (rowsInserted > 0)
                                System.out.println("Data inserted successfully!");
                        }
                    }
                    // UPDATE
                    case 2 -> {
                        System.out.print("Enter ID to update: ");
                        int updateId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter your name: ");
                        String newName = sc.nextLine();

                        String updateQuery = "UPDATE student SET name = Arathi  WHERE id = 60";
                        try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                            updateStmt.setString(1, newName);
                            updateStmt.setInt(2, updateId);

                            int rowsUpdated = updateStmt.executeUpdate();
                            if (rowsUpdated > 0)
                                System.out.println("Data updated successfully!");
                            else
                                System.out.println("ID not found!");
                        }
                    }
                    // DELETE
                    case 3 -> {
                        System.out.print("Enter ID to delete: ");
                        int deleteId = sc.nextInt();

                        String deleteQuery = "DELETE FROM student WHERE id = ?";
                        try (PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {
                            deleteStmt.setInt(1, deleteId);

                            int rowsDeleted = deleteStmt.executeUpdate();
                            if (rowsDeleted > 0)
                                System.out.println("Data deleted successfully!");
                            else
                                System.out.println("ID not found!");
                        }
                    }
                    case 4 -> {
                        System.out.println("Exiting...");
                        sc.close();
                        break;
                    }
                    default -> System.out.println("Invalid choice!");
                }
            }

        } catch (SQLException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}