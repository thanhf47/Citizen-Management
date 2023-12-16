package com.example.citizenmanagement.models;

import java.sql.*;

public class DatabaseConnection {
    public Connection connection;

    public DatabaseConnection() {
        String dbName = "QUANLYDANCU";
        String dbUser = "sa";
        String dbPassword = "040703";
        String url = "jdbc:sqlserver://MAIN-CHARACTER\\THANH_NGUYEN:1433;databaseName=" + dbName +
                ";encrypt=true;integratedSecurity=false;trustServerCertificate=true";

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
