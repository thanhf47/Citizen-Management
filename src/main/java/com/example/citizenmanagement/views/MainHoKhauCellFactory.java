package com.example.citizenmanagement.views;

import com.example.citizenmanagement.controllers.maincontrollers.hoKhau.hoKhauCellControler;
import com.example.citizenmanagement.models.MainHoKhauCell;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class MainHoKhauCellFactory extends ListCell<MainHoKhauCell> {
    @Override
    protected void updateItem(MainHoKhauCell mainHoKhauCell, boolean empty){
        super.updateItem(mainHoKhauCell,empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main_citizen/hoKhau/hoKhauCell.fxml"));
            //hoKhauCellControler HKcellControler = new hoKhauCellControler(mainHoKhauCell);
            loader.setController(new hoKhauCellControler(mainHoKhauCell));
            setText(null);
            try {
                setGraphic(loader.load());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
