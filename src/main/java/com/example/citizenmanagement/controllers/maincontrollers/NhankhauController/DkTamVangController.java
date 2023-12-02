package com.example.citizenmanagement.controllers.maincontrollers.NhankhauController;

import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class DkTamVangController implements Initializable {
    public Button thoat_btn;

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
