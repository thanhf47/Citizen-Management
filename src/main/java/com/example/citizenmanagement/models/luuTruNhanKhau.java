package com.example.citizenmanagement.models;

import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class luuTruNhanKhau  implements Initializable {
    private final String CCCD;
    private final String ho_ten;

    private final LocalDate nam_sinh;
    private final String gioi_tinh;
    private final String ton_giao;


    private final String dan_toc;
    private final String nghe_nghiep;
    private final String quoc_tich;
    private final String nguyen_quan;
    private final String thuong_tru;
    private final String noi_sinh;




    public luuTruNhanKhau(String ho_ten, String CCCD, LocalDate nam_sinh, String gioi_tinh, String noi_sinh, String nguyen_quan, String dan_toc, String ton_giao, String quoc_tich, String thuong_tru, String nghe_nghiep) {
        this.CCCD = CCCD;
        this.ho_ten = ho_ten;

        this.nam_sinh = nam_sinh;
        this.gioi_tinh = gioi_tinh;
        this.ton_giao = ton_giao;
        this.dan_toc = dan_toc;
        this.nghe_nghiep = nghe_nghiep;
        this.quoc_tich = quoc_tich;
        this.nguyen_quan = nguyen_quan;
        this.thuong_tru = thuong_tru;
        this.noi_sinh = noi_sinh;
    }
    public String getCCCD() {
        return CCCD;
    }

    public String getHo_ten() {
        return ho_ten;
    }


    public LocalDate getNam_sinh() {
        return nam_sinh;
    }



    public String getGioi_tinh() {
        return gioi_tinh;
    }

    public String getTon_giao() {
        return ton_giao;
    }

    public String getDan_toc() {
        return dan_toc;
    }

    public String getNghe_nghiep() {
        return nghe_nghiep;
    }

    public String getQuoc_tich() {
        return quoc_tich;
    }

    public String getNguyen_quan() {
        return nguyen_quan;
    }

    public String getThuong_tru() {
        return thuong_tru;
    }

    public String getNoi_sinh() {
        return noi_sinh;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
