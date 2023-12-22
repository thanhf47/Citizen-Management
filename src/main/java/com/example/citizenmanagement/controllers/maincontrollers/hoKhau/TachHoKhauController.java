package com.example.citizenmanagement.controllers.maincontrollers.hoKhau;

import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import com.example.citizenmanagement.models.thanh_vien_cua_ho_cell;
import com.example.citizenmanagement.views.thanh_vien_cua_ho_cell_factory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class TachHoKhauController implements Initializable {
    @FXML
    private Button cancel_but;

    @FXML
    private Button chuyen_lai;

    @FXML
    private Button chuyen_sang;

    @FXML
    private TextField dia_chi;

    @FXML
    private TextField ghi_chu;

    @FXML
    private TextField id_chu_cu;

    @FXML
    private TextField id_chu_moi;

    @FXML
    private ListView<thanh_vien_cua_ho_cell> new_list;

    @FXML
    private ListView<thanh_vien_cua_ho_cell> old_list;

    @FXML
    private TextField quan_he_textField;


    @FXML
    private Button xacNhan_but;

    private thanh_vien_cua_ho_cell thanhVien = null;
    private ArrayList<thanh_vien_cua_ho_cell> list_thanhVien = new ArrayList<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        id_chu_cu.setText(
                Model.getInstance().getDatabaseConnection().lay_chu_ho(Model.getHoKhauDuocChon().getId().get()));
        id_chu_cu.setDisable(true);

        init_old_list();

        chuyen_sang.setOnAction(event -> {
            if (!quan_he_textField.getText().isEmpty()) {
                thanhVien = old_list.getSelectionModel().getSelectedItem();
                if (thanhVien != null) {
                    boolean chuaChet = Model.getInstance().getDatabaseConnection().checkKhaiTu(thanhVien.getmaNhanKhau());
                    if(chuaChet) {
                        thanhVien.setQuan_he(quan_he_textField.getText());
                        old_list.getItems().remove(thanhVien);
                        new_list.getItems().add(thanhVien);

                        thanhVien = null;
                        quan_he_textField.setText("");
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("Người em chọn chết rồi, chọn thằng khác đi.");
                        alert.setTitle("Cảnh báo");
                        alert.setHeaderText(null);
                        alert.showAndWait();
                    }
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setContentText("Bạn chưa chọn người chuyển xuống");
                    alert.setTitle("Cảnh báo");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Cảnh báo");
                alert.setContentText("Bạn chưa nhập quan hệ với chủ hộ");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        });

        chuyen_lai.setOnAction(event -> {
            if(!quan_he_textField.getText().isEmpty()){
                thanhVien = new_list.getSelectionModel().getSelectedItem();
                if(thanhVien != null) {
                    thanhVien.setQuan_he(quan_he_textField.getText());
                    old_list.getItems().add(thanhVien);
                    new_list.getItems().remove(thanhVien);

                    thanhVien = null;
                    quan_he_textField.setText("");
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Cảnh báo");
                    alert.setContentText("Bạn chưa chọn người chuyển lên");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Cảnh báo");
                alert.setContentText("Bạn chưa nhập quan hệ với chủ hộ");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
        });


        cancel_but.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Xác nhận");
            alert.setContentText("Bạn chắc chắn muốn hủy chứ!");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.XEM_CHI_TIET_HO_KHAU);
            }
        });


        xacNhan_but.setOnAction(event -> {
            if (!dia_chi.getText().isEmpty()) {
                if (kiem_tra_chu_ho_moi()) {
                    Model.getInstance().getDatabaseConnection().xoaThanhVienCuaHo(id_chu_moi.getText());
                    Model.getInstance().getDatabaseConnection().addHoKhau(id_chu_moi.getText(), dia_chi.getText(), ghi_chu.getText());

                    String maHoKhauMoi;
                    ResultSet resultSet = Model.getInstance().getDatabaseConnection().getMaHoKhau(id_chu_moi.getText());
                    try {
                        resultSet.next();
                        maHoKhauMoi = resultSet.getString(1);
                        System.out.println(maHoKhauMoi);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }



                    for (thanh_vien_cua_ho_cell item : new_list.getItems()) {
                        Model.getInstance().getDatabaseConnection().xoaThanhVienCuaHo(item.getmaNhanKhau());

//                        if(!item.getmaNhanKhau().equals(id_chu_moi.getText()))
                            Model.getInstance().getDatabaseConnection().add_thanh_vien_cua_ho(item.getmaNhanKhau(), maHoKhauMoi, item.getQuan_he());
                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Thông báo");
                    alert.setContentText("Bạn đã tách thành công");
                    alert.showAndWait();
                    Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.HO_KHAU);
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Thông báo lỗi");
                    alert.setContentText("Mã chủ hộ mới không hợp lệ!");
                    alert.showAndWait();
                }

            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Thông báo lỗi");
                alert.setContentText("Bạn chưa nhập thông tin địa chỉ");
                alert.showAndWait();
            }
        });
        old_list.setCellFactory(param-> new thanh_vien_cua_ho_cell_factory());
        new_list.setCellFactory(param -> new thanh_vien_cua_ho_cell_factory());
    }

    private boolean kiem_tra_chu_ho_moi() {
        for (thanh_vien_cua_ho_cell item: new_list.getItems()) {
            if (item.getmaNhanKhau().equals(id_chu_moi.getText()))
                return true;
        }
        return false;
    }

    private void init_old_list() {
        ResultSet resultSet = Model.getInstance().getDatabaseConnection().lay_cac_thanh_vien(Model.getHoKhauDuocChon().getId().get());
        old_list.getItems().clear();
        try {
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    if(!id_chu_cu.getText().equals(resultSet.getString(1))) {
                        ResultSet resultSet1 = Model.getInstance().getDatabaseConnection().lay_nhan_khau(resultSet.getString(1));
                        if (resultSet1.isBeforeFirst()) {
                            resultSet1.next();
                            String cccd = resultSet1.getString(1);
                            String hoTen = resultSet1.getString(2);
                            String quanHe = resultSet.getString(3);
                            String ngaySinh = resultSet1.getString(4);
                            String gioi_tinh;
                            int gioiTinh = resultSet1.getInt(3);
                            if (gioiTinh == 1)
                                gioi_tinh = "Nam";
                            else
                                gioi_tinh = "Nữ";
                            old_list.getItems().add(new thanh_vien_cua_ho_cell(resultSet.getString(1), cccd, hoTen, quanHe, ngaySinh, gioi_tinh));
                        }
                    }
                }
            }
        }catch(SQLException e){
                throw new RuntimeException(e);
        }
    }
}
