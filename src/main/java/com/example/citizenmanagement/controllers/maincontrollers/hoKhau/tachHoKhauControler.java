package com.example.citizenmanagement.controllers.maincontrollers.hoKhau;

import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import com.example.citizenmanagement.models.thanh_vien_cua_ho_cell;
import com.example.citizenmanagement.views.thanh_vien_cua_ho_cell_factory;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class tachHoKhauControler implements Initializable {
    public TextField maHK_search_textField;
    public ListView<thanh_vien_cua_ho_cell> nguoi_in_listView_now;
    public ListView<thanh_vien_cua_ho_cell> nguoi_in_listView_new;
    public Button search_but;

    public Button cancel_but;
    public Button xacNhan_but;

    public TextField ghi_chu;
    public TextField dia_chi;
    public TextField id_chu_moi;

    public TextField quan_he_textField;
    public Button chuyen_sang;
    public Button chuyen_lai;
    //*******************************************
    private thanh_vien_cua_ho_cell thanh_vien_tren=null;
    private thanh_vien_cua_ho_cell thanh_vien_duoi=null;
    private String ma_ho_khau_moi;
    private String ma_chu_ho_hien_tai;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Model.getInstance().getDatabaseConnection().addHoKhau("41","null","");
        ResultSet resultSet = Model.getInstance().getDatabaseConnection().lay_ho_khau("41");
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                ma_ho_khau_moi=resultSet.getString(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        search_but.setOnAction(actionEvent -> {
            cap_nhat_tren();
        });


        chuyen_sang.setOnAction(actionEvent -> {
            if(!quan_he_textField.getText().isEmpty()){
                thanh_vien_tren=nguoi_in_listView_now.getSelectionModel().getSelectedItem();
                if(thanh_vien_tren!=null){
                    if(!ma_chu_ho_hien_tai.equals(thanh_vien_tren.getmaNhanKhau())) {
                        Model.getInstance().getDatabaseConnection().xoa_thanh_vien_cua_ho(thanh_vien_tren.getmaNhanKhau());
                        Model.getInstance().getDatabaseConnection().add_thanh_vien_cua_ho(thanh_vien_tren.getmaNhanKhau(), ma_ho_khau_moi, quan_he_textField.getText());
                        nguoi_in_listView_now.getItems().clear();
                        nguoi_in_listView_new.getItems().clear();
                        cap_nhat_tren();
                        cap_nhat_duoi();
                        quan_he_textField.setText("");
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("Thông báo lỗi");
                        alert.setContentText("Bạn không được chuyển chủ hộ của hộ hiện tại");
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



        chuyen_lai.setOnAction(actionEvent -> {
            if(!quan_he_textField.getText().isEmpty()){
                thanh_vien_duoi=nguoi_in_listView_new.getSelectionModel().getSelectedItem();
                if(thanh_vien_duoi!=null){
                    Model.getInstance().getDatabaseConnection().xoa_thanh_vien_cua_ho(thanh_vien_duoi.getmaNhanKhau());
                    Model.getInstance().getDatabaseConnection().add_thanh_vien_cua_ho(thanh_vien_duoi.getmaNhanKhau(),maHK_search_textField.getText(),quan_he_textField.getText());
                    nguoi_in_listView_now.getItems().clear();
                    nguoi_in_listView_new.getItems().clear();
                    cap_nhat_tren();
                    cap_nhat_duoi();
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
           if(nguoi_in_listView_new.getItems().isEmpty()){
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
               alert.setHeaderText(null);
               alert.setTitle("Xác nhận");
               alert.setContentText("Bạn chắc chắn muốn hủy chứ!");
               ButtonType ket = alert.showAndWait().orElse(ButtonType.CANCEL);
               if(ket==ButtonType.OK){
                   Model.getInstance().getDatabaseConnection().xoaHoKhau(ma_ho_khau_moi);
                   Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.HO_KHAU);
               }
           }
           else {
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText(null);
               alert.setTitle("Thông báo lỗi");
               alert.setContentText("Bạn cần đưa các thành viên trở lại hộ khẩu ban đầu!");
               alert.showAndWait();
           }
        });


        xacNhan_but.setOnAction(event -> {
            int ketqua=0;
            if(!dia_chi.getText().isEmpty()) {
                if(kiem_tra_chu_ho_moi()) {
                    ketqua = Model.getInstance().getDatabaseConnection().capNhatHoKhau(ma_ho_khau_moi, id_chu_moi.getText(), dia_chi.getText(), ghi_chu.getText());
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
                    alert.setContentText("Sai thông tin chủ hộ");
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

    }

    public void cap_nhat_tren(){
        String gioi_tinh;
        try {
            ResultSet resultSet = Model.getInstance().getDatabaseConnection().lay_cac_thanh_vien(maHK_search_textField.getText());
            nguoi_in_listView_now.getItems().clear();
            try {
                if(resultSet.isBeforeFirst()){
                    while (resultSet.next()){
                        ResultSet resultSet1 = Model.getInstance().getDatabaseConnection().lay_nhan_khau(resultSet.getString(1));
                        if(resultSet1.isBeforeFirst()){
                            resultSet1.next();
                            String cccd = resultSet1.getString(1);
                            String hoTen = resultSet1.getString(2);
                            String quanHe = resultSet.getString(3);
                            String ngaySinh = resultSet1.getString(4);
                            int gioiTinh = resultSet1.getInt(3);
                            if(gioiTinh==1)
                                gioi_tinh="Nam";
                            else
                                gioi_tinh="Nu";
                            nguoi_in_listView_now.getItems().add(new thanh_vien_cua_ho_cell(resultSet.getString(1),cccd, hoTen, quanHe,ngaySinh,gioi_tinh));
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("loi o xem chi tiet lay_nhan_khau");
                e.printStackTrace();
            }
        }catch (Exception e){
            System.out.println("loi o xem chi tiet lay_cac_thanh_vien");
            e.printStackTrace();
        }

        ma_chu_ho_hien_tai = Model.getInstance().getDatabaseConnection().lay_chu_ho(maHK_search_textField.getText());

        nguoi_in_listView_now.setCellFactory(param-> new thanh_vien_cua_ho_cell_factory());
    }

    public void cap_nhat_duoi(){
        String gioi_tinh;
        try {
            ResultSet resultSet = Model.getInstance().getDatabaseConnection().lay_cac_thanh_vien(ma_ho_khau_moi);
            nguoi_in_listView_new.getItems().clear();
            try {
                if(resultSet.isBeforeFirst()){
                    while (resultSet.next()){
                        ResultSet resultSet1 = Model.getInstance().getDatabaseConnection().lay_nhan_khau(resultSet.getString(1));
                        if(resultSet1.isBeforeFirst()){
                            resultSet1.next();
                            String cccd = resultSet1.getString(1);
                            String hoTen = resultSet1.getString(2);
                            String quanHe = resultSet.getString(3);
                            String ngaySinh = resultSet1.getString(4);
                            int gioiTinh = resultSet1.getInt(3);
                            if(gioiTinh==1)
                                gioi_tinh="Nam";
                            else
                                gioi_tinh="Nu";
                            nguoi_in_listView_new.getItems().add(new thanh_vien_cua_ho_cell(resultSet.getString(1),cccd, hoTen, quanHe,ngaySinh,gioi_tinh));
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("loi o xem chi tiet lay_nhan_khau");
                e.printStackTrace();
            }
        }catch (Exception e){
            System.out.println("loi o xem chi tiet lay_cac_thanh_vien");
            e.printStackTrace();
        }

        nguoi_in_listView_new.setCellFactory(param-> new thanh_vien_cua_ho_cell_factory());
    }

    public boolean kiem_tra_chu_ho_moi(){
        ResultSet resultSet = Model.getInstance().getDatabaseConnection().lay_cac_thanh_vien(ma_ho_khau_moi);
        try {
            if(resultSet.isBeforeFirst()){
                while (resultSet.next()){
                    if(id_chu_moi.getText().equals(resultSet.getString(1)))
                        return true;
                }
            }
        }catch (Exception e){
           return false;
        }
        return false;
    }


}
