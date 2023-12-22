package com.example.citizenmanagement.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FeeKhoanThuModel {
    // dung de luu tam thoi truoc khi dua vao database
    private StringProperty tenKhoanThu;
    private IntegerProperty batBuoc;

    private IntegerProperty soTienTrenMotNguoi;
    private StringProperty ngayTao;
    private StringProperty moTa;

    public FeeKhoanThuModel() {
        tenKhoanThu = new SimpleStringProperty("");
        batBuoc = new SimpleIntegerProperty(0);
        soTienTrenMotNguoi = new SimpleIntegerProperty(0);
        ngayTao = new SimpleStringProperty(LocalDate.now().toString());
        moTa = new SimpleStringProperty("");
    }

    public void setFeeKhoanThuModel(String tenKhoanThu, int batBuoc, int soTienTrenMotNguoi, String ngayTao, String moTa) {
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

    public IntegerProperty getSoTienTrenMotNguoi() {
        return soTienTrenMotNguoi;
    }

    public void setSoTienTrenMotNguoi(int soTienTrenMotNguoi) {
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

}
