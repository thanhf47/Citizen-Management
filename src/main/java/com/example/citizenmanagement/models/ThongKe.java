package com.example.citizenmanagement.models;

public class ThongKe {
     private String gioitinh;
     private int soluonggioitinh;

    public ThongKe(String gioitinh, int soluong1) {
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
