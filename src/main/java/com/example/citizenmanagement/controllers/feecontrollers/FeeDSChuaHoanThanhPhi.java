package com.example.citizenmanagement.controllers.feecontrollers;

import com.example.citizenmanagement.models.*;
import com.example.citizenmanagement.views.FeeHoKhauCell2Factory;
import com.example.citizenmanagement.views.FeeHoKhauCellFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class FeeDSChuaHoanThanhPhi implements Initializable {

    @FXML
    private ListView<FeeHoKhauCell> listView;

    @FXML
    private Button quayLaiBtn;

    @FXML
    private TextField search_textfield;

    private Alert alert;
    private long soTienCanDong;
    @FXML
    void onQuayLaiBtn(ActionEvent event) {
        Model.getInstance().getViewFactory().getFeeSelectedMenuItem().set(FeeMenuOptions.CHI_TIET_KHOAN_THU);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initDanhSach(); // khởi tạo Model.getInstance().getDanhSachChuaDongPhi()
        showDanhSach();
        onSearch();
        Model.getInstance().getFeeKhoanThuModel().getMaKhoanThu().addListener((observable, oldValue, newValue) -> {
            initDanhSach();
            showDanhSach();
        });
        onItemClicked();
        listView.setCellFactory(param -> new FeeHoKhauCell2Factory());
    }

    private void onItemClicked() {
        listView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // double-click
                FeeHoKhauCell selectedItem = listView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {

                    if (Model.getInstance().getFeeKhoanThuModel().getBatBuoc().get() == 0) {
                        TextInputDialog dialog = new TextInputDialog();
                        dialog.setTitle("Nộp phí!");
                        dialog.setHeaderText(null);
                        dialog.setContentText("Nhập số tiền muốn nộp: \n");
                        Optional<String> result1 = dialog.showAndWait();
                        result1.ifPresent((soTien) -> {
                            alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation");
                            alert.setHeaderText(null);
                            alert.setContentText("Xác nhận nộp tiền?");

                            Optional<ButtonType> result2 = alert.showAndWait();
                            if (result2.get() == ButtonType.OK) {

                                Model.getInstance().getDatabaseConnection().updateNopPhi(selectedItem.getMaHoKhau(), Model.getInstance().getFeeKhoanThuModel().getMaKhoanThu().get(), soTien);

                                Model.getInstance().getDanhSachChuaDongPhi().remove(selectedItem);
                                showDanhSach();
                            }
                        });
                    }
                    else {
                        alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation");
                        alert.setHeaderText(null);
                        alert.setContentText("Xác nhận nộp tiền?");

                        Optional<ButtonType> result2 = alert.showAndWait();
                        if (result2.get() == ButtonType.OK) {

                            Model.getInstance().getDatabaseConnection().updateNopPhi(
                                    selectedItem.getMaHoKhau(),
                                    Model.getInstance().getFeeKhoanThuModel().getMaKhoanThu().get(),
                                    String.valueOf(soTienCanDong));
                            System.out.println(soTienCanDong);
                            Model.getInstance().getDanhSachChuaDongPhi().remove(selectedItem);
                            showDanhSach();
                        }
                    }
                }
            }

        });
    }

    private void onSearch() {
        search_textfield.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                showDanhSach();
            }
            else {
                ResultSet resultSet = Model.getInstance().getDatabaseConnection().danhSachChuaDongPhi_timKiem(
                        Model.getInstance().getFeeKhoanThuModel().getMaKhoanThu().get(),newValue);
                listView.getItems().clear();
                    try {
                        if (resultSet.isBeforeFirst()){
                            while (resultSet.next()) {
                                int maHoKhau = resultSet.getInt(1);
                                String tenChuHo = resultSet.getNString(2);
                                String diaChi = resultSet.getNString(3);
                                int soThanhVien = resultSet.getInt(4);
                                soTienCanDong = resultSet.getLong(5);
                                listView.getItems().add(new FeeHoKhauCell(maHoKhau, tenChuHo, diaChi, soThanhVien, soTienCanDong));
                            }
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

            }
        });
    }

    private void initDanhSach() {
        Model.getInstance().getDanhSachChuaDongPhi().clear();
        ResultSet resultSet = Model.getInstance().getDatabaseConnection().getDanhSachChuaDongPhi(Model.getInstance().getFeeKhoanThuModel().getMaKhoanThu().get());
        try {
            if(resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    int maHoKhau = resultSet.getInt(1);
                    String tenChuHo = resultSet.getNString(2);
                    String diaChi = resultSet.getNString(3);
                    int soThanhVien = resultSet.getInt(4);
                    soTienCanDong = resultSet.getLong(5);

                    Model.getInstance().getDanhSachChuaDongPhi().add(new FeeHoKhauCell(maHoKhau, tenChuHo, diaChi, soThanhVien, soTienCanDong));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void showDanhSach() {
        listView.getItems().clear();
        listView.getItems().addAll(Model.getInstance().getDanhSachChuaDongPhi());
    }
}
