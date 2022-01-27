package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnection {

    public static Connection getConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;Database=jdbc;user=sa;password=123456;encrypt = true;trustServerCertificate=true;";
            Connection con = DriverManager.getConnection(url);
            return con;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("SqlServerConnection getConnection" + e);
            return null;
        }
    }

    public static Connection getConnection(String port, String name, String username, String password) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://localhost:" + port + ";Database=" + name + ";user=" + username + ";password=" + password + ";";
            Connection con = DriverManager.getConnection(URL);
            return con;
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
