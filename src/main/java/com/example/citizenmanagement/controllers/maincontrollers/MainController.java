package com.example.citizenmanagement.controllers.maincontrollers;

import com.example.citizenmanagement.models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    public BorderPane main_parent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Model.getInstance().getViewFactory().getSelectedMenuItem().addListener((observable, oldValue, newValue) -> {
            main_parent.requestFocus();
            switch (newValue) {
                case HO_KHAU -> main_parent.setCenter(Model.getInstance().getViewFactory().getHoKhauView());
                case NHAN_KHAU -> main_parent.setCenter(Model.getInstance().getViewFactory().getNhanKhauView());

                case Them_Moi -> main_parent.setCenter(Model.getInstance().getNhankhauFactoryView().getthemMoiView());
                case Tam_Vang -> main_parent.setCenter(Model.getInstance().getNhankhauFactoryView().gettamVangView());
                case Tam_Tru -> main_parent.setCenter(Model.getInstance().getNhankhauFactoryView().gettamTruView());
                case TAM_TRU_2 -> main_parent.setCenter(Model.getInstance().getNhankhauFactoryView().gettamTru2View());
                case Khai_Tu -> main_parent.setCenter(Model.getInstance().getNhankhauFactoryView().getkhaiTuView());
                default -> main_parent.setCenter(Model.getInstance().getViewFactory().getTrangChuView());
            }
        });
    }
}
