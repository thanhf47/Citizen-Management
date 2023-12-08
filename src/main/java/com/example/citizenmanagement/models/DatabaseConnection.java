package com.example.citizenmanagement.models;

import java.sql.*;

public class DatabaseConnection {
    private Connection connection;

    public DatabaseConnection() {
        String dbName = "QUANLYDANCU";
        String dbUser = "sa";
        String dbPassword = "040703";
        String url = "jdbc:sqlserver://MAIN-CHARACTER\\THANH_NGUYEN:1433;databaseName=" + dbName + ";integratedSecurity=false;trustServerCertificate=true";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, dbUser, dbPassword);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Citizen Manager Section - Phần Đăng Nhập
    public ResultSet getCitizenManagerData(String tenDangNhap, String matKhau) {

        String query = "SELECT * FROM NGUOIQUANLY\n" +
                "WHERE TENDANGNHAP = '" + tenDangNhap + "' AND MATKHAU = '" + matKhau +"'";
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

    public ResultSet checkCitizenManagerUsernameExisted(String tenDangNhap) {
        String query = "SELECT * FROM NGUOIQUANLY\n" +
                "WHERE TENDANGNHAP = '" + tenDangNhap + "'";
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

    public ResultSet checkCitizenManagerAccountExisted(String hoTen, String tenDangNhap, String soDienThoai, int vaiTro) {
        String query = "SELECT * FROM NGUOIQUANLY\n" +
                "WHERE HOTEN = N'" + hoTen + "' AND TENDANGNHAP = '" + tenDangNhap + "' AND SODIENTHOAI = '" + soDienThoai + "' AND VAITRO = '" + vaiTro + "'";
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
    public void updateCitizenManagerAccountPassword(String hoTen, String tenDangNhap, String soDienThoai, int vaiTro, String maKhau) {
        String query = "UPDATE NGUOIQUANLY SET MATKHAU = '" + maKhau + "' \n" +
                "WHERE HOTEN = N'" + hoTen+ "' AND TENDANGNHAP = '" + tenDangNhap + "' AND SODIENTHOAI = '" + soDienThoai + "' AND VAITRO = '" + vaiTro + "'";
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void setCitizenManagerData(String hoTen, String tenDangNhap, String matKhau, String soDienThoai, int vaiTro) {
        String query = "INSERT INTO NGUOIQUANLY(HOTEN, TENDANGNHAP, MATKHAU, SODIENTHOAI, VAITRO)\n" +
                        "VALUES (N'" + hoTen + "', '" + tenDangNhap + "', '" + matKhau + "', '" + soDienThoai + "', "+ Integer.toString(vaiTro)+ ")";

        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
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

    public ResultSet getTamTruOfThangVaNam(int thang,int nam){
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAGIAYTAMTRU)\n" +
                    "FROM TAMTRU\n" +
                    "WHERE MONTH(TUNGAY) =" + thang +"\n" +
                    "AND YEAR(TUNGAY) =" + nam);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;

    }

    public ResultSet getTamTruViLyDoHocTap(){
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAGIAYTAMTRU)\n" +
                    "FROM TAMTRU\n" +
                    "WHERE LYDO LIKE N'%Học tập%'");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  rs;
    }
    public ResultSet getTamTruViLyDoLamViec(){
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAGIAYTAMTRU)\n" +
                    "FROM TAMTRU\n" +
                    "WHERE LYDO LIKE N'%Làm việc%'");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  rs;
    }

    public ResultSet getTamTruViLyDoSucKhoe(){
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAGIAYTAMTRU)\n" +
                    "FROM TAMTRU\n" +
                    "WHERE LYDO LIKE N'%Chăm sóc sức khỏe%'");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  rs;
    }

    public ResultSet getTamVangOfThangVaNam(int thang,int nam){
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAGIAYTAMVANG)\n" +
                    "FROM TAMVANG\n" +
                    "WHERE MONTH(TUNGAY) =" +thang + "\n" +
                    "AND YEAR(TUNGAY) ="+ nam);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    public ResultSet getTamVangViLyDoHocTap(){
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAGIAYTAMVANG)\n" +
                    "FROM TAMVANG\n" +
                    "WHERE LYDO LIKE N'%Học tập%'");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  rs;
    }

    public ResultSet getTamVangViLyDoLamViec(){
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAGIAYTAMVANG)\n" +
                    "FROM TAMVANG\n" +
                    "WHERE LYDO LIKE N'%Làm việc%'");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  rs;
    }

    public ResultSet getTamVangViLyDoSucKhoe(){
        Statement st;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery("SELECT COUNT(MAGIAYTAMVANG)\n" +
                    "FROM TAMVANG\n" +
                    "WHERE LYDO LIKE N'%sức Khoẻ%'");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  rs;
    }





}


