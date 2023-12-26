package com.example.citizenmanagement.controllers.maincontrollers.NhankhauController;

import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import com.example.citizenmanagement.models.luuTruNhanKhau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DKTamTru2Controller implements Initializable {
    public Button thoat_btn_2;
    public Button confirm_btn;

    public TextField matamtru_text;

    public DatePicker ngayden_text;
    public DatePicker ngaydi_text;
    @FXML
    public TextArea lido_text;
    int bit;
    private static luuTruNhanKhau luuTruNhanKhau;
    private void onThoattamtru2() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.TAM_TRU_LIST);
    }
    public static void setFormData(luuTruNhanKhau tam) {
        luuTruNhanKhau = tam;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DKTamTru2Controller tamTru2Controller = new DKTamTru2Controller();

        thoat_btn_2.setOnAction(actionEvent -> {
            onThoattamtru2();
        });
        confirm_btn.setOnAction(actionEvent -> {
            if (luuTruNhanKhau.getGioi_tinh() == "Nam") {
                bit = 1;
            } else bit = 0;

//                LocalDate ngay_sinh = luuTruNhanKhau.getNam_sinh();
//                Date ngaysinh = Date.valueOf(ngay_sinh);

            if(luuTruNhanKhau.getHo_ten().isEmpty() || luuTruNhanKhau.getGioi_tinh().isEmpty() || luuTruNhanKhau.getNam_sinh() == null || luuTruNhanKhau.getNoi_sinh().isEmpty() || luuTruNhanKhau.getNguyen_quan().isEmpty() || luuTruNhanKhau.getDan_toc().isEmpty() || luuTruNhanKhau.getTon_giao().isEmpty() || luuTruNhanKhau.getQuoc_tich().isEmpty() || matamtru_text.getText().isEmpty() || ngayden_text.getValue() == null || ngaydi_text.getValue() == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText("Không Thành công");
                alert.setContentText("Vui lòng điền đầy đủ thông tin bắt buộc !!!");
                alert.showAndWait();
            }
            else {
                int thanhcong = Model.getInstance().getDatabaseConnection().addTamtru(luuTruNhanKhau.getHo_ten(), luuTruNhanKhau.getCCCD(), luuTruNhanKhau.getNam_sinh().toString(), bit, luuTruNhanKhau.getNoi_sinh(), luuTruNhanKhau.getNguyen_quan(), luuTruNhanKhau.getDan_toc(), luuTruNhanKhau.getTon_giao(), luuTruNhanKhau.getQuoc_tich(), luuTruNhanKhau.getThuong_tru(), luuTruNhanKhau.getNghe_nghiep(), matamtru_text.getText(), Date.valueOf(ngayden_text.getValue()), Date.valueOf(ngaydi_text.getValue()), lido_text.getText());
//          int thanhcong1 = Model.getInstance().getDatabaseConnection().capnhatNhanKhau(luuTruNhanKhau.getCCCD());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thành công");
                alert.setContentText("Đã thêm thành công người này!");
                alert.showAndWait();
                Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.TAM_TRU_LIST);
                if (thanhcong == 0 /*&& thanhcong1 == 0 */) {
                    System.out.println("Đã thêm không thành công");
                }
            }

        });
    }
    private void setDkTamTru1Controller(DkTamTruController dkTamTru1Controller) {
    }
}
