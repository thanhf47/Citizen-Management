package com.example.citizenmanagement.views;

import com.example.citizenmanagement.controllers.feecontrollers.FeeHokhauCellController;
import com.example.citizenmanagement.models.FeeHoKhauCell;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class FeeHoKhauCellFactory extends ListCell<FeeHoKhauCell> {
    @Override
    public void updateItem(FeeHoKhauCell feeHoKhauCell, boolean empty) {
        super.updateItem(feeHoKhauCell, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        }
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fee/FeeHoKhauCell.fxml"));
            loader.setController(new FeeHokhauCellController(feeHoKhauCell));
            setText(null);
            try {
                setGraphic(loader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
