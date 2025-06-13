package project;

import java.sql.*;
// import com.zaxxer.hikari.HikariConfig;
// import com.zaxxer.hikari.HikariDataSource;

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
