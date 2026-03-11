package sqlDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.ResultSet;

public class HospitalStoredProcedure {

    public static void main(String[] args) {

        try {

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/HospitalDB",
                    "root",
                    "Arathi@#123");

            CallableStatement cs = con.prepareCall("{CALL book_appointment(?,?,?)}");

            cs.setInt(1,3);
            cs.setInt(2,1);
            cs.setString(3,"2026-03-15");

            boolean result = cs.execute();

            if(result) {

                ResultSet rs = cs.getResultSet();

                while(rs.next()) {
                    System.out.println(rs.getString(1));
                }
            }

            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
