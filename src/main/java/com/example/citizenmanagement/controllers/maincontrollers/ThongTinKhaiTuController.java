package com.example.citizenmanagement.controllers.maincontrollers;

import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ThongTinKhaiTuController implements Initializable{

    @FXML
    private Button chinh_sua_btn;

    @FXML
    private Button confirm_btn;

    @FXML
    private TextField dan_toc_text;

    @FXML
    private TextField gioi_tinh_text;

    @FXML
    private TextField ho_ten_nguoi_khai;

    @FXML
    private TextField ho_ten_nguoi_mat;

    @FXML
    private TextArea ly_do;

    @FXML
    private Text maGiayKhaiTu_text;

    @FXML
    private DatePicker ngay_chet;

    @FXML
    private DatePicker ngay_khai;

    @FXML
    private DatePicker ngay_sinh;

    @FXML
    private TextField nguyen_quan_text;

    @FXML
    private TextField quoc_tich_text;

    @FXML
    private TextField soNK_nguoi_khai;

    @FXML
    private TextField soNK_nguoi_mat;

    @FXML
    private TextField so_can_cuoc_text;

    @FXML
    private Button thoat_btn;

    @FXML
    private TextField thuong_tru_text;

    private Alert alert;
    @FXML
    private void onThoatBtn() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.QUA_DOI);
    }
    @FXML
    private void onChinhSuaBtn() {
        chinh_sua_btn.setVisible(false);
        confirm_btn.setVisible(true);

        ngay_khai.setDisable(false);
        ngay_chet.setDisable(false);
        ly_do.setDisable(false);
    }
    @FXML
    private void onXacNhanBtn() {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation dialog");
        alert.setHeaderText(null);
        alert.setContentText("Ông chắc chưa?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            Model.getInstance().getDatabaseConnection().updateThongTinKhaiTu(
                    maGiayKhaiTu_text.getText(),
                    ngay_khai.getValue().toString(),
                    ngay_chet.getValue().toString(),
                    ly_do.getText());
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Chỉnh sửa thành công!");
            alert.showAndWait();

            Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.QUA_DOI);
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ResultSet resultSet = Model.getInstance().getDatabaseConnection().getThongTinKhaiTu(Model.getNhanKhauDuocChon().getSo_nhan_khau());

        try {
            if(resultSet.isBeforeFirst()) {
                maGiayKhaiTu_text.setDisable(true);
                resultSet.next();
                maGiayKhaiTu_text.setText(resultSet.getString(1));
                soNK_nguoi_khai.setText(resultSet.getString(2));
                ho_ten_nguoi_khai.setText(resultSet.getNString(3));
                soNK_nguoi_mat.setText(resultSet.getString(4));
                ho_ten_nguoi_mat.setText(resultSet.getNString(5));
                so_can_cuoc_text.setText(resultSet.getString(6));
                ngay_sinh.setValue(resultSet.getDate(7).toLocalDate());
                if(resultSet.getInt(8) == 1) gioi_tinh_text.setText("Nam");
                else gioi_tinh_text.setText("Nữ");
                dan_toc_text.setText(resultSet.getNString(9));
                quoc_tich_text.setText(resultSet.getNString(10));
                nguyen_quan_text.setText(resultSet.getNString(11));
                thuong_tru_text.setText(resultSet.getNString(12));
                if (resultSet.getDate(13) != null)
                    ngay_khai.setValue(resultSet.getDate(13).toLocalDate());
                if(resultSet.getDate(14) != null)
                    ngay_chet.setValue(resultSet.getDate(14).toLocalDate());
                ly_do.setText(resultSet.getNString(15));
                ly_do.setWrapText(true);

                soNK_nguoi_khai.setDisable(true); ho_ten_nguoi_khai.setDisable(true);
                soNK_nguoi_mat.setDisable(true); ho_ten_nguoi_mat.setDisable(true);
                so_can_cuoc_text.setDisable(true);
                ngay_sinh.setDisable(true); gioi_tinh_text.setDisable(true);
                dan_toc_text.setDisable(true); quoc_tich_text.setDisable(true);
                nguyen_quan_text.setDisable(true); thuong_tru_text.setDisable(true);
                ngay_khai.setDisable(true); ngay_chet.setDisable(true);
                ly_do.setDisable(true);

                confirm_btn.setVisible(false);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
