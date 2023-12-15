package com.example.citizenmanagement.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class FeeHoKhauCell {
    private BooleanProperty selected = new SimpleBooleanProperty();
    private int maHoKhau;
    private String tenChuHo;
    private String diaChi;
    private int soThanhVien;
    private int soTienCanDong;

    public FeeHoKhauCell(boolean selected, int maHoKhau, String tenChuHo, String diaChi, int soThanhVien, int soTienCanDong) {
        this.selected.setValue(selected);
        this.maHoKhau = maHoKhau;
        this.tenChuHo = tenChuHo;
        this.diaChi = diaChi;
        this.soThanhVien = soThanhVien;
        this.soTienCanDong = soTienCanDong;
    }

    public int getMaHoKhau() {
        return maHoKhau;
    }

    public String getTenChuHo() {
        return tenChuHo;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public int getSoThanhVien() {
        return soThanhVien;
    }

    public int getSoTienCanDong() {
        return soTienCanDong;
    }

    public boolean getSelected() { return selected.getValue();}

    public BooleanProperty getPropertySelected() {return selected;}

    public void setMaHoKhau(int maHoKhau) {
        this.maHoKhau = maHoKhau;
    }

    public void setTenChuHo(String tenChuHo) {
        this.tenChuHo = tenChuHo;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void setSoThanhVien(int soThanhVien) {
        this.soThanhVien = soThanhVien;
    }

    public void setSoTienCanDong(int soTienCanDong) {
        this.soTienCanDong = soTienCanDong;
    }

    public void setSelected(boolean selected) {this.selected.setValue(selected);}
}
