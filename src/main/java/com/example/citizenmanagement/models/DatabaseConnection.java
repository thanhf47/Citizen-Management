package com.example.citizenmanagement.models;

import java.sql.*;

public class DatabaseConnection {
    private final Connection connection;
    public DatabaseConnection() {
        String dbName = "QUANLYDANCU";
        String dbUser = "sa";
        String dbPassword = "123";
        String url = "jdbc:sqlserver://DESKTOP-0686QHH:1433;databaseName=" + dbName + ";IntegratedSecurity=false;encrypt=false;trustSeverCertificate=true;";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, dbUser, dbPassword);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getNumberOfNhanhKhau() {
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) from NHANKHAU");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getNumberOfHoKhau(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MAHOKHAU) from HOKHAU");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getNumberOfTamTru(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(SOCANCUOC) from TAMTRU");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
    public ResultSet getNumberOfTamVang(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) from TAMVANG");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getNumberOfNhanKhauNam(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) from NHANKHAU where GIOITINH = 1");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
    public ResultSet getNumberOfNhanKhauNu(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) from NHANKHAU where GIOITINH = 0");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getNumberOfNhanKhauDuoi3Tuoi(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) \n" +
                    "from NHANKHAU\n" +
                    "where YEAR(GETDATE()) - NAMSINH < 3 AND YEAR(GETDATE()) - NAMSINH >= 0");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
    public ResultSet getNumberOfNhanKhauTu3Den10Tuoi(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) \n" +
                    "from NHANKHAU\n" +
                    "where YEAR(GETDATE()) - NAMSINH >= 3 AND YEAR(GETDATE()) - NAMSINH < 10");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getNumberOfNhanKhauTu10Den18Tuoi(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) \n" +
                    "from NHANKHAU\n" +
                    "where YEAR(GETDATE()) - NAMSINH >= 10 AND YEAR(GETDATE()) - NAMSINH < 18");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getNumberOfNhanKhauTu18Den60Tuoi(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) \n" +
                    "from NHANKHAU\n" +
                    "where YEAR(GETDATE()) - NAMSINH >= 18 AND YEAR(GETDATE()) - NAMSINH < 60");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
    public ResultSet getNumberOfNhanKhauTren60Tuoi(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select count(MANHANKHAU) \n" +
                    "from NHANKHAU\n" +
                    "where YEAR(GETDATE()) - NAMSINH >= 60");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
    public ResultSet getNamHienTai(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("select YEAR(GETDATE())");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }
    public ResultSet getHoKhauOfNamHienTai(){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAHOKHAU)\n" +
                    "FROM HOKHAU");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getHoKhauOfNam(int r){
        Statement st;
        ResultSet rs = null;
        try {
            st= connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAHOKHAU)\n" +
                    "FROM HOKHAU\n" +
                    "WHERE YEAR(NGAYTAO) =" + r);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }


}


