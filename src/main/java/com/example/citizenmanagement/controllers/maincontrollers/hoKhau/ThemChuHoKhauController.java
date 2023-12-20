package com.example.citizenmanagement.controllers.maincontrollers.hoKhau;

import com.example.citizenmanagement.models.List_nhan_khau;
import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import com.example.citizenmanagement.views.List_nhan_khau_factory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

public class ThemChuHoKhauController implements Initializable {

    @FXML
    private ListView<List_nhan_khau> listView;

    @FXML
    private TextField search_text;

    private Alert alert;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initDanhSach();

        search_text.textProperty().addListener((observableValue, oldval, newval) -> {
            if(newval.isEmpty()){
                initDanhSach();
            }
            else {
                ResultSet resultSet1 = Model.getInstance().getDatabaseConnection().nhanKhau_timkiem_chua_co_nha(search_text.getText());
                listView.getItems().clear();
                try {
                    if(resultSet1.isBeforeFirst()) {
                        while(resultSet1.next()) {
                            String ma_nhan_khau=resultSet1.getString(1);
                            String cccd = resultSet1.getString(2);
                            String hoTen = resultSet1.getString(3);
                            String gioitinh = resultSet1.getString(4);
                            String namsinh = resultSet1.getString(5);
                            String diachi = resultSet1.getString(6);

                            listView.getItems().add(new List_nhan_khau(ma_nhan_khau,cccd, hoTen, gioitinh, namsinh, diachi));
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        listView.setCellFactory(param ->new List_nhan_khau_factory());


        listView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Confirm Alert");
                alert.setContentText("Chọn người này làm chủ hộ khẩu mới?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    Model.setNhanKhauDuocChon(listView.getSelectionModel().getSelectedItem());
                    Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.THEM_THANH_VIEN_HO_KHAU);
                }
            }
        });
    }

    private void initDanhSach() {
        ResultSet resultSet = Model.getInstance().getDatabaseConnection().truyvan_chua_co_nha();
        listView.getItems().clear();
        try{
            if(resultSet.isBeforeFirst()){
                while (resultSet.next()) {
                    String ma_nhan_khau=resultSet.getString(1);
                    String id = resultSet.getString(2);
                    String hoten = resultSet.getNString(3);
                    String gioitinh = resultSet.getString(4);
                    String namsinh = resultSet.getString(5);
                    String thuongtru;
                    if(resultSet.getNString(6)==null) thuongtru="không có";
                    else thuongtru = resultSet.getNString(6);
                    listView.getItems().add(new List_nhan_khau(ma_nhan_khau,id, hoten, gioitinh, namsinh, thuongtru));
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
