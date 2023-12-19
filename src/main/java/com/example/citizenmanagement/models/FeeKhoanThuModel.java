package com.example.citizenmanagement.models;

import javafx.beans.property.*;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FeeKhoanThuModel{
    // lưu tạm thời khoản thu phí
    private IntegerProperty maKhoanThu;
    private StringProperty tenKhoanThu;
    private IntegerProperty batBuoc;

    private LongProperty soTienTrenMotNguoi;
    private StringProperty ngayTao;
    private StringProperty moTa;

    public FeeKhoanThuModel() {
        maKhoanThu = new SimpleIntegerProperty(-1);
        tenKhoanThu = new SimpleStringProperty("");
        batBuoc = new SimpleIntegerProperty(0);
        soTienTrenMotNguoi = new SimpleLongProperty(0);
        ngayTao = new SimpleStringProperty(LocalDate.now().toString());
        moTa = new SimpleStringProperty("");

        maKhoanThu.addListener((observable, oldValue, newValue) -> {
            changeData(newValue.intValue());
        });
    }

    private void changeData(int maHK) {
        ResultSet resultSet = Model.getInstance().getDatabaseConnection().getKhoanThuPhi(maHK);
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();

                tenKhoanThu.set(resultSet.getNString(2));
                batBuoc.set(resultSet.getInt(3));
                soTienTrenMotNguoi.set(resultSet.getLong(4));
                ngayTao.set(resultSet.getString(5));
                moTa.set(resultSet.getNString(6));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFeeKhoanThuModel(String tenKhoanThu, int batBuoc, long soTienTrenMotNguoi, String ngayTao, String moTa) {
        this.tenKhoanThu.setValue(tenKhoanThu);
        this.batBuoc.setValue(batBuoc);
        this.soTienTrenMotNguoi.setValue(soTienTrenMotNguoi);
        this. ngayTao.setValue(ngayTao);
        this.moTa.setValue(moTa);
    }

    public StringProperty getTenKhoanThu() {
        return tenKhoanThu;
    }

    public void setTenKhoanThu(String tenKhoanThu) {
        this.tenKhoanThu.setValue(tenKhoanThu);
    }

    public IntegerProperty getBatBuoc() {
        return batBuoc;
    }

    public void setBatBuoc(int batBuoc) {
        this.batBuoc.setValue(batBuoc);
    }

    public LongProperty getSoTienTrenMotNguoi() {
        return soTienTrenMotNguoi;
    }

    public void setSoTienTrenMotNguoi(long soTienTrenMotNguoi) {
        this.soTienTrenMotNguoi.setValue(soTienTrenMotNguoi);
    }

    public StringProperty getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao.setValue(ngayTao);
    }

    public StringProperty getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa.setValue(moTa);
    }

    public IntegerProperty getMaKhoanThu() {return maKhoanThu;}
    public void setMaKhoanThu(int maKhoanThu) {this.maKhoanThu.set(maKhoanThu);}
}
