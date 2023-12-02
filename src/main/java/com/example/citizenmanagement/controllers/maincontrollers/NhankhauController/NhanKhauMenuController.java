package com.example.citizenmanagement.controllers.maincontrollers.NhankhauController;

import com.example.citizenmanagement.models.Model;
import com.example.citizenmanagement.models.NhanKhauMenu;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class NhanKhauController implements Initializable {
    public Button themmoi_btn;
    public Button khaitu_button;
    public Button tamtru_button;
    public Button tamvang_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.addListener();
    }

    private void addListener() {
        themmoi_btn.setOnAction(actionEvent -> onThemmoi());
        tamtru_button.setOnAction(actionEvent -> onTamtru());
        tamvang_button.setOnAction(actionEvent -> onTamvang());
        khaitu_button.setOnAction(actionEvent -> onKhaitu());
    }

    private void onThemmoi() {
        Model.getInstance().getNhankhauFactoryView().selectedMenuNhanKhau().set(NhanKhauMenu.Them_Moi);
    }

    private void onTamtru() {
        Model.getInstance().getNhankhauFactoryView().selectedMenuNhanKhau().set(NhanKhauMenu.Tam_Tru);
    }

    private void onTamvang() {
        Model.getInstance().getNhankhauFactoryView().selectedMenuNhanKhau().set(NhanKhauMenu.Tam_Vang);
    }

    private void onKhaitu() {
        Model.getInstance().getNhankhauFactoryView().selectedMenuNhanKhau().set(NhanKhauMenu.Khai_Tu);
    }


}
