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
            switch (newValue) {
                case THU_PHI_VE_SINH -> fee_parent.setCenter(Model.getInstance().getViewFactory().getFeeVeSinhView());
                case THU_PHI_DONG_GOP -> fee_parent.setCenter(Model.getInstance().getViewFactory().getFeeDongGopView());
                default -> fee_parent.setCenter(Model.getInstance().getViewFactory().getFeeTrangChuView());
            }
        });
    }
}
