package com.example.citizenmanagement.controllers.maincontrollers.hoKhau;

import com.example.citizenmanagement.models.MainHoKhauCell;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class hoKhauCellControler implements Initializable {

    private final MainHoKhauCell khauCell;
    public Text ma_ho_khau_text;
    public Text ten_chu_ho_text;
    public Text dia_chi_text;
    public Text ngay_lap_text;
    public Text ghi_chu_text;

    public hoKhauCellControler(MainHoKhauCell khauCell){
        this.khauCell = khauCell;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ma_ho_khau_text.setText(String.valueOf(khauCell.getId().get()));
        ten_chu_ho_text.setText(String.valueOf(khauCell.getOwner().get()));
        dia_chi_text.setText(String.valueOf(khauCell.getAddress().get()));
        ngay_lap_text.setText(String.valueOf(khauCell.getDate_tao().get()));
        ghi_chu_text.setText(String.valueOf(khauCell.getGhi_chu().get()));
    }
}
