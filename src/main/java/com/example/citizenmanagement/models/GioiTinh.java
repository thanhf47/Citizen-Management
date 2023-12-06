package com.example.citizenmanagement.models;

public class GioiTinh {
     private String gioitinh;
     private int soluonggioitinh;

    public GioiTinh(String gioitinh, int soluong1) {
        this.gioitinh = gioitinh;
        this.soluonggioitinh = soluong1;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public int getSoluonggioitinh() {
        return soluonggioitinh;
    }
}
