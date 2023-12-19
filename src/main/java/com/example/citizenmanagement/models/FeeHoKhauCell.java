package com.example.citizenmanagement.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class FeeHoKhauCell {
    private BooleanProperty selected = new SimpleBooleanProperty();
    private int maHoKhau;
    private String tenChuHo;
    private String diaChi;
    private int soThanhVien;
    private Long soTien;

    private int trangThai = 0; //đã đóng phí hay chưa, khởi tạo là chưa.

    public FeeHoKhauCell(boolean selected, int maHoKhau, String tenChuHo, String diaChi, int soThanhVien, long soTien) {
        this.selected.setValue(selected);
        this.maHoKhau = maHoKhau;
        this.tenChuHo = tenChuHo;
        this.diaChi = diaChi;
        this.soThanhVien = soThanhVien;
        this.soTien = soTien;
    }

    public FeeHoKhauCell(int maHoKhau, String tenChuHo, String diaChi, int soThanhVien, long soTien){
        this.maHoKhau = maHoKhau;
        this.tenChuHo = tenChuHo;
        this.diaChi = diaChi;
        this.soThanhVien = soThanhVien;
        this.soTien = soTien;
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

    public long getSoTien() {
        return soTien;
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

    public void setSoTien(long soTien) {
        this.soTien = soTien;
    }

    public void setSelected(boolean selected) {this.selected.setValue(selected);}

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }


}
