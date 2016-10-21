package data;

import org.apache.log4j.Logger;

import java.sql.*;

public class DatabaseConnectorManager {

    final static Logger logger = Logger.getLogger(DatabaseConnectorManager.class);

    private DatabaseConnectorManager() {

    }

    public static Connection getDatabaseConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("Postgresql driver class not found.");
        }

        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/monadiva", "postgres", "rened..17");
        } catch (SQLException e) {
            logger.error("Could not connect to the database.");
        }

        return connection;
    }
}
