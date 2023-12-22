package com.example.citizenmanagement.controllers.maincontrollers.NhankhauController;

import com.example.citizenmanagement.models.List_nhan_khau;
import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class NhanKhauShowController implements Initializable {

    public TextField ton_giao_text;
    public TextField nghe_text;
    public TextField quoc_tich_text;
    public TextField nam_sinh_text;
    public ChoiceBox my_choise_box;
    public TextField nguyen_quan_text;
    public TextField thuong_tru_text;
    public TextField cccd_text;
    public Button confirm_chinh_sua_btn;
    public Button thoat_chinhsua_button;
    public TextField dan_toc_text;
    public TextField ghi_chu_text;
    public TextField ho_ten_text;
    public TextField so_ho_chieu_text;
    public TextField noi_sinh_text;
    public Button tam_vang_btn;
    public Button khai_tu_btn;
    public DatePicker ngay_tao_date;

    private List_nhan_khau list ;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       chitiet();
//       Model.getInstance().getLuuTruNhanKhauShowModel().getCCCD().

      tam_vang_btn.setOnAction(actionEvent ->

    onTamvang());
      khai_tu_btn.setOnAction(actionEvent ->

    onKhaitu());
      thoat_chinhsua_button.setOnAction(event ->
    {
        onThoatTamVangBtn();
        reset();
    });
}

    private void reset() {
        ho_ten_text.clear();

    }
    private void onTamvang() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.TAM_VANG);
    }
    private void onThoatTamVangBtn() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.NHAN_KHAU);
    }
    private void onKhaitu() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.KHAI_TU);
    }

    private void chitiet() {
        list = Model.getNhanKhauDuocChon();
        ResultSet resultSet = Model.getInstance().getDatabaseConnection().truyvanlistNhanKhau(list.getCccd());
        try {
            if (resultSet.next()) {
                String value = resultSet.getString(1);
                ho_ten_text.setText(value);
                cccd_text.setText(resultSet.getString(2));
                String gender = resultSet.getString(4);
                if (gender.equals("1")) {
                    my_choise_box.setValue("Nam");
                } else if (gender.equals("0")) {
                    my_choise_box.setValue("Nữ");
                }
                nam_sinh_text.setText(resultSet.getString(3));
                noi_sinh_text.setText(resultSet.getString(5));
                nguyen_quan_text.setText(resultSet.getString(6));
                dan_toc_text.setText(resultSet.getString(7));
                ton_giao_text.setText(resultSet.getString(8));
                quoc_tich_text.setText(resultSet.getString(9));
                so_ho_chieu_text.setText(resultSet.getString(10));
                thuong_tru_text.setText(resultSet.getString(11));
                nghe_text.setText(resultSet.getString(12));
                ghi_chu_text.setText(resultSet.getString(14));
                Date sqlDate = resultSet.getDate(13);
                LocalDate localDate = null;
                if (sqlDate != null) {
                    localDate = sqlDate.toLocalDate();
                }
                ngay_tao_date.setValue(localDate);



            } else {
                // Xử lý khi không có bản ghi trong resultSet
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
