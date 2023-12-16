package com.example.citizenmanagement.controllers.maincontrollers.NhankhauController;

import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DkTamTruController implements Initializable {

    private String[] Gioitinh = {"Nam", "Nữ"};
    public Button thoat_tamtru_btn;
    public Button tiep_tuc_btn;
    public TextField CCCD_text;
    public TextField hoten_text;
    public ChoiceBox<String> gioitinh_text;
    public TextField nam_sinh_text;
    public TextField noi_sinh_text;
    public TextField nguyen_quan_text;
    public TextField thuong_tru_text;
    public TextField dantoc_text;
    public TextField tongiao_text;
    public TextField quoctich_text;
    public TextField hochieu_text;
    public TextField nghenghiep_text;

    public TextField getCCCD_text() {
        return CCCD_text;
    }

    public TextField getHoten_text() {
        return hoten_text;
    }

    public ChoiceBox getGioitinh_text() {
        return gioitinh_text;
    }

    public TextField getNam_sinh_text() {
        return nam_sinh_text;
    }

    public TextField getNoi_sinh_text() {
        return noi_sinh_text;
    }

    public TextField getNguyen_quan_text() {
        return nguyen_quan_text;
    }

    public TextField getThuong_tru_text() {
        return thuong_tru_text;
    }

    public TextField getDantoc_text() {
        return dantoc_text;
    }

    public TextField getTongiao_text() {
        return tongiao_text;
    }

    public TextField getQuoctich_text() {
        return quoctich_text;
    }

    public TextField getHochieu_text() {
        return hochieu_text;
    }

    public TextField getNghenghiep_text() {
        return nghenghiep_text;
    }

    private DKTamTru2Controller dkTamTru2Controller;

//    public void setDkTamTru2Controller(DKTamTru2Controller dkTamTru2Controller) {
//        this.dkTamTru2Controller = dkTamTru2Controller;
//    }

    private void onThoattamtru() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.NHAN_KHAU);
    }
    int bit;
    private void onTieptucTamtru() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.TAM_TRU_2);

        if(gioitinh_text.getValue() == "Nam") {
            bit = 1;
        }
        else bit = 0;
    }

        public int bits() {
        return bit;
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        thoat_tamtru_btn.setOnAction(event -> {
            onThoattamtru();
        });

        tiep_tuc_btn.setOnAction(actionEvent -> {
            onTieptucTamtru();
        });

        gioitinh_text.getItems().addAll(Gioitinh);
        gioitinh_text.setOnAction(this::getGioiTinh);
    }
    public String getGioiTinh(ActionEvent event) {
        String myGioiTinh = gioitinh_text.getValue();

        return myGioiTinh;
}


}
