package com.example.citizenmanagement.models;

import java.sql.*;

public class DatabaseConnection {
    public Connection connection;

    public DatabaseConnection() {
        String dbName = "QUANLYDANCU";
        String dbUser = "sa";
        String dbPassword = "123456789";
        String url = "jdbc:sqlserver://LAPTOP-GKSBA9V1\\SQLEXPRESS:1433;databaseName=QUANLYDANCU;encrypt=true;trustServerCertificate=true";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, dbUser, dbPassword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getListNhanKhau() {
        String query = "SELECT * FROM NHANKHAU";

        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }
}
