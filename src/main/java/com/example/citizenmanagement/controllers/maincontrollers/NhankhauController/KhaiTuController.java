package com.example.citizenmanagement.controllers.maincontrollers.NhankhauController;

import com.example.citizenmanagement.models.List_nhan_khau;
import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class KhaiTuController implements Initializable {
    public Button thoat_khaitu_btn;
    public TextField ma_nguoi_khai;
    public TextField ma_nguoi_mat;
    public TextArea li_do_mat;
    public DatePicker ngay_mat;
//    public DatePicker ngay_khai_tu;
    public Button xac_nhan_but;
    private List_nhan_khau tam;

    private void onthoatKhaitubtn() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.NHAN_KHAU);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        thoat_khaitu_btn.setOnAction(actionEvent -> {
            onthoatKhaitubtn();
        });
        tam = Model.getNhanKhauDuocChon();
        ma_nguoi_mat.setText(tam.getSo_nhan_khau());
        ma_nguoi_mat.setDisable(true);
        xac_nhan_but.setOnAction(ActionEvent -> {
            int thanhcong = Model.getInstance().getDatabaseConnection().addKhaitu(ma_nguoi_khai.getText(),ma_nguoi_mat.getText(), Date.valueOf(ngay_mat.getValue()), li_do_mat.getText());
            if(thanhcong ==1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thành công");
                alert.setContentText("Đã khai tử thành công người này!");
                alert.showAndWait();
                Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.NHAN_KHAU);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Lỗi");
                alert.setContentText("Lỗi rồi, kiểm tra lại khai tử!");
                alert.showAndWait();
                Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.NHAN_KHAU);
            }
        });
    }
}
