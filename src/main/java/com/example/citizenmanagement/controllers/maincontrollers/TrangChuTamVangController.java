package com.example.citizenmanagement.controllers.maincontrollers;

import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.MainTamVangCell;
import com.example.citizenmanagement.models.Model;
import com.example.citizenmanagement.views.MainTamVangCellFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class TrangChuTamVangController implements Initializable {

    @FXML
    private Button khaibaotamvang_btn;

    @FXML
    private ListView listview_tamvang;

    @FXML
    private TextField search_tamvang_fld;

    @FXML
    private Button timkiemtamvang_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showdanhsach();
        listview_tamvang.setOnMouseClicked(mouseEvent -> {
            Model.setNhanKhauTamVangDuocChon((MainTamVangCell) listview_tamvang.getSelectionModel().getSelectedItem());
            Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.CHI_TIET_THONG_TIN_TAM_VANG);
        });
    }

    public void showdanhsach() {
        ResultSet resultSet = Model.getInstance().getDatabaseConnection().getDanhSachTamVang();
        listview_tamvang.getItems().clear();
        try {
            if(resultSet.isBeforeFirst()){
                while (resultSet.next()){
                    String hovaten = resultSet.getString("HOTEN");
                    String magiaytamvang = resultSet.getString("MAGIAYTAMVANG");
                    String manhankhau = resultSet.getString("MANHANKHAU");
                    String noitamtru = resultSet.getString("NOITAMTRU");
                    String tungay = resultSet.getString("TUNGAY");
                    String denngay = resultSet.getString("DENNGAY");
                    String soCCCD = resultSet.getString("SOCANCUOC");
                    String ngaysinh = resultSet.getString("NGAYSINH");
                    String lido = resultSet.getString("LYDO");

                    listview_tamvang.getItems().add(new MainTamVangCell(hovaten,magiaytamvang,manhankhau,noitamtru,tungay,denngay,soCCCD,ngaysinh,lido));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        listview_tamvang.setCellFactory(param -> new MainTamVangCellFactory());
    }



}
