package com.example.citizenmanagement.controllers.feecontrollers;

import com.example.citizenmanagement.models.FeeMenuOptions;
import com.example.citizenmanagement.models.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FeeThemHoKhauController {

    @FXML
    private Button hoanThanhBtn;

    @FXML
    private Button quayLaiBtn;

    @FXML
    private void onQuayLaiBtn() {
        Model.getInstance().getViewFactory().getFeeSelectedMenuItem().set(FeeMenuOptions.THEM_KHOAN_THU_PHI);
    }

}
