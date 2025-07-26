import java.sql.*;

public class DBConnection {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/library_db";
        String username = "root";
        String password = "hari@Home1"; 
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    }
}
