package com.example.citizenmanagement.controllers.feecontrollers;

import com.example.citizenmanagement.models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class FeeController implements Initializable {
    @FXML
    private BorderPane fee_parent;


    private Button trang_chu_btn;
    private Button them_khoan_thu_btn;
    private Button danh_sach_phi_btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        trang_chu_btn = (Button) fee_parent.lookup("#trang_chu_btn");
        them_khoan_thu_btn = (Button) fee_parent.lookup("#them_khoan_thu_btn");
        danh_sach_phi_btn = (Button) fee_parent.lookup("#danh_sach_phi_btn");

        addListener();
    }

    private void addListener() {
        Model.getInstance().getViewFactory().getFeeSelectedMenuItem().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case THEM_KHOAN_THU_PHI -> {
                    them_khoan_thu_btn.requestFocus();
                    fee_parent.setCenter(Model.getInstance().getViewFactory().getFeeThemKhoanThuView());

                }
                case DANH_SACH_HO_KHAU_CAN_THU_PHI -> {
                    them_khoan_thu_btn.requestFocus();
                    fee_parent.setCenter(Model.getInstance().getViewFactory().getFeeThemHoKhauView());

                }
                case DANH_SACH_KHOAN_THU -> {
                    danh_sach_phi_btn.requestFocus();
                    fee_parent.setCenter(Model.getInstance().getViewFactory().getFeeDanhSachView());

                }
                case CHI_TIET_KHOAN_THU -> {
                    danh_sach_phi_btn.requestFocus();
                    fee_parent.setCenter(Model.getInstance().getViewFactory().getFeeXemChiTietKhoanThuView());

                }
                case DA_HOAN_THANH_DONG_PHI -> {
                    danh_sach_phi_btn.requestFocus();
                    fee_parent.setCenter(Model.getInstance().getViewFactory().getDSHoanThanhPhiView());

                }
                case CHUA_HOAN_THANH_DONG_PHI -> {
                    danh_sach_phi_btn.requestFocus();
                    fee_parent.setCenter(Model.getInstance().getViewFactory().getDSChuaHoanThanhPhiView());
                }
                case FEE_PROFILE -> {
                    fee_parent.setCenter(Model.getInstance().getViewFactory().getFeeProfileView());
                    trang_chu_btn.requestFocus();
                }

                default -> {
                    trang_chu_btn.requestFocus();
                    fee_parent.setCenter(Model.getInstance().getViewFactory().getFeeTrangChuView());

                }
            }
        });
    }
}
