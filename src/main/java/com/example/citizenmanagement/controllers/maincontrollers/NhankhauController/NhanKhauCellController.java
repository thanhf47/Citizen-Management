package com.example.citizenmanagement.controllers.maincontrollers.NhankhauController;

import com.example.citizenmanagement.models.List_nhan_khau;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class NhanKhauCellController implements Initializable {
    public Label So_nhan_khau_lbl;
    public Label cccd_lbl;
    public Label ten_lbl;
    public Label gioi_tinh_lbl;
    public Label ngay_sinh_lbl;
    public Label dia_chi_lbl;

    private final List_nhan_khau list_nhan_khau;

    public NhanKhauCellController(List_nhan_khau list_nhan_khau) {
        this.list_nhan_khau = list_nhan_khau;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         So_nhan_khau_lbl.setText(String.valueOf(list_nhan_khau.getSo_nhan_khau()));
         cccd_lbl.setText(String.valueOf(list_nhan_khau.getCccd()));
         ten_lbl.setText(String.valueOf(list_nhan_khau.getHoten()));
        if(  list_nhan_khau.getGioi_tinh().equals("1") ) {
            gioi_tinh_lbl.setText("Nam");
        }
        else {
            gioi_tinh_lbl.setText("Nữ");
        }
        ngay_sinh_lbl.setText(String.valueOf(list_nhan_khau.getNgay_sinh()));
        dia_chi_lbl.setText(String.valueOf(list_nhan_khau.getDia_chi()));
    }


}
