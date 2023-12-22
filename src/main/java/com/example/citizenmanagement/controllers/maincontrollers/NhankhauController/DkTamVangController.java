package com.example.citizenmanagement.controllers.maincontrollers.NhankhauController;

import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DkTamVangController implements Initializable {

    public Button thoat_btn;
    public TextField giay_tam_vang;
    public TextField ma_nhan_khau;
    public TextField noi_tam_tru;
    public TextField li_do;
    public DatePicker ngay_bat_dau;
    public DatePicker ngay_ket_thuc;

    private void onThoatBtn() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.NHAN_KHAU);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        thoat_btn.setOnAction(event -> {
            onThoatBtn();
        });
    }
}
