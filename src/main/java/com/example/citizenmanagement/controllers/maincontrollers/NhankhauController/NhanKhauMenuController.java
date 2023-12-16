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
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class NhanKhauMenuController implements Initializable {

    @FXML
    public Button themmoi_btn;
    @FXML
    public Button khaitu_button;
    @FXML
    public Button tamtru_button;
    @FXML
    public Button tamvang_button;
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
                                String cccd = resultSet.getString(1);
                                String hoTen = resultSet.getString(2);
                                String gioitinh = resultSet.getString(3);
                                String namsinh = resultSet.getString(4);
                                String diachi = resultSet.getString(5);

                                list_view.getItems().add(new List_nhan_khau(cccd, hoTen, gioitinh, namsinh, diachi));
                            }
                            list_view.setCellFactory(param ->new List_nhan_khau_factory());
                        }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }



        });

    }



    private void addListener() {
        themmoi_btn.setOnAction(actionEvent -> onThemmoi());
        tamtru_button.setOnAction(actionEvent -> onTamtru());
        tamvang_button.setOnAction(actionEvent -> onTamvang());
        khaitu_button.setOnAction(actionEvent -> onKhaitu());
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
                    String id = resultSet.getString(1);
                    String hoten = resultSet.getNString(2);
                    String gioitinh = resultSet.getString(3);
                    String namsinh = resultSet.getString(4);
                    String thuongtru = resultSet.getNString(5);
                    list_view.getItems().add(new List_nhan_khau(id, hoten, gioitinh, namsinh, thuongtru));
                }
            }
        }
        catch(Exception e) {
            System.out.println("Concac");
        }
        list_view.setCellFactory(param-> new List_nhan_khau_factory());
    }

}
