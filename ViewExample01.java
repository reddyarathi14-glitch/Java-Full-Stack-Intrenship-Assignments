package sqlDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class ViewExample {

    public static void main(String[] args) {

        try {

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/collegeDB",
                    "root",
                    "Arathi@#123");

            Statement st = con.createStatement();

            // Create View
            String viewQuery = "CREATE OR REPLACE VIEW student_course_view AS "
                    + "SELECT students.name, courses.course_name "
                    + "FROM students "
                    + "JOIN courses "
                    + "ON students.course_id = courses.course_id";

            st.executeUpdate(viewQuery);

            // Fetch Data From View
            ResultSet rs = st.executeQuery("SELECT * FROM student_course_view");

            System.out.println("Name\tCourse");

            while (rs.next()) {
                System.out.println(rs.getString("name") + "\t" +
                        rs.getString("course_name"));
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
