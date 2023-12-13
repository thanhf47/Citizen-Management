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
        String query = "SELECT * FROM HOKHAU WHERE IDHOKHAU LIKE ? OR IDCHUHO LIKE ? OR DIACHI LIKE ? OR NGAYLAP LIKE ? OR GHICHU LIKE ?";
        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, dieukien + "%");
            statement.setString(2, dieukien + "%");
            statement.setString(3, dieukien + "%");
            statement.setString(4, dieukien + "%");
            statement.setString(5, dieukien + "%");
            resultSet = statement.executeQuery();
        }catch (Exception e){
            System.out.println("timkiem");
        }
        return resultSet;
    }
    public int capNhatHoKhau(String idHoKhau, String maChuHo, String diaChi, String ghiChu){
        ResultSet resultSet=null;
        try {
            String capnhat = "update HOKHAU set IDCHUHO=?, DIACHI=?, GHICHU=? where IDHOKHAU=?";
            PreparedStatement preparedStatement = connection.prepareStatement(capnhat);
            preparedStatement.setString(1,maChuHo);
            preparedStatement.setString(2,diaChi);
            if(ghiChu.isEmpty())
                preparedStatement.setString(3,null);
            else
                preparedStatement.setString(3, ghiChu);
            preparedStatement.setString(4,idHoKhau);
            int ketqua=preparedStatement.executeUpdate();
            if(ketqua!=0)
                return 1;
            else
                return 0;
        } catch (SQLException e) {
            System.out.println("loi o capnhat !!!!!!!!!!!!!");
            return 0;
        }
    }

    public int xoa_ho_khau(String maHoKhau){
        try {
            String xoa = "delete from HOKHAU WHERE IDHOKHAU='" + maHoKhau+"'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(xoa);
            return 1;
        }catch (Exception e){
            System.out.println("loi o xoa ho khau");
            return 0;
        }
    }
}
