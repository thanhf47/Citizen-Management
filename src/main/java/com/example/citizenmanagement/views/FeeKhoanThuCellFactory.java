package com.example.citizenmanagement.views;

import com.example.citizenmanagement.controllers.feecontrollers.FeeHokhauCellController;
import com.example.citizenmanagement.controllers.feecontrollers.FeeKhoanThuCellController;
import com.example.citizenmanagement.models.FeeHoKhauCell;
import com.example.citizenmanagement.models.FeeKhoanThuCell;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class FeeKhoanThuCellFactory extends ListCell<FeeKhoanThuCell> {
    @Override
    public void updateItem(FeeKhoanThuCell feeKhoanThuCell, boolean empty) {
        super.updateItem(feeKhoanThuCell, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        }
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fee/FeeKhoanThuCell.fxml"));
            loader.setController(new FeeKhoanThuCellController(feeKhoanThuCell));

            setText(null);
            try {
                setGraphic(loader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
