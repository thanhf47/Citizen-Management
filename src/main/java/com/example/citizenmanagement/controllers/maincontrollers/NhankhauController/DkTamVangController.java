package com.example.citizenmanagement.controllers.maincontrollers.NhankhauController;

import com.example.citizenmanagement.models.List_nhan_khau;
import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DkTamVangController implements Initializable {


    @FXML
    private TextField li_do;

    @FXML
    private TextField ma_nhan_khau;

    @FXML
    private DatePicker ngay_bat_dau;

    @FXML
    private DatePicker ngay_ket_thuc;

    @FXML
    private TextField noi_tam_tru;

    @FXML
    private Button thoat_btn;

    @FXML
    private Button xac_nhan_tam_vang;
    private List_nhan_khau tam;


    private void onThoatBtn() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.TAM_VANG);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        thoat_btn.setOnAction(event -> onThoatBtn());
        xac_nhan_tam_vang.setOnAction(event -> onDangKyTamVang());

        tam = Model.getNhanKhauDuocChon();
       ma_nhan_khau.setText(tam.getSo_nhan_khau());
       ma_nhan_khau.setDisable(true);
    }

    public void onDangKyTamVang(){
        if(ma_nhan_khau.getText().isEmpty() || li_do.getText().isEmpty() || ngay_bat_dau.getValue() == null || ngay_ket_thuc.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Bạn chưa nhập đủ thông tin !");
            alert.setContentText("Xin hãy kiểm tra lại !");
            alert.showAndWait();
        }

        else if(Model.getInstance().KiemTraXemMaNhanKhauDaTonTaiTrongTamVang(Integer.valueOf(ma_nhan_khau.getText()))){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Không thể đăng kí tạm vắng !");
            alert.setContentText("Nhân khẩu này đã đăng kí tạm vắng trước đó");
            alert.showAndWait();
        }
        else if(!Model.getInstance().KiemTraMaNhanKhauCoTonTaiHayKhong(Integer.valueOf(ma_nhan_khau.getText()))){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText("Không thể đăng kí tạm vắng !");
            alert.setContentText("Nhân khẩu này không tồn tại");
            alert.showAndWait();
        }

        else {
            int idNhanKhau = Integer.parseInt(ma_nhan_khau.getText());
            String lido = li_do.getText();
            String noitamtru = noi_tam_tru.getText();
            String ngayBD = String.valueOf(ngay_bat_dau.getValue());
            String ngayKT = String.valueOf(ngay_ket_thuc.getValue());
            if(Model.getInstance().getDatabaseConnection().dangKiTamVang(idNhanKhau,ngayBD,ngayKT,lido,noitamtru)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Bạn đã thêm thành công");
                alert.setContentText("Mời bạn trở về trang chủ");
                alert.showAndWait();

//                Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.TRANG_CHU_TAM_VANG);

                Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.NHAN_KHAU);

            }
        }
    }
}

