package com.example.citizenmanagement.models;

import com.example.citizenmanagement.views.ViewFactory;
import com.example.citizenmanagement.views.ViewFactoryThongKe;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;

    private final DatabaseConnection databaseConnection;
    private final ViewFactoryThongKe viewFactoryThongKe;

    private Model() {
        this.viewFactory = new ViewFactory();
        this.databaseConnection = new DatabaseConnection();
        this.viewFactoryThongKe = new ViewFactoryThongKe();
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {return viewFactory;}
    public ViewFactoryThongKe getViewFactoryThongKe(){
        return viewFactoryThongKe;
    }


    public int getNumberOfNhanKhau() {
        ResultSet resultSet = databaseConnection.getNumberOfNhanhKhau();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()) {
                resultSet.next();
                res =  resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public int getNumberOfHoKhau(){
        ResultSet resultSet = databaseConnection.getNumberOfHoKhau();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public int getNumberOfTamTru(){
        ResultSet resultSet = databaseConnection.getNumberOfTamTru();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public int getNumberOfTamVang(){
        ResultSet resultSet = databaseConnection.getNumberOfTamVang();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public int getNumberOfNhanKhauNam(){
        ResultSet resultSet = databaseConnection.getNumberOfNhanKhauNam();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public int getNumberOfNhanKhauNu(){
        ResultSet resultSet = databaseConnection.getNumberOfNhanKhauNu();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int getNumberOfNhanKhauDuoi3Tuoi(){
        ResultSet resultSet = databaseConnection.getNumberOfNhanKhauDuoi3Tuoi();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public int getNumberOfNhanKhauTu3Den10Tuoi(){
        ResultSet resultSet = databaseConnection.getNumberOfNhanKhauTu3Den10Tuoi();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public int getNumberOfNhanKhauTu10Den18Tuoi(){
        ResultSet resultSet = databaseConnection.getNumberOfNhanKhauTu10Den18Tuoi();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public int getNumberOfNhanKhauTu18Den60Tuoi(){
        ResultSet resultSet = databaseConnection.getNumberOfNhanKhauTu18Den60Tuoi();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int getNumberOfNhanKhauTren60Tuoi(){
        ResultSet resultSet = databaseConnection.getNumberOfNhanKhauTren60Tuoi();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int getNamHienTai(){
        ResultSet resultSet = databaseConnection.getNamHienTai();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public int getHoKhauOfNamHienTai(){
        ResultSet resultSet = databaseConnection.getHoKhauOfNamHienTai();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public int getHoKhauOfNam(int r){
        ResultSet resultSet = databaseConnection.getHoKhauOfNam(r);
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int getTamTruOfThangVaNam(int thang, int nam){
        ResultSet resultSet = databaseConnection.getTamTruOfThangVaNam(thang,nam);
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public int getTamTruViLyDoHocTap(){
        ResultSet resultSet = databaseConnection.getTamTruViLyDoHocTap();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public int getTamTruViLyDoLamViec(){
        ResultSet resultSet = databaseConnection.getTamTruViLyDoLamViec();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int getTamTruViLyDoSucKhoe(){
        ResultSet resultSet = databaseConnection.getTamTruViLyDoSucKhoe();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int getTamTruViLyDoKhac(){
        return  getNumberOfTamTru() - getTamTruViLyDoHocTap() - getTamTruViLyDoSucKhoe() - getTamTruViLyDoLamViec();

    }
    public int getTamVangOfThangVaNam(int thang, int nam){
        ResultSet resultSet = databaseConnection.getTamVangOfThangVaNam(thang, nam);
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int getTamVangViLyDoSucKhoe(){
        ResultSet resultSet = databaseConnection.getTamVangViLyDoSucKhoe();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int getTamVangViLyDoHocTap(){
        ResultSet resultSet = databaseConnection.getTamVangViLyDoHocTap();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int getTamVangViLyDoLamViec(){
        ResultSet resultSet = databaseConnection.getTamVangViLyDoLamViec();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int getTamVangViLyDoKhac(){
        return  getNumberOfTamVang() - getTamVangViLyDoHocTap() - getTamVangViLyDoLamViec() - getTamVangViLyDoSucKhoe();

    }




}
