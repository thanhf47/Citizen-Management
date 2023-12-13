package com.example.citizenmanagement.controllers.maincontrollers;

import com.example.citizenmanagement.models.CitizenManager;
import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import com.example.citizenmanagement.models.hoKhauOptions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {


    @FXML
    private Button trang_chu_btn;

    @FXML
    private Button ho_khau_btn;

    @FXML
    private HBox logout_btn;

    @FXML
    private Button nhan_khau_btn;


    @FXML
    public void onLogoutBtn() {
        Stage stage = (Stage) logout_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
        Model.getInstance().setCitizenManagerLoginSuccessFlag(false);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListeners();
    }


    private void addListeners() {
        trang_chu_btn.setOnAction(event -> onTrangChu());
        nhan_khau_btn.setOnAction(event -> onNhanKhau());
        ho_khau_btn.setOnAction(event -> onHoKhau());
    }

    private void onTrangChu() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.TRANG_CHU);
    }
    private void onNhanKhau() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.NHAN_KHAU);
    }
    private void onHoKhau() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.HO_KHAU);
    }

}
