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

    private Button trang_chu_btn;
    private Button ho_khau_btn;
    private Button nhan_khau_btn;
    private Button quadoi_btn;
    private Button tamvang_btn;
    public Button tamtru_btn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        trang_chu_btn = (Button) main_parent.lookup("#trang_chu_btn");
        ho_khau_btn = (Button) main_parent.lookup("#ho_khau_btn");
        nhan_khau_btn = (Button) main_parent.lookup("#nhan_khau_btn");
        quadoi_btn = (Button) main_parent.lookup("#quadoi_btn");
        tamvang_btn = (Button) main_parent.lookup("#tamvang_btn");
        tamtru_btn = (Button) main_parent.lookup("#tamtru_btn");

        addListener();
    }

    private void addListener() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().addListener((observable, oldValue, newValue) -> {
            main_parent.requestFocus();
            switch (newValue) {
                case NHAN_KHAU -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getNhanKhauView());
                    nhan_khau_btn.requestFocus();
                }
                case PROFILE -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getProfile());
                    trang_chu_btn.requestFocus();
                }
                case THONG_KE_NHAN_KHAU -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getThongKeNhanKhauView());
                    trang_chu_btn.requestFocus();
                }
                case THONG_KE_HO_KHAU -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getThongKeHoKhauView());
                    trang_chu_btn.requestFocus();
                }
                case THONG_KE_TAM_TRU -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getThongKeTamTruView());
                    trang_chu_btn.requestFocus();
                }
                case THONG_KE_TAM_VANG -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getThongKeTamVangView());
                    trang_chu_btn.requestFocus();
                }
                case TRANG_CHU_TAM_VANG -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getTrangChuTamVangView());
                    tamvang_btn.requestFocus();
                }
                case CHI_TIET_THONG_TIN_TAM_VANG -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getChiTietThongTinTamVangView());
                    tamvang_btn.requestFocus();
                }
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
                case THEM_CHU_HO_KHAU -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getThemChuHoKhau());
                }
                case THEM_THANH_VIEN_HO_KHAU -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getThemThanhVienHoKhau());
                    ho_khau_btn.requestFocus();
                }
                case QUA_DOI -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getListDeadView());
                    quadoi_btn.requestFocus();
                }
                case THONG_TIN_KHAI_TU -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getThongTinKhaiTu());
                    quadoi_btn.requestFocus();
                }
                case THAY_DOI_HO_KHAU -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().get_thay_doi_ho_khau());
                    ho_khau_btn.requestFocus();
                }
                //*********************************************************************************

                case THEM_NHAN_KHAU -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getthemMoiView());
                    nhan_khau_btn.requestFocus();
                }
                case TAM_VANG -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().gettamVangView());
                    nhan_khau_btn.requestFocus();
                }
                case TAM_TRU -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().gettamTruView());
                    nhan_khau_btn.requestFocus();
                }
                case TAM_TRU_LIST -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getTamTruListView());
                    tamtru_btn.requestFocus();
                }
                case TAM_TRU_2 -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().gettamTru2View());
                    nhan_khau_btn.requestFocus();
                }
                case KHAI_TU -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getkhaiTuView());
                    nhan_khau_btn.requestFocus();
                }

                case XEM_CHI_TIET_NHAN_KHAU -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getXemChiTietNhanKhau());
                    nhan_khau_btn.requestFocus();
                }
                case XEM_CHI_TIET_TAM_TRU -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getXemChiTietTamTru());
                    tamtru_btn.requestFocus();
                }
                default -> {
                    main_parent.setCenter(Model.getInstance().getViewFactory().getTrangChuView());
                    trang_chu_btn.requestFocus();
                }

            }
        });
    }
}
