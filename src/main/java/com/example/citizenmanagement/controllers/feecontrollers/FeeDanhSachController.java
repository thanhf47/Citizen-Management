package com.example.citizenmanagement.controllers.feecontrollers;

import com.example.citizenmanagement.models.FeeHoKhauCell;
import com.example.citizenmanagement.models.FeeKhoanThuCell;
import com.example.citizenmanagement.models.Model;
import com.example.citizenmanagement.views.FeeKhoanThuCellFactory;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class FeeDanhSachController implements Initializable {

    @FXML
    private ListView<FeeKhoanThuCell> listView;

    @FXML
    private TextField search_textfield;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        initDanhSach();
        onSearch();
        Model.getInstance().getDanhSachKhoanThu().addListener((ListChangeListener.Change<? extends FeeKhoanThuCell> change) -> {
            while (change.next()){
                initDanhSach();
            }
        });

        listView.setCellFactory(param -> new FeeKhoanThuCellFactory());
    }

    private void onSearch() {
        search_textfield.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                initDanhSach();
            }
            else {
                ResultSet resultSet = Model.getInstance().getDatabaseConnection().danhSachKhoanThu_timKiem(newValue);
                listView.getItems().clear();
                try {
                    if (resultSet.isBeforeFirst()) {
                        while (resultSet.next()) {
                            int maKhoanThu = resultSet.getInt(1);
                            String tenKhoanThu = resultSet.getNString(2);
                            int batBuoc = resultSet.getInt(3);
                            int soTienCanDong = resultSet.getInt(4);
                            String ngayTao = resultSet.getString(5);

                            listView.getItems().add(new FeeKhoanThuCell(maKhoanThu, tenKhoanThu, batBuoc, soTienCanDong, ngayTao));
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }

    private void initDanhSach() {
        listView.getItems().clear();
        listView.getItems().addAll(Model.getInstance().getDanhSachKhoanThu());

    }
}
