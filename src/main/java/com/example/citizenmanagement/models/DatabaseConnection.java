package com.example.citizenmanagement.models;

import java.sql.*;

public class DatabaseConnection {
    public Connection connection;
    public DatabaseConnection(){
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
    public int addHoKhau(String ma_ch, String ngaythem, String diachi, String ghichu){
        if(!ma_ch.isEmpty() && !diachi.isEmpty() && !ngaythem.isEmpty()) {
            String query = "insert into HOKHAU (IDCHUHO, NGAYLAP, DIACHI, GHICHU) VALUES (?, ?, ?, ?)";
            try {
                PreparedStatement statement = connection.prepareStatement(query);

                statement.setString(1, ma_ch);
                statement.setString(2, ngaythem);
                statement.setString(3, diachi);
                if(ghichu.isEmpty())
                    statement.setString(4,null);
                else
                    statement.setString(4, ghichu);

                statement.executeUpdate();
                return 1;
            } catch (Exception e) {
                return 0;
            }
        }
        else
            return 0;
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
        String query = "SELECT * FROM HOKHAU WHERE IDHOKHAU LIKE ? OR IDCHUHO LIKE ? OR DIACHI LIKE ? OR NGAYLAP LIKE ? OR NGAYCHUYENDI LIKE ? OR GHICHU LIKE ?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, dieukien + "%");
            statement.setString(2, dieukien + "%");
            statement.setString(3, dieukien + "%");
            statement.setString(4, dieukien + "%");
            statement.setString(5, dieukien + "%");
            statement.setString(6, dieukien + "%");
            resultSet = statement.executeQuery();
        }catch (Exception e){
            System.out.println("timkiem");
        }
        return resultSet;
    }
    public int capNhatHoKhau(String idHoKhau, String maChuHo, String diaChi, String ngayChuyen, String ghiChu){
        ResultSet resultSet=null;
        try {
            String capnhat = "update HOKHAU set IDCHUHO=?, DIACHI=?, NGAYCHUYENDI=?, GHICHU=? where IDHOKHAU=?";
            PreparedStatement preparedStatement = connection.prepareStatement(capnhat);
            preparedStatement.setString(1,maChuHo);
            preparedStatement.setString(2,diaChi);
            if(ngayChuyen.isEmpty() || ngayChuyen.equals("null")){
                preparedStatement.setString(3,null);
            }
            else
                preparedStatement.setString(3, ngayChuyen);
            if(ghiChu.isEmpty())
                preparedStatement.setString(4,null);
            else
                preparedStatement.setString(4, ghiChu);
            preparedStatement.setString(5,idHoKhau);
            preparedStatement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            System.out.println("loi o capnhat !!!!!!!!!!!!!");
            return 0;
        }
    }
}
