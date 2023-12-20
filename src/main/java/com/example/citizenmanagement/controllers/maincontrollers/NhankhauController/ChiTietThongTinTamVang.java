package com.example.citizenmanagement.controllers.maincontrollers.NhankhauController;

import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.MainTamVangCell;
import com.example.citizenmanagement.models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ChiTietThongTinTamVang implements Initializable {

    private MainTamVangCell luaChon;
    @FXML
    private TextField denngay_fld;

    @FXML
    private TextField hoten_fld;

    @FXML
    private TextField lydo_fld;

    @FXML
    private TextField magiaytamvang_fld;

    @FXML
    private TextField manhankhau_fld;

    @FXML
    private TextField ngaysinh_fld;

    @FXML
    private TextField noitamtru_fld;

    @FXML
    private TextField socancuoc_fld;

    @FXML
    private TextField tungay_fld;

    @FXML
    private Button thoat_btn;
    @FXML
    private Button xoaTamVang;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        luaChon = Model.getNhanKhauTamVangDuocChon();
        hoten_fld.setText(String.valueOf(luaChon.getHoVaTen().get()));
        socancuoc_fld.setText(String.valueOf(luaChon.getSoCCCD().get()));
        ngaysinh_fld.setText(String.valueOf(luaChon.getNgaySinh().get()));
        magiaytamvang_fld.setText(String.valueOf(luaChon.getMaGiayTamVang().get()));
        manhankhau_fld.setText(String.valueOf(luaChon.getMaNhanKhau().get()));
        noitamtru_fld.setText(String.valueOf(luaChon.getNoiTamTru().get()));
        tungay_fld.setText(String.valueOf(luaChon.getTuNgay().get()));
        denngay_fld.setText(String.valueOf(luaChon.getDenNgay().get()));
        lydo_fld.setText(String.valueOf(luaChon.getLyDo().get()));


        //thoat
        thoat_btn.setOnAction(event ->{
            Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.TRANG_CHU_TAM_VANG);
        });
        xoaTamVang.setOnAction(event -> onXoaTamVang());
    }

    public void onXoaTamVang(){
        int magiaytamvang = Integer.parseInt(magiaytamvang_fld.getText());
        Model.getInstance().getDatabaseConnection().xoaTamVang(magiaytamvang);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText("Xoá Thành Công");
        alert.setContentText("Thông tin đã được xoá");
        alert.showAndWait();
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.TRANG_CHU_TAM_VANG);
    }
}
