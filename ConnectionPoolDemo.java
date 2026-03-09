package project1;

import java.util.*;

class Candidate {
    String name, email, password;
    List<String> skills = new ArrayList<>();
    List<Job> appliedJobs = new ArrayList<>();

    Candidate(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}

class Company {
    String name, email, password;
    List<Job> jobs = new ArrayList<>();

    Company(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}

class Job {
    String title;
    Company company;
    boolean isOpen = true;
    List<Candidate> applicants = new ArrayList<>();
    List<Candidate> shortlisted = new ArrayList<>();

    Job(String title, Company company) {
        this.title = title;
        this.company = company;
    }
}

public class JobPortalSystem {

    static Scanner sc = new Scanner(System.in);
    static List<Candidate> candidates = new ArrayList<>();
    static List<Company> companies = new ArrayList<>();
    static List<Job> jobs = new ArrayList<>();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== JOB PORTAL =====");
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
        System.out.println("1. Register\n2. Login");
        int ch = sc.nextInt(); sc.nextLine();

        if (ch == 1) {
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Password: ");
            String pass = sc.nextLine();

            candidates.add(new Candidate(name, email, pass));
            System.out.println("Registered Successfully!");
        }
        else {
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Password: ");
            String pass = sc.nextLine();

            for (Candidate c : candidates) {
                if (c.email.equals(email) && c.password.equals(pass)) {
                    candidateDashboard(c);
                    return;
                }
            }
            System.out.println("Invalid Login!");
        }
    }

    static void candidateDashboard(Candidate c) {
        while (true) {
            System.out.println("\n1.Add Skills\n2.View Jobs\n3.Apply Job\n4.View Applied Jobs\n5.Withdraw\n6.Logout");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1 -> {
                    System.out.print("Enter Skill: ");
                    c.skills.add(sc.nextLine());
                }
                case 2 -> jobs.forEach(j -> {
                    if (j.isOpen)
                        System.out.println(j.title + " - " + j.company.name);
                });
                case 3 -> {
                    System.out.print("Enter Job Title: ");
                    String title = sc.nextLine();
                    for (Job j : jobs) {
                        if (j.title.equalsIgnoreCase(title) && j.isOpen) {
                            j.applicants.add(c);
                            c.appliedJobs.add(j);
                            System.out.println("Applied Successfully!");
                        }
                    }
                }
                case 4 -> c.appliedJobs.forEach(j ->
                        System.out.println(j.title + " - " + j.company.name));
                case 5 -> {
                    System.out.print("Enter Job Title to Withdraw: ");
                    String title = sc.nextLine();
                    c.appliedJobs.removeIf(j -> j.title.equalsIgnoreCase(title));
                    System.out.println("Withdrawn Successfully!");
                }
                case 6 -> { return; }
            }
        }
    }

    // ================= COMPANY =================

    static void companyMenu() {
        System.out.println("1. Register\n2. Login");
        int ch = sc.nextInt(); sc.nextLine();

        if (ch == 1) {
            System.out.print("Company Name: ");
            String name = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Password: ");
            String pass = sc.nextLine();

            companies.add(new Company(name, email, pass));
            System.out.println("Company Registered!");
        }
        else {
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Password: ");
            String pass = sc.nextLine();

            for (Company comp : companies) {
                if (comp.email.equals(email) && comp.password.equals(pass)) {
                    companyDashboard(comp);
                    return;
                }
            }
            System.out.println("Invalid Login!");
        }
    }

    static void companyDashboard(Company comp) {
        while (true) {
            System.out.println("\n1.Post Job\n2.Delete Job\n3.View Applicants\n4.Shortlist\n5.Close Job\n6.Logout");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1 -> {
                    System.out.print("Job Title: ");
                    String title = sc.nextLine();
                    Job j = new Job(title, comp);
                    jobs.add(j);
                    comp.jobs.add(j);
                    System.out.println("Job Posted!");
                }
                case 2 -> {
                    System.out.print("Enter Job Title: ");
                    String title = sc.nextLine();
                    jobs.removeIf(j -> j.title.equalsIgnoreCase(title));
                    System.out.println("Job Deleted!");
                }
                case 3 -> {
                    System.out.print("Enter Job Title: ");
                    String title = sc.nextLine();
                    for (Job j : jobs)
                        if (j.title.equalsIgnoreCase(title))
                            j.applicants.forEach(a -> System.out.println(a.name));
                }
                case 4 -> {
                    System.out.print("Enter Job Title: ");
                    String title = sc.nextLine();
                    for (Job j : jobs)
                        if (j.title.equalsIgnoreCase(title))
                            j.shortlisted.addAll(j.applicants);
                    System.out.println("All Applicants Shortlisted!");
                }
                case 5 -> {
                    System.out.print("Enter Job Title: ");
                    String title = sc.nextLine();
                    for (Job j : jobs)
                        if (j.title.equalsIgnoreCase(title))
                            j.isOpen = false;
                    System.out.println("Job Closed!");
                }
                case 6 -> { return; }
            }
        }
    }

    // ================= ADMIN =================

    static void adminMenu() {
        System.out.println("\n--- ADMIN PANEL ---");
        System.out.println("1.View Candidates\n2.View Companies\n3.Remove Candidate\n4.Job Statistics\n5.Logout");
        int ch = sc.nextInt(); sc.nextLine();

        switch (ch) {
            case 1 -> candidates.forEach(c -> System.out.println(c.name));
            case 2 -> companies.forEach(c -> System.out.println(c.name));
            case 3 -> {
                System.out.print("Enter Candidate Email: ");
                String email = sc.nextLine();
                candidates.removeIf(c -> c.email.equalsIgnoreCase(email));
                System.out.println("Removed!");
            }
            case 4 -> {
                System.out.println("Total Candidates: " + candidates.size());
                System.out.println("Total Companies: " + companies.size());
                System.out.println("Total Jobs: " + jobs.size());
            }
        }
    }
}