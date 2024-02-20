package com.uzinfo.datagenerate.web.configuration.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class ConnectionManager {
    @Autowired
    @Qualifier("appDataSource")
    private static DataSource dataSource;

    public ConnectionManager(DataSource dataSource) {
        ConnectionManager.dataSource = dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // Method to close a connection
    public static void closeConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close(); // This returns the connection to the pool
        }
    }

    // Example method to reopen a connection (get a new one from the pool)
    public static Connection reopenConnection(Connection connection) throws SQLException {
        closeConnection(connection); // Close the existing connection
        return getConnection(); // Get a new connection from the pool
    }
}
