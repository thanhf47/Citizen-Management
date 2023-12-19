package com.example.citizenmanagement.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MainTamVangCell {

    private final StringProperty maGiayTamVang;
    private final StringProperty hoVaTen;
    private final StringProperty maNhanKhau;
    private final StringProperty noiTamTru;
    private final StringProperty tuNgay;
    private final StringProperty denNgay;

    private  final StringProperty soCCCD ;
    private final StringProperty ngaySinh;
    private final StringProperty lyDo;



    public MainTamVangCell( String hovaten,String magiaytamvang,String manhankhau,String noitamtru,String tungay,String denngay,String soccd,String ngaysinh,String lido){

        this.hoVaTen = new SimpleStringProperty(this,"hoVaTen",hovaten);
        this.maGiayTamVang = new SimpleStringProperty(this,"maGiayTamVang",magiaytamvang);
        this.maNhanKhau = new SimpleStringProperty(this,"maNhanKhau",manhankhau);
        this.noiTamTru = new SimpleStringProperty(this,"noiTamTru",noitamtru);
        this.tuNgay = new SimpleStringProperty(this,"tuNgay",tungay);
        this.denNgay = new SimpleStringProperty(this,"denNgay",denngay);
        this.soCCCD = new SimpleStringProperty(this,"soCCCD",soccd);
        this.ngaySinh = new SimpleStringProperty(this,"ngaySinh",ngaysinh);
        this.lyDo = new SimpleStringProperty(this,"lyDo",lido);


    }
    public StringProperty getHoVaTen(){
        return this.hoVaTen;
    }

    public StringProperty getMaGiayTamVang() {
        return this.maGiayTamVang;
    }

    public StringProperty getMaNhanKhau() {
        return this.maNhanKhau;
    }

    public StringProperty getNoiTamTru() {
        return this.noiTamTru;
    }

    public StringProperty getTuNgay() {
        return this.tuNgay;
    }

    public StringProperty getDenNgay() {
        return this.denNgay;
    }

    public StringProperty getSoCCCD(){
        return this.soCCCD;
    }
    public StringProperty getLyDo(){
        return  this.lyDo;
    }
    public StringProperty getNgaySinh(){
        return this.ngaySinh;
    }



}
