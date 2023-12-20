package com.example.citizenmanagement.controllers.maincontrollers.NhankhauController;

import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class KhaiTuController implements Initializable {
    public Button thoat_khaitu_btn;
    public TextField ma_nguoi_khai;
    public TextField ma_nguoi_mat;
    public TextField li_do_mat;
    public DatePicker ngay_mat;
    public DatePicker ngay_khai_tu;

    private void onthoatKhaitubtn() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.NHAN_KHAU);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        thoat_khaitu_btn.setOnAction(actionEvent -> {
            onthoatKhaitubtn();
        });
    }
}
