package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Simple MySQL Database Connection class for Docker environment
 * Used to test connection between Java and MySQL containers
 */
public class MySQLDatabaseConnection {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = System.getenv("$JDBC_URL");
    private static final String USER = System.getenv("$USERNAME");
    private static final String PASSWORD = System.getenv("$PASSWORD");

    /**
     * Get MySQL database connection
     * 
     * @return Connection object if successful, null otherwise
     */
    
    public static Connection getConnection() {
        System.out.println("======"+URL+"    "+USER+"    "+PASSWORD);
         DRIVER = "com.mysql.cj.jdbc.Driver";
     URL = System.getenv("$JDBC_URL");
    USER = System.getenv("$USERNAME");
     PASSWORD = System.getenv("$PASSWORD");
        System.out.println("======"+URL+"    "+USER+"    "+PASSWORD);
        Connection conn = null;
        try {
            // Load MySQL JDBC driver
            Class.forName(DRIVER);

            // Establish connection
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✓ Successfully connected to MySQL database!");
            
        } catch (ClassNotFoundException e) {
            System.err.println("✗ MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("✗ Failed to connect to MySQL database!");
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Close database connection
     * 
     * @param conn Connection object to close
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("✓ Connection closed successfully!");
            } catch (SQLException e) {
                System.err.println("✗ Error closing connection!");
                e.printStackTrace();
            }
        }
    }

    /**
     * Test the connection
     */
    public static void main(String[] args) {
        System.out.println("Starting MySQL connection test...");
        Connection conn = getConnection();
        
        if (conn != null) {
            System.out.println("Connection Status: ACTIVE");
            closeConnection(conn);
        } else {
            System.out.println("Connection Status: FAILED");
        }
    }
}
