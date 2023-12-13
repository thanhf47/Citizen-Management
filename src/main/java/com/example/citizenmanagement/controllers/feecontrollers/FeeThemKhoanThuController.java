package com.example.citizenmanagement.controllers.feecontrollers;

import com.example.citizenmanagement.models.FeeMenuOptions;
import com.example.citizenmanagement.models.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FeeThemKhoanThuController {

    @FXML
    private Button fee_create_tiep_btn;

    @FXML
    private void onFeeCreateTiepBtn() {
        Model.getInstance().getViewFactory().getFeeSelectedMenuItem().set(FeeMenuOptions.DANH_SACH_HO_KHAU_CAN_THU_PHI);
    }

}
