package sqlDemo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SubqueryExample01 {

    public static void main(String[] args) {

        try {

            // Database Connection
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/CompanyDB",
                    "root",
                    "Arathi@#123");

            Statement st = con.createStatement();

            // ==============================
            // Requirement 1 - Single Row Subquery
            // ==============================
            System.out.println("Employees with salary greater than average salary:");

            ResultSet rs1 = st.executeQuery(
                    "SELECT name, salary FROM Employees WHERE salary > (SELECT AVG(salary) FROM Employees)");

            while (rs1.next()) {
                System.out.println(rs1.getString("name") + "  " + rs1.getInt("salary"));
            }

            // ==============================
            // Requirement 2 - Multiple Row Subquery
            // ==============================
            System.out.println("\nEmployees working in IT or Finance:");

            ResultSet rs2 = st.executeQuery(
                    "SELECT name FROM Employees WHERE dept_id IN (SELECT dept_id FROM Departments WHERE dept_name IN ('IT','Finance'))");

            while (rs2.next()) {
                System.out.println(rs2.getString("name"));
            }

            // ==============================
            // Requirement 3 - Correlated Subquery
            // ==============================
            System.out.println("\nEmployees with salary greater than department average:");

            ResultSet rs3 = st.executeQuery(
                    "SELECT name, salary, dept_id FROM Employees e WHERE salary > (SELECT AVG(salary) FROM Employees WHERE dept_id = e.dept_id)");

            while (rs3.next()) {
                System.out.println(rs3.getString("name") + "  "
                        + rs3.getInt("salary") + "  Dept:" + rs3.getInt("dept_id"));
            }

            // ==============================
            // Requirement 4 - Nested Subquery
            // ==============================
            System.out.println("\nEmployees working on IT department projects:");

            ResultSet rs4 = st.executeQuery(
                    "SELECT name FROM Employees WHERE emp_id IN (SELECT emp_id FROM Projects WHERE emp_id IN (SELECT emp_id FROM Employees WHERE dept_id = (SELECT dept_id FROM Departments WHERE dept_name='IT')))");

            while (rs4.next()) {
                System.out.println(rs4.getString("name"));
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
