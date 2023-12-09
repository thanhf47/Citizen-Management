package com.example.citizenmanagement.controllers.maincontrollers.hoKhau;

import com.example.citizenmanagement.models.hoKhauCell;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class hoKhauCellControler implements Initializable {
//122222222222222232
    private final hoKhauCell khauCell;
    public AnchorPane anchorPane;
    public HBox hbox;
    public Line line1;
    public Text ma_ho_khau_text;
    public Line line2;
    public Line line3;
    public Text ma_chu_ho_text;
    public Text dia_chi_text;
    public Line line4;
    public Text ngay_lap_text;
    public Text ngay_chuyen_di_text;
    public Line line5;
    public Line line6;
    public Text ghi_chu_text;

    public hoKhauCellControler(hoKhauCell khauCell){
        this.khauCell = khauCell;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ma_ho_khau_text.setText(String.valueOf(khauCell.getId().get()));
        ma_chu_ho_text.setText(String.valueOf(khauCell.getOwner().get()));
        dia_chi_text.setText(String.valueOf(khauCell.getAddress().get()));
        ngay_lap_text.setText(String.valueOf(khauCell.getDate_tao().get()));
        ngay_chuyen_di_text.setText(String.valueOf(khauCell.getDate_chuyen().get()));
        ghi_chu_text.setText(String.valueOf(khauCell.getGhi_chu().get()));
    }
}
