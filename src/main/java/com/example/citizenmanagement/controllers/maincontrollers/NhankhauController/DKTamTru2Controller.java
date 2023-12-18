package com.example.citizenmanagement.controllers.maincontrollers.NhankhauController;

import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import com.example.citizenmanagement.models.luuTruNhanKhau;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DKTamTru2Controller implements Initializable {
    public Button thoat_btn_2;
    public Button confirm_btn;

    public TextField matamtru_text;
    public TextField sdt_text;
    public DatePicker ngayden_text;
    public DatePicker ngaydi_text;
    public TextField lido_text;

    public TextField CCCD_text;
    public TextField hoten_text;
    public ChoiceBox<String> gioitinh_text;
    public TextField nam_sinh_text ;
    public TextField noi_sinh_text;
    public TextField nguyen_quan_text;
    public TextField thuong_tru_text;
    public TextField dantoc_text;
    public TextField tongiao_text;
    public TextField quoctich_text;
    public TextField hochieu_text;
    public TextField nghenghiep_text;

    int bit;

    private static luuTruNhanKhau luuTruNhanKhau;


    private void onThoattamtru2() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.NHAN_KHAU);
    }

    public static void setFormData(luuTruNhanKhau tam) {
        luuTruNhanKhau = tam;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DKTamTru2Controller tamTru2Controller = new DKTamTru2Controller();

        thoat_btn_2.setOnAction(actionEvent -> {
            onThoattamtru2();
        });

        confirm_btn.setOnAction(actionEvent -> {
            if(luuTruNhanKhau.getGioi_tinh() == "Nam") {
                 bit = 1;
            }
            else bit = 0;
         int thanhcong = Model.getInstance().getDatabaseConnection().addTamtru(luuTruNhanKhau.getHo_ten(), luuTruNhanKhau.getCCCD(),luuTruNhanKhau.getNam_sinh(), bit, luuTruNhanKhau.getNoi_sinh(),luuTruNhanKhau.getNguyen_quan(), luuTruNhanKhau.getDan_toc(),luuTruNhanKhau.getTon_giao(),luuTruNhanKhau.getQuoc_tich(), luuTruNhanKhau.getSo_ho_chieu(),luuTruNhanKhau.getThuong_tru(),luuTruNhanKhau.getNghe_nghiep(),matamtru_text.getText(),sdt_text.getText(),Date.valueOf(ngayden_text.getValue()),Date.valueOf(ngaydi_text.getValue()),lido_text.getText() );
         // int thanhcong = Model.getInstance().getDatabaseConnectionNhanKhau().addTamtru(hoten_text.getText(), CCCD_text.getText(), Integer.parseInt(nam_sinh_text.getText()), dkTamTru1Controller.bits(), noi_sinh_text.getText(),nguyen_quan_text.getText(), dantoc_text.getText(),tongiao_text.getText(), quoctich_text.getText(), hochieu_text.getText(), thuong_tru_text.getText(),nghenghiep_text.getText(),matamtru_text.getText(),sdt_text.getText(), Date.valueOf(ngayden_text.getValue()), Date.valueOf(ngaydi_text.getValue()), lido_text.getText() );
            if(thanhcong == 0) {
                System.out.println("Đã thêm không thành công");
            }
        });

    }

    private void setDkTamTru1Controller(DkTamTruController dkTamTru1Controller) {
    }


}
