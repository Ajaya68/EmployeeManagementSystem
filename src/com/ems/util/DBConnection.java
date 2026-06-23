package com.ems.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConnection Utility Class
 * Handles Oracle SQL database connection using JDBC.
 * Demonstrates: Abstraction, Singleton-style connection management
 */
public class DBConnection {

    // ─── Oracle DB Configuration ───────────────────────────────────────
    private static final String DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:ORCL";
    private static final String DB_USERNAME = "MOTODB";
    private static final String DB_PASSWORD = "MOTO60"; // Change as needed

    private static Connection connection = null;

    // Private constructor – prevents instantiation
    private DBConnection() {
    }

    /**
     * Returns a singleton JDBC Connection instance.
     */
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // Step 1: Load Oracle JDBC Driver
                Class.forName(DRIVER_CLASS);

                // Step 2: Establish connection
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                System.out.println("[DB] Connection established successfully.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("[ERROR] Oracle JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("[ERROR] Failed to connect to database: " + e.getMessage());
        }
        return connection;
    }

    /**
     * Closes the active database connection.
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("[DB] Connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] Error closing connection: " + e.getMessage());
        }
    }
}
