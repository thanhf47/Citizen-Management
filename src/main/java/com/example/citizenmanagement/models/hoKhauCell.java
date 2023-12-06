package com.example.citizenmanagement.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class hoKhauCell {
private final StringProperty id;
private final StringProperty owner;
private final StringProperty address;
private final StringProperty date_tao;
private final StringProperty date_chuyen;
private final StringProperty ghi_chu;
public hoKhauCell(String id, String owner, String address, String ngaytao, String ngaychuyen, String ghichu){
    this.id = new SimpleStringProperty(this,"id",id);
    this.owner = new SimpleStringProperty(this,"owner",owner);
    this.address = new SimpleStringProperty(this,"address",address);
    this.date_tao = new SimpleStringProperty(this,"date_tao",ngaytao);
    this.date_chuyen = new SimpleStringProperty(this, "date_chuyen", ngaychuyen);
    this.ghi_chu = new SimpleStringProperty(this,"ghi_chu",ghichu);
}
public StringProperty getId(){
    return this.id;
}
public StringProperty getOwner(){
    return this.owner;
}
public StringProperty getAddress(){
    return this.address;
}
public StringProperty getDate_tao(){
    return this.date_tao;
}
    public StringProperty getDate_chuyen(){
    return this.date_chuyen;
    }
    public StringProperty getGhi_chu(){
    return ghi_chu;
    }
}
