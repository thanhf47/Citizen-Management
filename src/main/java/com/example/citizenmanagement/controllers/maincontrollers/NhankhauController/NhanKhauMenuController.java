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


public class NhanKhauMenuController implements Initializable {

    @FXML
    public Button themmoi_btn;



    public Button tamtru_button;

    @FXML
    public ListView<List_nhan_khau> list_view;
    @FXML
    public TextField tim_kiem_text;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        capnhat();
        this.addListener();
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
                                String hoten = resultSet.getNString(3);
                                String gioitinh = resultSet.getString(4);
                               String namsinh = resultSet.getString(5);
                                String diachi = resultSet.getNString(6);

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
            List_nhan_khau selected = list_view.getSelectionModel().getSelectedItem();
            if(selected != null) {
                Model.setNhanKhauDuocChon(selected);
                Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.XEM_CHI_TIET_NHAN_KHAU);
            }
        });

    }

    private void addListener() {
        themmoi_btn.setOnAction(actionEvent -> onThemmoi());
        tamtru_button.setOnAction(actionEvent -> onTamtru());
    }

    private void onThemmoi() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.THEM_NHAN_KHAU);
    }

    private void onTamtru() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.TAM_TRU);
    }
    private void onTamvang() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.TAM_VANG);
    }
    private void onKhaitu() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.KHAI_TU);
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
            System.out.println("Loi ơr NhanKhaumenucontroller");
        }
        list_view.setCellFactory(param-> new List_nhan_khau_factory());
    }

}
