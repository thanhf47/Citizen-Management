package com.example.citizenmanagement.views;

import com.example.citizenmanagement.controllers.maincontrollers.hoKhau.hoKhauCellControler;
import com.example.citizenmanagement.models.hoKhauCell;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class hoKhauCellFactory extends ListCell<hoKhauCell> {
    @Override
    protected void updateItem(hoKhauCell khauCell, boolean empty){
        super.updateItem(khauCell,empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main_citizen/hoKhau/hoKhauCell.fxml"));
            hoKhauCellControler HKcellControler = new hoKhauCellControler(khauCell);
            loader.setController(HKcellControler);
            setText(null);
            try {
                setGraphic(loader.load());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
