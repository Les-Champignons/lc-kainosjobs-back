package org.kainos.ea.dao;

import org.kainos.ea.controllers.JobRoleController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public final class DatabaseConnector {

    private static Connection conn;
    private static final Logger LOGGER = Logger.getLogger(
            DatabaseConnector.class.getName());

    private DatabaseConnector() { }
    public static Connection getConnection() throws SQLException {

        if (conn != null && !conn.isClosed()) {
            return conn;
        }

        try {
            String username = System.getenv().get("DB_USERNAME");
            String password = System.getenv().get("DB_PASSWORD");
            String host = System.getenv().get("DB_HOST");
            String name = System.getenv().get("DB_NAME");

            if (username == null || password == null || host == null
                    || name == null) {
                throw new IllegalArgumentException(
                        "Add the following properties to env vars: "
                        + "DB_USERNAME, DB_PASSWORD, DB_HOST and DB_NAME");
            }
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + host + "/" + name, username, password);
            LOGGER.info("Database successfully connected");
            return conn;

        } catch (Exception e) {
            LOGGER.severe("SEVERE: Database connection error: " + e.getMessage());
            System.err.println(e.getMessage());
        }

        return null;
    }
}
