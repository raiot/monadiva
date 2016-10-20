package data;

import java.sql.*;

public class DatabaseConnectorManager {

    private DatabaseConnectorManager() {

    }

    public static Connection getDatabaseConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Postgresql driver class not found.");
        }

        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/monadiva", "postgres", "rened..17");
        } catch (SQLException e) {
            System.out.println("Could not connect to the database.");
        }

        return connection;
    }
}
