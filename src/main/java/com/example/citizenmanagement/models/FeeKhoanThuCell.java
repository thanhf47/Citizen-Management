package com.example.citizenmanagement.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FeeKhoanThuCell {
    private final IntegerProperty maKhoanThu = new SimpleIntegerProperty();
    private final StringProperty tenKhoanThu = new SimpleStringProperty();
    private final IntegerProperty batBuoc = new SimpleIntegerProperty();
    private final IntegerProperty soTienCanDong = new SimpleIntegerProperty();
    private final StringProperty ngayTao = new SimpleStringProperty();

    public FeeKhoanThuCell(int maKhoanThu, String tenKhoanThu, int batBuoc, int soTienCanDong, String ngayTao) {
        this.maKhoanThu.setValue(maKhoanThu);
        this.tenKhoanThu.setValue(tenKhoanThu);
        this.batBuoc.setValue(batBuoc);
        this.soTienCanDong.setValue(soTienCanDong);
        this.ngayTao.setValue(ngayTao);
    }

    public int getMaKhoanThu() {
        return maKhoanThu.get();
    }

    public IntegerProperty maKhoanThuProperty() {
        return maKhoanThu;
    }

    public void setMaKhoanThu(int maKhoanThu) {
        this.maKhoanThu.set(maKhoanThu);
    }

    public String getTenKhoanThu() {
        return tenKhoanThu.get();
    }

    public StringProperty tenKhoanThuProperty() {
        return tenKhoanThu;
    }

    public void setTenKhoanThu(String tenKhoanThu) {
        this.tenKhoanThu.set(tenKhoanThu);
    }

    public int getBatBuoc() {
        return batBuoc.get();
    }

    public IntegerProperty batBuocProperty() {
        return batBuoc;
    }

    public void setBatBuoc(int batBuoc) {
        this.batBuoc.set(batBuoc);
    }

    public int getSoTienCanDong() {
        return soTienCanDong.get();
    }

    public IntegerProperty soTienCanDongProperty() {
        return soTienCanDong;
    }

    public void setSoTienCanDong(int soTienCanDong) {
        this.soTienCanDong.set(soTienCanDong);
    }

    public String getNgayTao() {
        return ngayTao.get();
    }

    public StringProperty ngayTaoProperty() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao.set(ngayTao);
    }
}
