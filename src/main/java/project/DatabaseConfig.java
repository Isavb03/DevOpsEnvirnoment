package project;

import java.sql.*;

public class DatabaseConfig {
    // Method called in JSP files
    public static Connection getCon() throws SQLException {
        return ConnectionProvider.getCon();
    }
    
    // Keep the original method as well
    public static Connection getConnection() throws SQLException {
        return ConnectionProvider.getCon();
    }
}
