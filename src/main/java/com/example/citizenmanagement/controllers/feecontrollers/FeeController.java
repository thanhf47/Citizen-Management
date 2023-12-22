package com.example.citizenmanagement.controllers.feecontrollers;

import com.example.citizenmanagement.models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class FeeController implements Initializable {
    @FXML
    private BorderPane fee_parent;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Model.getInstance().getViewFactory().getFeeSelectedMenuItem().addListener((observable, oldValue, newValue) -> {
            fee_parent.requestFocus();
            switch (newValue) {
                case THEM_KHOAN_THU_PHI -> fee_parent.setCenter(Model.getInstance().getViewFactory().getFeeThemKhoanThuView());
                case DANH_SACH_KHOAN_THU -> fee_parent.setCenter(Model.getInstance().getViewFactory().getFeeDanhSachView());
                case DANH_SACH_HO_KHAU_CAN_THU_PHI -> fee_parent.setCenter(Model.getInstance().getViewFactory().getFeeThemHoKhauView());
                default -> fee_parent.setCenter(Model.getInstance().getViewFactory().getFeeTrangChuView());
            }
        });
    }
}
