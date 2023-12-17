package com.example.citizenmanagement.controllers.feecontrollers;

import com.example.citizenmanagement.models.FeeHoKhauCell;
import com.example.citizenmanagement.models.FeeMenuOptions;
import com.example.citizenmanagement.models.Model;
import com.example.citizenmanagement.views.FeeHoKhauCell2Factory;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class FeeDSHoanThanhPhiController implements Initializable {

    @FXML
    private ListView<FeeHoKhauCell> listView;

    @FXML
    private Button quayLaiBtn;

    @FXML
    private TextField search_textfield;

    private Alert alert;
    @FXML
    void onQuayLaiBtn() {
        Model.getInstance().getViewFactory().getFeeSelectedMenuItem().set(FeeMenuOptions.CHI_TIET_KHOAN_THU);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initDanhSach(); // khởi tạo Model.getInstance().getDanhSachDaDongPhi()
        showDanhSach();
        onSearch();
        Model.getInstance().getFeeKhoanThuModel().getMaKhoanThu().addListener((observable, oldValue, newValue) -> {
            initDanhSach();
            showDanhSach();
        });
        Model.getInstance().getDanhSachChuaDongPhi().addListener((ListChangeListener.Change<? extends FeeHoKhauCell> change) -> {
            while(change.next()) {
                if (change.wasRemoved()) {
                    initDanhSach();
                    showDanhSach();
                }
            }
        });
        onItemClicked();
        listView.setCellFactory(param -> new FeeHoKhauCell2Factory());
    }

    private void onItemClicked() {
        listView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // double-click
                FeeHoKhauCell selectedItem = listView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Ngày nộp phí: " + Model.getInstance().getDatabaseConnection().getNgayNopPhi(
                            selectedItem.getMaHoKhau(), Model.getInstance().getFeeKhoanThuModel().getMaKhoanThu().get()
                    ));
                    alert.showAndWait();
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
                ResultSet resultSet = Model.getInstance().getDatabaseConnection().danhSachDaDongPhi_timKiem(
                        Model.getInstance().getFeeKhoanThuModel().getMaKhoanThu().get(),newValue);
                listView.getItems().clear();
                try {
                    if (resultSet.isBeforeFirst()){
                        while (resultSet.next()) {
                            int maHoKhau = resultSet.getInt(1);
                            String tenChuHo = resultSet.getNString(2);
                            String diaChi = resultSet.getNString(3);
                            int soThanhVien = resultSet.getInt(4);
                            int soTienDaDong = soThanhVien * Model.getInstance().getFeeKhoanThuModel().getSoTienTrenMotNguoi().get();

                            listView.getItems().add(new FeeHoKhauCell(maHoKhau, tenChuHo, diaChi, soThanhVien, soTienDaDong));
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }

    private void initDanhSach() {
        Model.getInstance().getDanhSachDaDongPhi().clear();
        ResultSet resultSet = Model.getInstance().getDatabaseConnection().getDanhSachDaDongPhi(Model.getInstance().getFeeKhoanThuModel().getMaKhoanThu().get());
        try {
            if(resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    int maHoKhau = resultSet.getInt(1);
                    String tenChuHo = resultSet.getNString(2);
                    String diaChi = resultSet.getNString(3);
                    int soThanhVien = resultSet.getInt(4);
                    int soTienCanDong = soThanhVien * Model.getInstance().getFeeKhoanThuModel().getSoTienTrenMotNguoi().get();

                    Model.getInstance().getDanhSachDaDongPhi().add(new FeeHoKhauCell(maHoKhau, tenChuHo, diaChi, soThanhVien, soTienCanDong));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void showDanhSach() {
        listView.getItems().clear();
        listView.getItems().addAll(Model.getInstance().getDanhSachDaDongPhi());
    }
}
