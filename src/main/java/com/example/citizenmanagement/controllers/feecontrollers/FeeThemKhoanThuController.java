package com.example.citizenmanagement.controllers.feecontrollers;

import com.example.citizenmanagement.models.FeeKhoanThuModel;
import com.example.citizenmanagement.models.FeeMenuOptions;
import com.example.citizenmanagement.models.Model;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FeeThemKhoanThuController implements Initializable {

    @FXML
    private ChoiceBox<String> bat_buoc;

    @FXML
    private TextArea mo_ta;

    @FXML
    private Button next_page_btn;

    @FXML
    private TextField so_tien_can_dong;

    @FXML
    private TextField ten_khoan_thu;
    private Alert alert;
    private ObservableList<String> choices = FXCollections.observableArrayList(
            "Không Bắt Buộc",
            "Bắt Buộc"
    );

    private int luaChon;
    @FXML
    private void onFeeCreateTiepBtn() {
        if(checkThongTin()) {
            Model.getInstance().getFeeKhoanThuModel().setFeeKhoanThuModel(
                    ten_khoan_thu.getText(),
                    luaChon, Long.parseLong(so_tien_can_dong.getText()),
                    LocalDate.now().toString(),
                    mo_ta.getText());
            Model.getInstance().getViewFactory().getFeeSelectedMenuItem().set(FeeMenuOptions.DANH_SACH_HO_KHAU_CAN_THU_PHI);

        }
    }

    public void onResetBtn() {
        ten_khoan_thu.setText("");
        bat_buoc.setValue(choices.get(0));
        so_tien_can_dong.setText("0");
        so_tien_can_dong.setDisable(true);
        mo_ta.setText("");
    }

    private boolean checkThongTin() {
        if (ten_khoan_thu.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng điền đủ thông tin Tên khoản thu!");
            alert.showAndWait();
            return false;
        }
        else if (luaChon == 1 && so_tien_can_dong.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng điền đủ thông tin Số tiền cần đóng trên một người!");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        luaChon = 0;
        so_tien_can_dong.setText("0");
        so_tien_can_dong.setDisable(true);
        bat_buoc.setItems(choices);
        bat_buoc.setValue(choices.get(0));

        bat_buoc.setOnAction(event -> {
            if (bat_buoc.getValue().equals(choices.get(0))) {
                luaChon = 0;
                so_tien_can_dong.setText("0");
                so_tien_can_dong.setDisable(true);
            }
            else if (bat_buoc.getValue().equals(choices.get(1))) {
                luaChon = 1;
                so_tien_can_dong.setText("");
                so_tien_can_dong.setDisable(false);
            }
        });



        FeeKhoanThuModel fee = Model.getInstance().getFeeKhoanThuModel();
        fee.getTenKhoanThu().addListener((observable, oldValue, newValue) -> {
            ten_khoan_thu.setText(newValue.toString());
        });
        fee.getBatBuoc().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() == 1) {
                bat_buoc.setValue(choices.get(1));
                so_tien_can_dong.setText("");
                so_tien_can_dong.setDisable(false);
            }
            else if (newValue.intValue() == 0) {
                bat_buoc.setValue(choices.get(0));
                so_tien_can_dong.setText("0");
                so_tien_can_dong.setDisable(true);
            }
        });
        fee.getMoTa().addListener((observable, oldValue, newValue) -> {
            mo_ta.setText(newValue.toString());
        });
    }


}
