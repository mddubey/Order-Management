package com.mritunjd.order_management.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class DBHandler {
    private Connection connection;
    private final Statement statement;

    public DBHandler() throws SQLException, ClassNotFoundException {
        createConnection();
        statement = connection.createStatement();
    }

    public DBHandler(Connection connection) throws SQLException {
        this.connection = connection;
        statement = connection.createStatement();
    }

    private void createConnection() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/order_management?user=jdbc&password=password");
        } catch (Exception e) {
            throw e;
        }
    }

    public ResultSet executeReadQuery(String readQuery) throws SQLException {
        return statement.executeQuery(readQuery);
    }

    public int executeUpdate(String updateQuery) throws SQLException {
        return statement.executeUpdate(updateQuery);
    }
}
