package com.example.citizenmanagement.controllers.maincontrollers.NhankhauController;

import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
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


    private DkTamTruController dkTamTru1Controller;


    private void onThoattamtru2() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.NHAN_KHAU);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        thoat_btn_2.setOnAction(actionEvent -> {
            onThoattamtru2();
        });

        confirm_btn.setOnAction(actionEvent -> {
         int thanhcong = Model.getInstance().getDatabaseConnection().addTamtru(
                 dkTamTru1Controller.getHoten_text().getText(),
                 dkTamTru1Controller.getCCCD_text().getText(),
                 Integer.parseInt(dkTamTru1Controller.getNam_sinh_text().getText()),
                 dkTamTru1Controller.bits(),
                 dkTamTru1Controller.getNoi_sinh_text().getText(),
                 dkTamTru1Controller.getNguyen_quan_text().getText(),
                 dkTamTru1Controller.getDantoc_text().getText(),
                 dkTamTru1Controller.getTongiao_text().getText(),
                 dkTamTru1Controller.getQuoctich_text().getText(),
                 dkTamTru1Controller.getThuong_tru_text().getText(),
                 dkTamTru1Controller.getNghenghiep_text().getText(),
                 sdt_text.getText(),
                 Date.valueOf(ngayden_text.getValue()),
                 Date.valueOf(ngaydi_text.getValue()),
                 lido_text.getText());
         // int thanhcong = Model.getInstance().getDatabaseConnectionNhanKhau().addTamtru(hoten_text.getText(), CCCD_text.getText(), Integer.parseInt(nam_sinh_text.getText()), dkTamTru1Controller.bits(), noi_sinh_text.getText(),nguyen_quan_text.getText(), dantoc_text.getText(),tongiao_text.getText(), quoctich_text.getText(), hochieu_text.getText(), thuong_tru_text.getText(),nghenghiep_text.getText(),matamtru_text.getText(),sdt_text.getText(), Date.valueOf(ngayden_text.getValue()), Date.valueOf(ngaydi_text.getValue()), lido_text.getText() );
            if(thanhcong == 0) {
                System.out.println("Đã thêm không thành công");
            }
        });

    }


}
