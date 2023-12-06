package com.example.citizenmanagement.controllers.maincontrollers.hoKhau;

import com.example.citizenmanagement.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.zip.InflaterInputStream;

public class xemChiTietHokhauControler implements Initializable {
    public ListView listView_thanhvien;
    public TextField ma_ho_khau;
    public TextField ma_chu_ho;
    public TextField ten_chu_ho;
    public TextField dia_chi;
    public TextField ngay_tao;
    public TextField ngay_chuyen;
    public TextField ghi_chu;
    public Button chuyen_di_but;
    public Button cancel_but;
    public Button xac_nhan_but;
    public Button thaydoi_but;
    public Button them_but;
    public TextField tim_kiem_text;
    public Button tim_kiem_but;
    public ListView listview_tim_kiem;
    public Label thong_bao_loi_lbl;
    public Button xoa_but;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chuyen_di_but.setOnAction(event -> {
            thaydoi_but.setDisable(true);

            ngay_chuyen.setDisable(false);
            ghi_chu.setDisable(false);
        });

        thaydoi_but.setOnAction(event -> {
            chuyen_di_but.setDisable(true);

            ma_chu_ho.setDisable(false);
            ten_chu_ho.setDisable(false);
            dia_chi.setDisable(false);
            ghi_chu.setDisable(false);
            them_but.setDisable(false);
            xoa_but.setDisable(false);

            tim_kiem_but.setVisible(true);
            tim_kiem_text.setVisible(true);
            listview_tim_kiem.setVisible(true);
        });

        xac_nhan_but.setOnAction(event -> {
            Model.getInstance().getDataBCHK().capNhatHoKhau(ma_ho_khau.getText(),ma_chu_ho.getText(),dia_chi.getText(),ngay_chuyen.getText(),ghi_chu.getText());
            chuyen_di_but.setDisable(false);
            thaydoi_but.setDisable(false);

            ma_chu_ho.setDisable(true);
            ten_chu_ho.setDisable(true);
            dia_chi.setDisable(true);
            ghi_chu.setDisable(true);
            ngay_chuyen.setDisable(true);
            them_but.setDisable(true);
            xoa_but.setDisable(true);

            tim_kiem_but.setVisible(false);
            tim_kiem_text.setVisible(false);
            listview_tim_kiem.setVisible(false);

            Stage stage = (Stage)xac_nhan_but.getScene().getWindow();
            BorderPane tam = Model.getInstance().getViewFactory().getMain();
            tam.setCenter(Model.getInstance().getViewHK().getHoKhauShow());
            Scene scene = new Scene(tam);
            stage.setScene(scene);
        });

        cancel_but.setOnAction(event -> {
            chuyen_di_but.setDisable(false);
            thaydoi_but.setDisable(false);

            ma_chu_ho.setDisable(true);
            ten_chu_ho.setDisable(true);
            dia_chi.setDisable(true);
            ghi_chu.setDisable(true);
            ngay_chuyen.setDisable(true);
            them_but.setDisable(true);
            xoa_but.setDisable(true);

            tim_kiem_but.setVisible(false);
            tim_kiem_text.setVisible(false);
            listview_tim_kiem.setVisible(false);

            Stage stage = (Stage)cancel_but.getScene().getWindow();
            BorderPane tam = Model.getInstance().getViewFactory().getMain();
            tam.setCenter(Model.getInstance().getViewHK().getHoKhauShow());
            Scene scene = new Scene(tam);
            stage.setScene(scene);
        });

        tim_kiem_but.setOnAction(event -> {
            String timkiem = tim_kiem_text.getText();

        });

        them_but.setOnAction(event -> {

        });

        xoa_but.setOnAction(event -> {

        });
    }
}
