package com.mritunjd.order_management.util;

import java.sql.*;

public class DBConnection {
    private Connection connection = null;
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/order_management?user=jdbc&password=password");
        } catch (Exception e) {
            throw e;
        }
        return connection;
    }
}
