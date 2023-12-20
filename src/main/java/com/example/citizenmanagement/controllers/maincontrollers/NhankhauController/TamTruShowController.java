package com.example.citizenmanagement.controllers.maincontrollers.NhankhauController;

import com.example.citizenmanagement.models.List_nhan_khau;
import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class TamTruShowController implements Initializable {

    public TextField ton_giao_text;
    public TextField nghe_text;
    public TextField quoc_tich_text;

    public ChoiceBox<String> my_choise_box;
    public TextField nguyen_quan_text;
    public TextField thuong_tru_text;
    public TextField cccd_text;
    public Button confirm_chinh_sua_btn;
    public Button thoat_chinhsua_button;
    public TextField dan_toc_text;
    public TextField ghi_chu_text;
    public TextField ho_ten_text;

    public TextField noi_sinh_text;
    public Button khai_tu_btn;
    public DatePicker ngay_tao_date;
    public DatePicker ngay_sinh_lbl;
//    public Button tam_vang_btn;

    private List_nhan_khau list1 ;

    private String[] Gioitinh = {"Nam", "Nữ"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        my_choise_box.setItems(FXCollections.observableArrayList(Gioitinh));

        chitiet();
        khai_tu_btn.setOnAction(actionEvent ->
                onKhaitu());
        thoat_chinhsua_button.setOnAction(event ->
        {
            onThoatTamVangBtn();
        });
        confirm_chinh_sua_btn.setOnAction(event -> {
//          Model.getInstance().getDatabaseConnection().capnhatNhanKhau()
        });
    }


    private void onTamvang() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.TAM_VANG);
    }
    private void onThoatTamVangBtn() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.TAM_TRU_LIST);
    }
    private void onKhaitu() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.KHAI_TU);
    }

    private void chitiet() {
        list1 = Model.getNhanKhauDuocChon();
        ResultSet resultSet = Model.getInstance().getDatabaseConnection().truyvanlistNhanKhau(list1.getCccd());
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
                Date ngay_sinh = resultSet.getDate(3);
                ngay_sinh_lbl.setValue(ngay_sinh.toLocalDate());
                noi_sinh_text.setText(resultSet.getString(5));
                nguyen_quan_text.setText(resultSet.getString(6));
                dan_toc_text.setText(resultSet.getString(7));
                ton_giao_text.setText(resultSet.getString(8));
                quoc_tich_text.setText(resultSet.getString(9));
                thuong_tru_text.setText(resultSet.getString(10));
                nghe_text.setText(resultSet.getString(11));
                ghi_chu_text.setText(resultSet.getString(13));
                Date sqlDate = resultSet.getDate(12);
                LocalDate localDate = null;
                if (sqlDate != null) {
                    localDate = sqlDate.toLocalDate();
                }
                ngay_tao_date.setValue(localDate);
            } else {
                System.out.println("Lỗi rồi!!!");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
