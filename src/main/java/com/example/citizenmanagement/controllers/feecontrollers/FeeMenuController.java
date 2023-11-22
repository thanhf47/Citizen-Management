package com.example.citizenmanagement.controllers.feecontrollers;

import com.example.citizenmanagement.models.FeeMenuOptions;
import com.example.citizenmanagement.models.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FeeMenuController implements Initializable {

    @FXML
    private Button logout_btn;

    @FXML
    private Button phi_dong_gop_btn;

    @FXML
    private Button phi_ve_sinh_btn;

    @FXML
    private Button report_btn;

    @FXML
    private Button trang_chu_btn;

    @FXML
    void onLogoutBtn(ActionEvent event) {
        Stage stage = (Stage) logout_btn.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListeners();
    }

    private void addListeners() {
        trang_chu_btn.setOnAction(event -> onTrangChu());
        phi_ve_sinh_btn.setOnAction(event -> onThuPhiVeSinh());
        phi_dong_gop_btn.setOnAction(event -> onThuPhiDongGop());
    }

    private void onThuPhiDongGop() {
        Model.getInstance().getViewFactory().getFeeSelectedMenuItem().set(FeeMenuOptions.THU_PHI_DONG_GOP);
    }

    private void onThuPhiVeSinh() {
        Model.getInstance().getViewFactory().getFeeSelectedMenuItem().set(FeeMenuOptions.THU_PHI_VE_SINH);
    }

    private void onTrangChu() {
        Model.getInstance().getViewFactory().getFeeSelectedMenuItem().set(FeeMenuOptions.TRANG_CHU);
    }
}
