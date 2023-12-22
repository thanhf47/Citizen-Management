package com.example.citizenmanagement.views;

import com.example.citizenmanagement.controllers.maincontrollers.NhankhauController.NhanKhauCellController;
import com.example.citizenmanagement.models.List_nhan_khau;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class List_nhan_khau_factory extends ListCell<List_nhan_khau> {
    protected void updateItem(List_nhan_khau list_nhan_khau, boolean empty) {
        super.updateItem(list_nhan_khau, empty);
        if(empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main_citizen/HoKhau/NhanKhauCell.fxml"));
            NhanKhauCellController controller =new NhanKhauCellController(list_nhan_khau);
            loader.setController(controller);
            setText(null);
            try{
                setGraphic(loader.load());
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
