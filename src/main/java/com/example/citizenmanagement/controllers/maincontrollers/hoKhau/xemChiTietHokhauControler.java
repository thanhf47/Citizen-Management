package com.example.citizenmanagement.controllers.maincontrollers.hoKhau;

import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import com.example.citizenmanagement.models.MainHoKhauCell;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class xemChiTietHokhauControler implements Initializable {
    public ListView listView_thanhvien;
    public TextField ma_ho_khau;
    public TextField ma_chu_ho;
    public TextField ten_chu_ho;
    public TextField dia_chi;
    public TextField ngay_tao;
    public TextField ghi_chu;
    public Button chuyen_di_but;
    public Button cancel_but;
    public Button xac_nhan_but;
    public Button thaydoi_but;
    public Button them_but;
    public TextField tim_kiem_text;
    public ListView listview_tim_kiem;
    public Label thong_bao_loi_lbl;
    public Button xoa_but;
    public Button quay_lai_but;

    private MainHoKhauCell tam;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tam=Model.getHoKhauDuocChon();
        ma_ho_khau.setText(String.valueOf(tam.getId().get()));
        ma_chu_ho.setText(String.valueOf(tam.getOwner().get()));
        dia_chi.setText(String.valueOf(tam.getAddress().get()));
        ngay_tao.setText(String.valueOf(tam.getDate_tao().get()));
        ghi_chu.setText(String.valueOf(tam.getGhi_chu().get()));
        //********************************************************
        chuyen_di_but.setOnAction(event -> {
            thaydoi_but.setVisible(false);
            quay_lai_but.setVisible(false);
            xac_nhan_but.setVisible(true);
            cancel_but.setVisible(true);

            thong_bao_loi_lbl.setText("");

        });

        thaydoi_but.setOnAction(event -> {
            chuyen_di_but.setVisible(false);
            quay_lai_but.setVisible(false);
            xac_nhan_but.setVisible(true);
            cancel_but.setVisible(true);

            ma_chu_ho.setDisable(false);
            ten_chu_ho.setDisable(false);
            dia_chi.setDisable(false);
            ghi_chu.setDisable(false);
            them_but.setDisable(false);
            xoa_but.setDisable(false);

            tim_kiem_text.setVisible(true);
            listview_tim_kiem.setVisible(true);

            xac_nhan_but.setVisible(true);
            cancel_but.setVisible(true);

            thong_bao_loi_lbl.setText("");

        });

        xac_nhan_but.setOnAction(event -> {

            quay_lai_but.setVisible(true);
            cancel_but.setVisible(false);
            xac_nhan_but.setVisible(false);
            if(thaydoi_but.isVisible()) {
                chuyen_di_but.setVisible(true);
                ma_chu_ho.setDisable(true);
                ten_chu_ho.setDisable(true);
                dia_chi.setDisable(true);
                ghi_chu.setDisable(true);
                them_but.setDisable(true);
                xoa_but.setDisable(true);

                tim_kiem_text.setVisible(false);
                listview_tim_kiem.setVisible(false);


                int ketqua = 0;
                ketqua = Model.getInstance().getDatabaseConnection().capNhatHoKhau(ma_ho_khau.getText(), ma_chu_ho.getText(), dia_chi.getText(), ghi_chu.getText());
                if (ketqua != 0) {
                    thong_bao_loi_lbl.setStyle("-fx-text-fill: #10e61c");
                    thong_bao_loi_lbl.setText("Thay doi thanh cong");
                }
                else {
                    thong_bao_loi_lbl.setStyle("-fx-text-fill: #aa0000");
                    thong_bao_loi_lbl.setText("Khong thay doi thanh cong");
                }
            }
            else {
                thaydoi_but.setVisible(true);
                int ketqua = Model.getInstance().getDatabaseConnection().xoaHoKhau(ma_ho_khau.getText());
                if(ketqua!=0) {
                    thong_bao_loi_lbl.setStyle("-fx-text-fill: #10e61c");
                    thong_bao_loi_lbl.setText("Xoa thanh cong");
                }
                else {
                    thong_bao_loi_lbl.setStyle("-fx-text-fill: #aa0000");
                    thong_bao_loi_lbl.setText("Khong xoa thanh cong");
                }
            }

        });

        cancel_but.setOnAction(event -> {
            quay_lai_but.setVisible(true);
            thaydoi_but.setVisible(true);
            chuyen_di_but.setVisible(true);
            cancel_but.setVisible(false);
            xac_nhan_but.setVisible(false);

            ma_chu_ho.setDisable(true);
            ten_chu_ho.setDisable(true);
            dia_chi.setDisable(true);
            ghi_chu.setDisable(true);
            them_but.setDisable(true);
            xoa_but.setDisable(true);

            tim_kiem_text.setVisible(false);
            listview_tim_kiem.setVisible(false);
        });

        quay_lai_but.setOnAction(event->{
            Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.HO_KHAU);
        });


        them_but.setOnAction(event -> {

        });

        xoa_but.setOnAction(event -> {

        });
    }
}
