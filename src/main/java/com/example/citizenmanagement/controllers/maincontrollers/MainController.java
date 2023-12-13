package com.example.citizenmanagement.controllers.maincontrollers;

import com.example.citizenmanagement.models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private BorderPane main_parent;

    private Button ho_khau_btn;
    private Button nhan_khau_btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ho_khau_btn = (Button) main_parent.lookup("#ho_khau_btn");
        nhan_khau_btn = (Button) main_parent.lookup("#nhan_khau_btn");

        addListener();
    }

    private void addListener() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case NHAN_KHAU -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getNhanKhauView());
                    nhan_khau_btn.requestFocus();
                }
                case THONG_KE_NHAN_KHAU -> main_parent.setCenter(Model.getInstance().getViewFactoryThongKe().getThongKeNhanKhauView());
                case THONG_KE_HO_KHAU -> main_parent.setCenter(Model.getInstance().getViewFactoryThongKe().getThongKeHoKhauView());
                case THONG_KE_TAM_TRU -> main_parent.setCenter(Model.getInstance().getViewFactoryThongKe().getThongKeTamTruView());
                case THONG_KE_TAM_VANG -> main_parent.setCenter(Model.getInstance().getViewFactoryThongKe().getThongKeTamVangView());
                //ho khau ****************************************************************************************
                case HO_KHAU -> {
                    main_parent.setCenter(Model.getInstance().getViewHK().getHoKhauShow());
                    ho_khau_btn.requestFocus();
                }
                case XEM_CHI_TIET_HO_KHAU -> {
                    main_parent.setCenter(Model.getInstance().getViewHK().getXemChiTietHoKhau());
                    ho_khau_btn.requestFocus();
                }
                case TACH_HO_KHAU -> {
                    main_parent.setCenter(Model.getInstance().getViewHK().getTachHoKhau());
                    ho_khau_btn.requestFocus();
                }
                case THEM_HO_KHAU -> {
                    main_parent.setCenter(Model.getInstance().getViewHK().getThemHoKhau());
                    ho_khau_btn.requestFocus();
                }
                //*********************************************************************************

                case THEM_NHAN_KHAU -> {
                    main_parent.setCenter(Model.getInstance().getNhankhauFactoryView().getthemMoiView());
                    nhan_khau_btn.requestFocus();
                }
                case TAM_VANG -> {
                    main_parent.setCenter(Model.getInstance().getNhankhauFactoryView().gettamVangView());
                    nhan_khau_btn.requestFocus();
                }
                case TAM_TRU -> {
                    main_parent.setCenter(Model.getInstance().getNhankhauFactoryView().gettamTruView());
                    nhan_khau_btn.requestFocus();
                }
                case KHAI_TU -> {
                    main_parent.setCenter(Model.getInstance().getNhankhauFactoryView().getkhaiTuView());
                    nhan_khau_btn.requestFocus();
                }
                default -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getTrangChuView());
                }
            }
        });
    }
}
