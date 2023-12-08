package com.example.citizenmanagement.controllers.maincontrollers.hoKhau;


import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import com.example.citizenmanagement.models.hoKhauCell;
import com.example.citizenmanagement.views.hoKhauCellFactory;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;

import java.util.*;

public class hoKhauShowControler implements Initializable {
    public TextField search_textfield;
    public ListView<hoKhauCell> listView;
    public Button tach_but;
    public Button them_but;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        capnhat();

        search_textfield.textProperty().addListener((observable, oldvalue, newvalue)->{

            if(newvalue.isEmpty()){
                capnhat();
            }
            else {
                ResultSet resultSet = Model.getInstance().getDataBCHK().timKiem(search_textfield.getText());
                listView.getItems().clear();
                try {
                    if(resultSet.isBeforeFirst()){
                        System.out.println("co");
                        while (resultSet.next()){
                            String id = resultSet.getString(1);
                            String Owner = resultSet.getString(2);
                            String add = resultSet.getString(3);
                            String date_tao = resultSet.getString(4);
                            String date_chuyen = resultSet.getString(5);
                            String ghi_chu = resultSet.getString(6);

                            listView.getItems().add(new hoKhauCell(id, Owner, add,date_tao,date_chuyen,ghi_chu));
                        }
                        listView.setCellFactory(param-> new hoKhauCellFactory());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }// END ELSE
        });
        //**************************************************************

        listView.setOnMouseClicked(mouseEvent -> {
            Model.setHoKhauDuocChon(listView.getSelectionModel().getSelectedItem());
            Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.XEM_CHI_TIET_HO_KHAU);

        });
        //****************************************************

        tach_but.setOnAction(event -> {
            Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.TACH_HO_KHAU);
        });
        //****************************************************

        them_but.setOnAction(event -> {
            Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.THEM_HO_KHAU);
        });
    }

    public void capnhat(){
        ResultSet resultSet = Model.getInstance().getDataBCHK().getResultSet();
        listView.getItems().clear();
        try {
            if(resultSet.isBeforeFirst()){
                while (resultSet.next()){
                    String id = resultSet.getString(1);
                    String Owner = resultSet.getString(2);
                    String add = resultSet.getString(3);
                    String date_tao = resultSet.getString(4);
                    String date_chuyen = resultSet.getString(5);
                    String ghi_chu = resultSet.getString(6);
                    listView.getItems().add(new hoKhauCell(id, Owner, add,date_tao,date_chuyen,ghi_chu));
                }
            }
        } catch (Exception e) {
            System.out.println("loi o hokhauShow");
        }
        listView.setCellFactory(param-> new hoKhauCellFactory());
    }
}
