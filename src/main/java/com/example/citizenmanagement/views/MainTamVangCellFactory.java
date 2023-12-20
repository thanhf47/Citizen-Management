package com.example.citizenmanagement.views;

import com.example.citizenmanagement.controllers.maincontrollers.NhankhauController.TamVangCellController;
import com.example.citizenmanagement.controllers.maincontrollers.hoKhau.hoKhauCellControler;
import com.example.citizenmanagement.models.MainHoKhauCell;
import com.example.citizenmanagement.models.MainTamVangCell;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MainTamVangCellFactory extends ListCell<MainTamVangCell> {
    protected void updateItem(MainTamVangCell mainTamVangCell, boolean empty){
        super.updateItem(mainTamVangCell,empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main_citizen/hoKhau/TamVangCell.fxml"));
            //hoKhauCellControler HKcellControler = new hoKhauCellControler(mainHoKhauCell);
            loader.setController(new TamVangCellController(mainTamVangCell));
            setText(null);
            try {
                setGraphic(loader.load());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
