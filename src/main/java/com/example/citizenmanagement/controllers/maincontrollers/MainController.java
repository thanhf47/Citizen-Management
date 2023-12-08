package com.example.citizenmanagement.controllers.maincontrollers;

import com.example.citizenmanagement.models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public BorderPane main_parent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Model.getInstance().getViewFactory().getSelectedMenuItem().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case NHAN_KHAU -> main_parent.setCenter(Model.getInstance().getViewFactory().getNhanKhauView());
                case THONG_KE_NHAN_KHAU -> main_parent.setCenter(Model.getInstance().getViewFactoryThongKe().getThongKeNhanKhauView());
                case THONG_KE_HO_KHAU -> main_parent.setCenter(Model.getInstance().getViewFactoryThongKe().getThongKeHoKhauView());
                case THONG_KE_TAM_TRU -> main_parent.setCenter(Model.getInstance().getViewFactoryThongKe().getThongKeTamTruView());
                case THONG_KE_TAM_VANG -> main_parent.setCenter(Model.getInstance().getViewFactoryThongKe().getThongKeTamVangView());
                //ho khau ****************************************************************************************
                case HO_KHAU -> main_parent.setCenter(Model.getInstance().getViewHK().getHoKhauShow());
                case XEM_CHI_TIET_HO_KHAU -> main_parent.setCenter(Model.getInstance().getViewHK().getXemChiTietHoKhau());
                case TACH_HO_KHAU -> main_parent.setCenter(Model.getInstance().getViewHK().getTachHoKhau());
                case THEM_HO_KHAU -> main_parent.setCenter(Model.getInstance().getViewHK().getThemHoKhau());
                //*********************************************************************************
                default -> main_parent.setCenter(Model.getInstance().getViewFactory().getTrangChuView());
            }
        });

    }
}
