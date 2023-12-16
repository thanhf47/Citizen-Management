package com.example.citizenmanagement.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class List_nhan_khau {

    private final StringProperty cccd;
    private final StringProperty hoten;
//    private final StringProperty so_ho_khau;
    private final StringProperty gioi_tinh;
    private final StringProperty ngay_sinh;
    private final StringProperty dia_chi;

    public List_nhan_khau(String cccd, String hoten, String gioi_tinh, String ngay_sinh, String dia_chi) {
        this.cccd = new SimpleStringProperty(this, "Cccd", cccd);
        this.hoten = new SimpleStringProperty(this, "HoVaTen", hoten);
        this.gioi_tinh = new SimpleStringProperty(this, "Gioi_tinh", gioi_tinh);
        this.ngay_sinh = new SimpleStringProperty(this, "NgaySinh", ngay_sinh);
        this.dia_chi = new SimpleStringProperty(this, "DiaChi", dia_chi);
    }

    public String getCccd() {
        return cccd.get();
    }

    public StringProperty cccdProperty() {
        return cccd;
    }

    public String getHoten() {
        return hoten.get();
    }

    public StringProperty hotenProperty() {
        return hoten;
    }

//    public String getSo_ho_khau() {
//        return so_ho_khau.get();
//    }
//
//    public StringProperty so_ho_khauProperty() {
//        return so_ho_khau;
//    }

    public String getGioi_tinh() {
        return gioi_tinh.get();
    }

    public StringProperty gioi_tinhProperty() {
        return gioi_tinh;
    }

    public String getNgay_sinh() {
        return ngay_sinh.get();
    }


    public String getDia_chi() {
        return dia_chi.get();
    }

    public StringProperty dia_chiProperty() {
        return dia_chi;
    }
}
