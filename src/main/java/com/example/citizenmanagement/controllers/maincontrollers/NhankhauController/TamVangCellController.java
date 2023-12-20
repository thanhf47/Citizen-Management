package com.example.citizenmanagement.controllers.maincontrollers.NhankhauController;

import com.example.citizenmanagement.models.MainHoKhauCell;
import com.example.citizenmanagement.models.MainTamVangCell;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class TamVangCellController implements Initializable {
    @FXML
    private Text hovaten_text;
    @FXML
    private Text denngay_text;




    @FXML
    private Text magiaytamvang_text;

    @FXML
    private Text manhankhau_text;

    @FXML
    private Text noitamtru_text;

    @FXML
    private Text tungay_text;
    private final MainTamVangCell khauCell;

    public TamVangCellController(MainTamVangCell mainTamVangCell){
        this.khauCell = mainTamVangCell;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hovaten_text.setText(String.valueOf(khauCell.getHoVaTen().get()));
        magiaytamvang_text.setText(String.valueOf(khauCell.getMaGiayTamVang().get()));
        manhankhau_text.setText(String.valueOf(khauCell.getMaNhanKhau().get()));
        noitamtru_text.setText(String.valueOf(khauCell.getNoiTamTru().get()));
        tungay_text.setText(String.valueOf(khauCell.getTuNgay().get()));
        denngay_text.setText(String.valueOf(khauCell.getDenNgay().get()));

    }
}
