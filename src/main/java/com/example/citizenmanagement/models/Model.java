package com.example.citizenmanagement.models;

import com.example.citizenmanagement.views.ViewFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseConnection databaseConnection;

    //citizen manager section
    private CitizenManager citizenManager;
    private boolean citizenManagerLoginSuccessFlag;

    private Model() {
        this.viewFactory = new ViewFactory();
        this.databaseConnection = new DatabaseConnection();
        this.citizenManager = new CitizenManager("", "", "", "", -1);
        citizenManagerLoginSuccessFlag = false;
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {return viewFactory;}

    public DatabaseConnection getDatabaseConnection() {return databaseConnection;}


    //Citizen Manager Method
    public void setCitizenManagerLoginSuccessFlag(boolean flag) {
        this.citizenManagerLoginSuccessFlag = flag;
    }
    public boolean getCitizenManagerLoginSuccessFlag() {return citizenManagerLoginSuccessFlag;}
    public CitizenManager getCitizenManager() {return citizenManager;}
    public void verifyManagerAccount(String tenDangNhap, String matKhau) {
        ResultSet resultSet = databaseConnection.getCitizenManagerData(tenDangNhap, matKhau);

        try {
            if(resultSet.isBeforeFirst()) {
                resultSet.next();
                this.citizenManager.setHoTen(resultSet.getString("HOTEN"));
                this.citizenManager.setTenDangNhap(resultSet.getString("TENDANGNHAP"));
                this.citizenManager.setMatKhau(resultSet.getString("MATKHAU"));
                this.citizenManager.setSoDienThoai(resultSet.getString("SODIENTHOAI"));
                this.citizenManager.setVaiTro(resultSet.getInt("VAITRO"));
                this.citizenManagerLoginSuccessFlag = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkManagerUsernameExisted(String tenDangNhap) {
        ResultSet resultSet = databaseConnection.checkCitizenManagerUsernameExisted(tenDangNhap);

        try {
            if (resultSet.isBeforeFirst()){
                return true;
            }
            else return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean checkManagerAccountExisted(String hoTen, String tenDangNhap, String soDienThoai, int vaiTro) {
        ResultSet resultSet = databaseConnection.checkCitizenManagerAccountExisted(hoTen, tenDangNhap, soDienThoai, vaiTro);

        try {
            if (resultSet.isBeforeFirst()) return true;
            else return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateManagerAccountPassword(String hoTen, String tenDangNhap, String soDienThoai, int vaiTro, String matKhau) {
        databaseConnection.updateCitizenManagerAccountPassword(hoTen, tenDangNhap, soDienThoai, vaiTro, matKhau);
    }
    public void registerManagerAccount(String hoTen, String tenDangNhap, String matKhau, String soDienThoai, int vaiTro) {
        databaseConnection.setCitizenManagerData(hoTen, tenDangNhap, matKhau, soDienThoai, vaiTro);
    }

}
