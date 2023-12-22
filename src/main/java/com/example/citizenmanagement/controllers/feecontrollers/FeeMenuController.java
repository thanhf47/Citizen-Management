package com.example.citizenmanagement.controllers.feecontrollers;

import com.example.citizenmanagement.models.FeeMenuOptions;
import com.example.citizenmanagement.models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FeeMenuController implements Initializable {

    @FXML
    private HBox dang_xuat_btn;

    @FXML
    private Button danh_sach_phi_btn;

    @FXML
    private Button them_khoan_thu_btn;

    @FXML
    private Button trang_chu_btn;

    @FXML
    public void onLogoutBtn(MouseEvent event) {
        Stage stage = (Stage) dang_xuat_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
        Model.createNewInstance();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListeners();
    }

    private void addListeners() {
        trang_chu_btn.setOnAction(event -> onTrangChu());
        them_khoan_thu_btn.setOnAction(event -> onThemKhoanThu());
        danh_sach_phi_btn.setOnAction(event -> onDanhSachKhoanThu());
    }

    private void onDanhSachKhoanThu() {
        Model.getInstance().getViewFactory().getFeeSelectedMenuItem().set(FeeMenuOptions.DANH_SACH_KHOAN_THU);
    }

    private void onThemKhoanThu() {

        Model.getInstance().getViewFactory().getFeeSelectedMenuItem().set(FeeMenuOptions.THEM_KHOAN_THU_PHI);
    }

    private void onTrangChu() {
        Model.getInstance().getViewFactory().getFeeSelectedMenuItem().set(FeeMenuOptions.TRANG_CHU);
    }

}
