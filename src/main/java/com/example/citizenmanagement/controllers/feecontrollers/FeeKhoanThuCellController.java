package com.example.citizenmanagement.controllers.feecontrollers;

import com.example.citizenmanagement.models.FeeHoKhauCell;
import com.example.citizenmanagement.models.FeeKhoanThuCell;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class FeeKhoanThuCellController implements Initializable {

    @FXML
    private Text bat_buoc;

    @FXML
    private Text ma_khoan_thu;

    @FXML
    private Text ngay_tao;

    @FXML
    private Text so_tien_can_dong;

    @FXML
    private Text ten_khoan_thu;

    private FeeKhoanThuCell feeKhoanThuCell;

    public FeeKhoanThuCellController(FeeKhoanThuCell feeKhoanThuCell) {this.feeKhoanThuCell = feeKhoanThuCell;}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ma_khoan_thu.setText(String.valueOf(feeKhoanThuCell.getMaKhoanThu()));
        ten_khoan_thu.setText(feeKhoanThuCell.getTenKhoanThu());
        if(feeKhoanThuCell.getBatBuoc() == 0) {
            bat_buoc.setText("Không");
        }
        else bat_buoc.setText("Có");
        so_tien_can_dong.setText(String.valueOf(feeKhoanThuCell.getSoTienCanDong()));
        ngay_tao.setText(feeKhoanThuCell.getNgayTao());
    }
}
