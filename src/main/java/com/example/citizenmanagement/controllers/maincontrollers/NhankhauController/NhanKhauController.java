package com.example.citizenmanagement.controllers.maincontrollers.NhankhauController;

import com.example.citizenmanagement.models.List_nhan_khau;
import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import com.example.citizenmanagement.views.List_nhan_khau_factory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class NhanKhauController implements Initializable {

    @FXML
    public Button themmoi_btn;

    @FXML
    public ListView<List_nhan_khau> list_view;
    @FXML
    public TextField tim_kiem_text;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        themmoi_btn.setOnAction(actionEvent -> onThemmoi());

        capnhat();
        tim_kiem_text.textProperty().addListener((observableValue, oldval, newval) -> {
            if(newval.isEmpty()){
                capnhat();
            }
            else {
                ResultSet resultSet = Model.getInstance().getDatabaseConnection().nhanKhau_timkiem(tim_kiem_text.getText());
                list_view.getItems().clear();
                try {
                        if(resultSet.isBeforeFirst()) {
                            while(resultSet.next()) {
                                String ma = resultSet.getString(1);
                                String id = resultSet.getString(2);
                                 if (id == null) id = "null";
                                String hoten = resultSet.getNString(3);
                                String gioitinh = resultSet.getString(4);
                               String namsinh = resultSet.getString(5);
                                String diachi = resultSet.getNString(6);
                                if (diachi == null) diachi = "null";

                                list_view.getItems().add(new List_nhan_khau(ma, id, hoten, gioitinh, namsinh, diachi));
                            }
                        }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        list_view.setCellFactory(param ->new List_nhan_khau_factory());

        list_view.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                List_nhan_khau selected = list_view.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    Model.setNhanKhauDuocChon(selected);
                    Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.XEM_CHI_TIET_NHAN_KHAU);
                }
            }
        });

    }

    private void onThemmoi() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.THEM_NHAN_KHAU);
    }

    public void capnhat() {
        ResultSet resultSet = Model.getInstance().getDatabaseConnection().truyvan();
        list_view.getItems().clear();
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

                   list_view.getItems().add(new List_nhan_khau(ma,id, hoten, gioitinh, ngaysinh, noisinh));
                }
            }
        }
        catch(Exception e) {
            System.out.println("Lỗi ở NhanKhaumenucontroller");
        }
        list_view.setCellFactory(param-> new List_nhan_khau_factory());
    }
}
