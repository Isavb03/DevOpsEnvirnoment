package project;

import java.sql.*;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionProvider {
	
	
    private static final String DB_URL = "jdbc:mysql://mysql-service:3306/university";
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    private static HikariDataSource dataSource;

    static {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(DB_URL);
            config.setUsername(DB_USER);
            config.setPassword(DB_PASSWORD);
            config.setMaximumPoolSize(10);
            config.setMinimumIdle(2);
            config.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource = new HikariDataSource(config);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    // Original method
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    
    // Add the getCon() method that's used in JSP files
    public static Connection getCon() throws SQLException {
        return getConnection(); // Just call the original method
    }	

}