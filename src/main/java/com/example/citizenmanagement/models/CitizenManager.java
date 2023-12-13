package com.example.citizenmanagement.models;

public class CitizenManager {
    private String hoTen;
    private String tenDangNhap;
    private String matKhau;
    private String soDienThoai;
    private int vaiTro;

    public CitizenManager(String hoTen, String tenDangNhap, String matKhau, String soDienThoai, int vaiTro) {
        this.hoTen = hoTen;
        this.tenDangNhap = tenDangNhap;
        this.soDienThoai = soDienThoai;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
    }

    public void setHoTen(String hoTen) {this.hoTen = hoTen;}

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public void setVaiTro(int vaiTro) {
        this.vaiTro = vaiTro;
    }


    public String getHoTen() {return hoTen;}

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public int getVaiTro() {
        return vaiTro;
    }
}
