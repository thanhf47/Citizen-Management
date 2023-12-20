package com.example.citizenmanagement.controllers.maincontrollers.NhankhauController;

import com.example.citizenmanagement.models.List_nhan_khau;
import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import com.example.citizenmanagement.views.List_nhan_khau_factory;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ListTamTruController implements Initializable {
    public ListView<List_nhan_khau> list_tam_tru;
    public Button Them_tam_tru_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        capnhat2();
        Them_tam_tru_btn.setOnAction(event -> onTamtru());

        list_tam_tru.setCellFactory(param -> new List_nhan_khau_factory());

        list_tam_tru.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                List_nhan_khau selected = list_tam_tru.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    Model.setNhanKhauDuocChon(selected);
                    Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.XEM_CHI_TIET_TAM_TRU);
                }
            }
        });
    }

    private void onTamtru() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.TAM_TRU);
    }

    public void capnhat2() {
        ResultSet resultSet = Model.getInstance().getDatabaseConnection().truyvanTamTru();
        list_tam_tru.getItems().clear();
        try{
            if(resultSet.isBeforeFirst()){
                while (resultSet.next()) {
                    String ma = resultSet.getString(1);
                    String id = resultSet.getString(2);
                    String hoten = resultSet.getNString(3);
                    String gioitinh = resultSet.getString(4);
                    Date ngay_sinh = resultSet.getDate(5);
                    String ngaysinh = "";

                    if (ngay_sinh != null) {
                        LocalDate localDate = ngay_sinh.toLocalDate();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        ngaysinh = localDate.format(formatter);
                    }

                    String noisinh = resultSet.getNString(6);

                    list_tam_tru.getItems().add(new List_nhan_khau(ma, id, hoten, gioitinh, ngaysinh, noisinh));
                }
            }
        }
        catch(Exception e) {
            System.out.println("Lỗi ở NhanKhaumenucontroller");
        }
        list_tam_tru.setCellFactory(param-> new List_nhan_khau_factory());
    }
}
