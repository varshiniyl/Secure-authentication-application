package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpDao {
    String driver1 = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/Student63";
    String uname = "root";
    String pass = "abc123";

    private Connection getConnect() throws ClassNotFoundException, SQLException {
        Class.forName(driver1);
        Connection con = DriverManager.getConnection(url, uname, pass);
        return con;
    }

    public int save(Emp e) throws SQLException, ClassNotFoundException {
        try (Connection con = getConnect();
             PreparedStatement ps = con.prepareStatement("INSERT INTO ram (email, upass, upass1) VALUES (?, ?, ?)")) {

            ps.setString(1, e.getEmail());
            ps.setString(2, e.getUpass());
            ps.setString(3, e.getUpass1());

            int a = ps.executeUpdate();
            return a;
        }
    }

    public boolean verifyUser(Emp e) throws ClassNotFoundException, SQLException {
        try (Connection conn = getConnect();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ram WHERE email = ? AND upass = ?")) {

            stmt.setString(1, e.getEmail());
            stmt.setString(2, e.getUpass());

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Check if the result set has a row
            }
        }
    }
}
