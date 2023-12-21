package com.example.citizenmanagement.controllers.maincontrollers.hoKhau;


import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import com.example.citizenmanagement.models.MainHoKhauCell;
import com.example.citizenmanagement.views.MainHoKhauCellFactory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;

import java.util.*;

public class hoKhauShowControler implements Initializable {
    @FXML
    private TextField search_textfield;
    @FXML
    private ListView<MainHoKhauCell> listView;
    @FXML
    private Button them_but;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        capnhat();

        search_textfield.textProperty().addListener((observable, oldvalue, newvalue)->{

            if(newvalue.isEmpty()){
                capnhat();
            }
            else {
                ResultSet resultSet = Model.getInstance().getDatabaseConnection().timKiem(search_textfield.getText());
                listView.getItems().clear();
                try {
                    if(resultSet.isBeforeFirst()){
                        while (resultSet.next()){
                            String id = resultSet.getString(1);
                            String Owner = resultSet.getString(6);
                            String add = resultSet.getString(3);
                            String date_tao = resultSet.getString(4);
                            String ghi_chu = resultSet.getString(5);

                            listView.getItems().add(new MainHoKhauCell(id, Owner, add,date_tao, ghi_chu));
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }// END ELSE
        });

        listView.setCellFactory(param-> new MainHoKhauCellFactory());
        //**************************************************************

        listView.setOnMouseClicked(mouseEvent -> {
            Model.setHoKhauDuocChon(listView.getSelectionModel().getSelectedItem());
            Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.XEM_CHI_TIET_HO_KHAU);
        });
        //****************************************************

        them_but.setOnAction(event -> {
            Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.THEM_CHU_HO_KHAU);
        });
    }

    private void capnhat(){
        ResultSet resultSet = Model.getInstance().getDatabaseConnection().getDanhSachHoKhau();
        listView.getItems().clear();
        try {
            if(resultSet.isBeforeFirst()){
                while (resultSet.next()){
                    String id = resultSet.getString(1);
                    ResultSet resultSet1= Model.getInstance().getDatabaseConnection().lay_nhan_khau(resultSet.getString(2));
                    String Owner=null;
                    try {
                        if(resultSet1.isBeforeFirst()){
                            resultSet1.next();
                            Owner = resultSet1.getString(2);
                        }
                    }catch (Exception e){
                        throw new RuntimeException(e);
                    }
                    String add = resultSet.getString(3);


                    String date_tao;
                    date_tao = resultSet.getString(4);

                    String ghi_chu;
                    ghi_chu=resultSet.getString(5);

                    listView.getItems().add(new MainHoKhauCell(id, Owner, add,date_tao,ghi_chu));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
