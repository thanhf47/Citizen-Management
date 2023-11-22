package com.example.citizenmanagement.controllers.feecontrollers;

import com.example.citizenmanagement.models.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FeeMenuController {

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

}
