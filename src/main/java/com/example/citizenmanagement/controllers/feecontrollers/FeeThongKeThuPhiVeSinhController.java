package com.example.citizenmanagement.controllers.feecontrollers;

import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class FeeThongKeThuPhiVeSinhController implements Initializable {
    public Button thoatThongKeThuPhi_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        thoatThongKeThuPhi_btn.setOnAction(event -> onThoatThongKe());
    }

    public void onThoatThongKe(){
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.TRANG_CHU);
    }
}
