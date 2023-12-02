package com.example.citizenmanagement.controllers.maincontrollers.NhankhauController;

import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;


public class NhanKhauMenuController implements Initializable {

    @FXML
    public Button themmoi_btn;
    @FXML
    public Button khaitu_button;
    @FXML
    public Button tamtru_button;
    @FXML
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
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.Them_Moi);
    }

    private void onTamtru() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.Tam_Tru);
    }
    private void onTamvang() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.Tam_Vang);
    }
    private void onKhaitu() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.Khai_Tu);
    }


}
