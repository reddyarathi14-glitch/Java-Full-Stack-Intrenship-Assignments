package project1;

import java.sql.*;
import java.util.Scanner;

public class JobPortalSystemSS {

    static final String URL = "jdbc:mysql://localhost:3306/jobportal";
    static final String USER = "root";
    static final String PASSWORD = "1234";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n=== JOB PORTAL ===");
            System.out.println("1. Candidate");
            System.out.println("2. Company");
            System.out.println("3. Admin");
            System.out.println("4. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> candidateMenu();
                case 2 -> companyMenu();
                case 3 -> adminMenu();
                case 4 -> System.exit(0);
            }
        }
    }

    // ================= CANDIDATE =================
    static void candidateMenu() {

        System.out.println("\n1. Register\n2. Login");
        int ch = sc.nextInt();
        sc.nextLine();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // REGISTER
            if (ch == 1) {
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Password: ");
                String pass = sc.nextLine();

                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO candidate(name,email,password) VALUES(?,?,?)");

                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, pass);

                ps.executeUpdate();
                System.out.println("✅ Registered Successfully!");
            }

            // LOGIN
            else if (ch == 2) {
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Password: ");
                String pass = sc.nextLine();

                PreparedStatement ps = con.prepareStatement(
                        "SELECT id FROM candidate WHERE email=? AND password=?");

                ps.setString(1, email);
                ps.setString(2, pass);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    int cid = rs.getInt("id");
                    System.out.println("✅ Login Success!");

                    while (true) {
                        System.out.println("\n1.View Jobs\n2.Apply Job\n3.Withdraw Application\n4.Logout");
                        int op = sc.nextInt();

                        // VIEW JOBS
                        if (op == 1) {
                            Statement st = con.createStatement();
                            ResultSet jobs = st.executeQuery("SELECT * FROM job WHERE status='OPEN'");

                            while (jobs.next()) {
                                System.out.println("JobID: " + jobs.getInt("id")
                                        + " Title: " + jobs.getString("title"));
                            }
                        }

                        // APPLY JOB
                        else if (op == 2) {
                            System.out.print("Enter Job ID: ");
                            int jid = sc.nextInt();

                            PreparedStatement apply = con.prepareStatement(
                                    "INSERT INTO application(candidate_id,job_id,status) VALUES(?,?,?)");

                            apply.setInt(1, cid);
                            apply.setInt(2, jid);
                            apply.setString(3, "APPLIED");

                            apply.executeUpdate();
                            System.out.println("✅ Applied Successfully!");
                        }

                        // WITHDRAW
                        else if (op == 3) {
                            System.out.print("Enter Job ID to Withdraw: ");
                            int jid = sc.nextInt();

                            PreparedStatement withdraw = con.prepareStatement(
                                    "DELETE FROM application WHERE candidate_id=? AND job_id=?");

                            withdraw.setInt(1, cid);
                            withdraw.setInt(2, jid);

                            withdraw.executeUpdate();
                            System.out.println("✅ Application Withdrawn!");
                        }

                        else {
                            break;
                        }
                    }
                } else {
                    System.out.println("❌ Invalid Login!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= COMPANY =================
    static void companyMenu() {

        System.out.println("\n1. Register\n2. Login");
        int ch = sc.nextInt();
        sc.nextLine();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // REGISTER
            if (ch == 1) {
                System.out.print("Company Name: ");
                String name = sc.nextLine();
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Password: ");
                String pass = sc.nextLine();

                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO company(name,email,password) VALUES(?,?,?)");

                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, pass);

                ps.executeUpdate();
                System.out.println("✅ Company Registered!");
            }

            // LOGIN
            else if (ch == 2) {
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Password: ");
                String pass = sc.nextLine();

                PreparedStatement ps = con.prepareStatement(
                        "SELECT id FROM company WHERE email=? AND password=?");

                ps.setString(1, email);
                ps.setString(2, pass);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    int compId = rs.getInt("id");
                    System.out.println("✅ Login Success!");

                    System.out.print("Enter Job Title: ");
                    String title = sc.nextLine();

                    PreparedStatement job = con.prepareStatement(
                            "INSERT INTO job(company_id,title,status) VALUES(?,?,?)");

                    job.setInt(1, compId);
                    job.setString(2, title);
                    job.setString(3, "OPEN");

                    job.executeUpdate();
                    System.out.println("✅ Job Posted!");
                } else {
                    System.out.println("❌ Invalid Login!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= ADMIN =================
    static void adminMenu() {

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM candidate");

            System.out.println("\n=== ALL CANDIDATES ===");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("email")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}