package com.example.citizenmanagement.controllers.maincontrollers.NhankhauController;

import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class KhaiTuController implements Initializable {
    public Button thoat_khaitu_btn;

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
