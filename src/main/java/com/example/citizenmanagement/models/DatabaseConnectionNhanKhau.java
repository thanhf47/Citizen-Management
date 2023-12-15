package com.example.citizenmanagement.models;

import java.sql.*;
import java.time.LocalDate;

public class DatabaseConnectionNhanKhau {
    public Connection connection;

    public DatabaseConnectionNhanKhau() {
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

    public int addNhanKhau (String hoTen, String CCCD, String namSinh, int gioiTinh, String noiSinh, String nguyenQuan,String danToc, String tonGiao, String quocTich, String soHoChieu, String noiThuongTru, String ngheNghiep, String ngayTao, String ghiChu ){
        int thanhcong = 0;
        String querry = "insert into NHANKHAU (HOTEN, SOCANCUOC, NAMSINH, GIOITINH, NOISINH, NGUYENQUAN, DANTOC, TONGIAO, QUOCTICH, SOHOCHIEU, NOITHUONGTRU, NGHENGHIEP, NGAYTAO, GHICHU )" +
                " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement pre = connection.prepareStatement(querry);
            pre.setNString(1,hoTen); pre.setString(2,CCCD);
            pre.setString(3,namSinh); pre.setInt(4,gioiTinh);
            pre.setNString(5,noiSinh); pre.setNString(6,nguyenQuan);
            pre.setNString(7,danToc); pre.setNString(8,tonGiao);
            pre.setNString(9,quocTich); pre.setString(10,soHoChieu);
            pre.setNString(11,noiThuongTru); pre.setNString(12,ngheNghiep);
            pre.setDate(13, Date.valueOf(ngayTao)); pre.setNString(14,ghiChu);
            thanhcong = pre.executeUpdate();
        }
        catch(SQLException e) {
            System.out.println("Lỗi thêm nhân khẩu");
            throw new RuntimeException(e);
        }
        return thanhcong;
    }

    public ResultSet truyvan() {
        ResultSet resultSet = null;
        String querry = " select SOCANCUOC, HOTEN, GIOITINH, NAMSINH, NOITHUONGTRU from NHANKHAU;";
        try{
            Statement statement = connection.createStatement();
           resultSet = statement.executeQuery(querry);
        }
        catch(Exception e) {

        }
        return resultSet;
    }

    public ResultSet timkiem(String string) {
        ResultSet resultSet = null;
        String querry = " select SOCANCUOC, HOTEN, GIOITINH, NAMSINH, NOITHUONGTRU from NHANKHAU where SOCANCUOC like ? or HOTEN like ? or GIOITINH like ? or NAMSINH like ? or NOITHUONGTRU like ?;";
        try {
            PreparedStatement preparedstatement = connection.prepareStatement(querry);
            preparedstatement.setString(1, "%" + string + "%");
            preparedstatement.setString(2, "%" + string + "%");
            preparedstatement.setString(3, "%" + string + "%");
            preparedstatement.setString(4, "%" + string + "%");
            preparedstatement.setString(5, "%" + string + "%");
            resultSet = preparedstatement.executeQuery();
        }
        catch(Exception e) {
            System.out.println("Lỗi tìm kiếm");
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public int addTamtru(String hoTen, String CCCD, int namSinh, int gioiTinh, String noiSinh, String nguyenQuan, String danToc, String tonGiao, String quocTich, String soHoChieu, String noiThuongTru, String ngheNghiep, String maTamTru, String sdt, Date ngayDen, Date ngayDi, String liDo ) {
        int thanhcong = 0;
        String que = "ínsert to TAMTRU()" +
                "value (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try{
            PreparedStatement pre = connection.prepareStatement(que);
            pre.setNString(1,hoTen); pre.setString(2,CCCD);
            pre.setInt(3,namSinh); pre.setInt(4,gioiTinh);
            pre.setNString(5,noiSinh); pre.setNString(6,nguyenQuan);
            pre.setNString(7,danToc); pre.setNString(8,tonGiao);
            pre.setNString(9,quocTich); pre.setString(10,soHoChieu);
            pre.setNString(11,noiThuongTru); pre.setNString(12,ngheNghiep);
            pre.setString(13, maTamTru); pre.setString(14, sdt);
            pre.setDate(15, ngayDen); pre.setDate(16,ngayDi);
            pre.setNString(17,liDo);
            thanhcong = pre.executeUpdate();
        }catch(Exception e) {
                    System.out.println("Lỗi thêm nhân khẩu");
                    throw new RuntimeException(e);
        }

        return thanhcong;
    }


}



