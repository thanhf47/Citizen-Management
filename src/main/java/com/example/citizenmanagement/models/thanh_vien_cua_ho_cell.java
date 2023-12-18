package com.example.citizenmanagement.models;


import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class thanh_vien_cua_ho_cell {
    private String maNhanKhau;
    private final StringProperty cccd;
    private final StringProperty ho_ten;
    private final StringProperty quan_he;
    private final StringProperty ngay_sinh;
    private final StringProperty gioi_tinh;
    private Boolean check_box;

    public thanh_vien_cua_ho_cell(String cancuoc, String hoten, String quanhe, String ngaysinh, String gioitinh){
        maNhanKhau = "";
        cccd = new SimpleStringProperty(this,"id",cancuoc);
        ho_ten = new SimpleStringProperty(this,"owner",hoten);
        quan_he = new SimpleStringProperty(this,"address",quanhe);
        ngay_sinh = new SimpleStringProperty(this,"date_tao",ngaysinh);
        gioi_tinh = new SimpleStringProperty(this,"ghi_chu",gioitinh);
        check_box=false;
    }

    public thanh_vien_cua_ho_cell(String maNhanKhau, String cancuoc, String hoten, String quanhe, String ngaysinh, String gioitinh){
        this.maNhanKhau = maNhanKhau;
        cccd = new SimpleStringProperty(this,"id",cancuoc);
        ho_ten = new SimpleStringProperty(this,"owner",hoten);
        quan_he = new SimpleStringProperty(this,"address",quanhe);
        ngay_sinh = new SimpleStringProperty(this,"date_tao",ngaysinh);
        gioi_tinh = new SimpleStringProperty(this,"ghi_chu",gioitinh);
        check_box=false;
    }
    public Boolean getCheck_box() {
        return check_box;
    }

    public void setCheck_box(Boolean check_box) {
        this.check_box = check_box;
    }

    public String getCccd() {
        return cccd.get();
    }

    public StringProperty cccdProperty() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd.set(cccd);
    }

    public String getHo_ten() {
        return ho_ten.get();
    }

    public StringProperty ho_tenProperty() {
        return ho_ten;
    }

    public void setHo_ten(String ho_ten) {
        this.ho_ten.set(ho_ten);
    }

    public String getQuan_he() {
        return quan_he.get();
    }

    public StringProperty quan_heProperty() {
        return quan_he;
    }

    public void setQuan_he(String quan_he) {
        this.quan_he.set(quan_he);
    }

    public String getNgay_sinh() {
        return ngay_sinh.get();
    }

    public StringProperty ngay_sinhProperty() {
        return ngay_sinh;
    }

    public void setNgay_sinh(String ngay_sinh) {
        this.ngay_sinh.set(ngay_sinh);
    }

    public String getGioi_tinh() {
        return gioi_tinh.get();
    }

    public StringProperty gioi_tinhProperty() {
        return gioi_tinh;
    }

    public void setGioi_tinh(String gioi_tinh) {
        this.gioi_tinh.set(gioi_tinh);
    }

    public String getmaNhanKhau() {
        return maNhanKhau;
    }
}
