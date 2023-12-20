package com.example.citizenmanagement.controllers.maincontrollers.hoKhau;

import com.example.citizenmanagement.models.thanh_vien_cua_ho_cell;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Text;


import java.net.URL;
import java.util.ResourceBundle;

public class thanh_vien_cua_ho_cell_controler implements Initializable {
    public Text ma_nhan_khau_text;
    public Text cccd_text;
    public Text ho_ten_text;
    public Text quan_he_text;
    public Text ngay_sinh_text;
    public Text gioi_tinh_text;

    public CheckBox check_box;
    private final thanh_vien_cua_ho_cell thanhVienCuaHoCell;
    public thanh_vien_cua_ho_cell_controler(thanh_vien_cua_ho_cell thanhVienCuaHoCell){
        this.thanhVienCuaHoCell = thanhVienCuaHoCell;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ma_nhan_khau_text.setText(thanhVienCuaHoCell.getmaNhanKhau());
        cccd_text.setText(String.valueOf(thanhVienCuaHoCell.getCccd()));
        ho_ten_text.setText(String.valueOf(thanhVienCuaHoCell.getHo_ten()));
        quan_he_text.setText(String.valueOf(thanhVienCuaHoCell.getQuan_he()));
        ngay_sinh_text.setText(String.valueOf(thanhVienCuaHoCell.getNgay_sinh()));
        gioi_tinh_text.setText(String.valueOf(thanhVienCuaHoCell.getGioi_tinh()));
        check_box.setSelected(thanhVienCuaHoCell.getCheck_box());
        check_box.setDisable(true);
    }
}
