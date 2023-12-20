package com.example.citizenmanagement.views;


import com.example.citizenmanagement.controllers.maincontrollers.hoKhau.thanh_vien_cua_ho_cell_controler;

import com.example.citizenmanagement.models.thanh_vien_cua_ho_cell;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class thanh_vien_cua_ho_cell_factory extends ListCell<thanh_vien_cua_ho_cell> {
    @Override
    protected void updateItem(thanh_vien_cua_ho_cell thanhVienCuaHoCell, boolean empty){
        super.updateItem(thanhVienCuaHoCell,empty);
        if(empty){
            setText(null);
            setGraphic(null);

        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main_citizen/hoKhau/thanh_vien_cua_ho_cell.fxml"));
            loader.setController(new thanh_vien_cua_ho_cell_controler(thanhVienCuaHoCell));
            setText(null);
            try {
                setGraphic(loader.load());
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
