package com.example.citizenmanagement.controllers.maincontrollers;

import com.example.citizenmanagement.models.List_nhan_khau;
import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import com.example.citizenmanagement.views.List_nhan_khau_factory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ListDeadController implements Initializable {

    @FXML
    private ListView<List_nhan_khau> listView;

    @FXML
    private TextField search_textfield;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();


        search_textfield.textProperty().addListener((observableValue, oldval, newval) -> {
            if(newval.isEmpty()){
                init();
            }
            else {
                ResultSet resultSet = Model.getInstance().getDatabaseConnection().deadNhanKhau_timkiem(search_textfield.getText());
                listView.getItems().clear();
                try {
                    if(resultSet.isBeforeFirst()) {
                        while(resultSet.next()) {
                            String ma = resultSet.getString(1);
                            String id = resultSet.getString(2);
                            String hoten = resultSet.getNString(3);
                            String gioitinh = resultSet.getString(4);
                            String namsinh = resultSet.getString(5);
                            String diachi = resultSet.getNString(6);

                            listView.getItems().add(new List_nhan_khau(ma, id, hoten, gioitinh, namsinh, diachi));
                        }

                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        listView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                List_nhan_khau selected = listView.getSelectionModel().getSelectedItem();
                if(selected != null) {
                    Model.setNhanKhauDuocChon(selected);
                    Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.THONG_TIN_KHAI_TU);
                }
            }
        });

        listView.setCellFactory(param ->new List_nhan_khau_factory());
    }

    private void init() {
        ResultSet resultSet = Model.getInstance().getDatabaseConnection().getDSNguoiChet();
        listView.getItems().clear();
        try {
            if(resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    String maNhanKhau = resultSet.getString(1);
                    String soCanCuoc = resultSet.getString(2);
                    String hoTen = resultSet.getNString(3);
                    String gioiTinh = resultSet.getString(4);
                    String ngaySinh = resultSet.getString(5);
                    String diaChi = resultSet.getNString(6);

                    listView.getItems().add(new List_nhan_khau(maNhanKhau, soCanCuoc, hoTen, gioiTinh, ngaySinh, diaChi));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
