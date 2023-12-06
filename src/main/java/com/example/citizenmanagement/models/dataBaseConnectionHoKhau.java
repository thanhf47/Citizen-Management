package com.example.citizenmanagement.models;

import java.sql.*;

public class dataBaseConnectionHoKhau {
    public Connection connection;
    public dataBaseConnectionHoKhau(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://DESKTOP-UK69FVU:1433;databaseName=QLDanCu;encrypt=true;trustServerCertificate=true";
            String user = "sa";
            String pass = "123456789";
            connection = DriverManager.getConnection(url, user, pass);
    }catch (Exception e){
            System.out.println("concac");
        }
    }
    public int addHoKhau(String id, String name_owner, String add){
        int anhhuong=0;
        String query = "insert into HOKHAU values('" + id+"','"+name_owner+"','"+add+"');";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            anhhuong = statement.getUpdateCount();
        }catch (Exception e){
            System.out.println("loi o dataBCHK");
        }
        return anhhuong;
    }
    public ResultSet getResultSet(){
        String query = "select * from HOKHAU";
        ResultSet resultSet=null;
        try {
             resultSet = connection.createStatement().executeQuery(query);
        }catch (Exception e){
            System.out.println("concan");
        }
        return resultSet;
    }
    public ResultSet timKiem(String dieukien){
        ResultSet resultSet=null;
        String query = "select * from HOKHAU where ID = " + dieukien;
        try{
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        }catch (Exception e){
            System.out.println("concac timkiem");
        }
        return resultSet;
    }
    public int capNhatHoKhau(String idHoKhau, String maChuHo, String diaChi, String ngayChuyen, String ghiChu){
        ResultSet resultSet=null;
        int ketqua=0;
        try {
            String capnhat = "update HOKHAU set IDCHUHO=?, DIACHI=?, NGAYCHUYENDI=?, GHICHU=? where IDHOKHAU=?";
            PreparedStatement preparedStatement = connection.prepareStatement(capnhat);
            preparedStatement.setString(1,maChuHo);
            preparedStatement.setString(2,diaChi);
            preparedStatement.setString(3,ngayChuyen);
            preparedStatement.setString(4,ghiChu);
            preparedStatement.setString(5,idHoKhau);
            ketqua = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("loi o capnhat !!!!!!!!!!!!!");
            throw new RuntimeException(e);
        }
        return ketqua;
    }
}
