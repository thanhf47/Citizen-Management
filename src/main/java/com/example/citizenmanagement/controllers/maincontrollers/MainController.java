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
    private Button tamvang_btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ho_khau_btn = (Button) main_parent.lookup("#ho_khau_btn");
        nhan_khau_btn = (Button) main_parent.lookup("#nhan_khau_btn");
        tamvang_btn = (Button) main_parent.lookup("#tamvang_btn");

        addListener();
    }

    private void addListener() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case NHAN_KHAU -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getNhanKhauView());
                    nhan_khau_btn.requestFocus();
                }
                case PROFILE -> main_parent.setCenter(Model.getInstance().getViewFactory().getProfile());
                case THONG_KE_NHAN_KHAU -> main_parent.setCenter(Model.getInstance().getViewFactory().getThongKeNhanKhauView());
                case THONG_KE_HO_KHAU -> main_parent.setCenter(Model.getInstance().getViewFactory().getThongKeHoKhauView());
                case THONG_KE_TAM_TRU -> main_parent.setCenter(Model.getInstance().getViewFactory().getThongKeTamTruView());
                case THONG_KE_TAM_VANG -> main_parent.setCenter(Model.getInstance().getViewFactory().getThongKeTamVangView());
                case TRANG_CHU_TAM_VANG -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getTrangChuTamVangView());
                    tamvang_btn.requestFocus();
                }
                case CHI_TIET_THONG_TIN_TAM_VANG -> main_parent.setCenter(Model.getInstance().getViewFactory().getChiTietThongTinTamVangView());
                //ho khau ****************************************************************************************
                case HO_KHAU -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getHoKhauView());
                    ho_khau_btn.requestFocus();
                }
                case XEM_CHI_TIET_HO_KHAU -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getXemChiTietHoKhau());
                    ho_khau_btn.requestFocus();
                }
                case TACH_HO_KHAU -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getTachHoKhau());
                    ho_khau_btn.requestFocus();
                }
                case THEM_HO_KHAU -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getThemHoKhau());
                    ho_khau_btn.requestFocus();
                }
                //*********************************************************************************

                case THEM_NHAN_KHAU -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getthemMoiView());
                    nhan_khau_btn.requestFocus();
                }
                case TAM_VANG -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().gettamVangView());
                    tamvang_btn.requestFocus();
                }
                case TAM_TRU -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().gettamTruView());
                    nhan_khau_btn.requestFocus();
                }
                case KHAI_TU -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getkhaiTuView());
                    nhan_khau_btn.requestFocus();
                }
                default -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getTrangChuView());
                }
            }
        });
    }
}
