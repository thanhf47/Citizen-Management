package com.example.citizenmanagement.controllers.maincontrollers.hoKhau;

import com.example.citizenmanagement.models.List_nhan_khau;
import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;

import com.example.citizenmanagement.models.thanh_vien_cua_ho_cell;
import com.example.citizenmanagement.views.List_nhan_khau_factory;
import com.example.citizenmanagement.views.thanh_vien_cua_ho_cell_factory;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class themMoiHoKhauControler implements Initializable {

    public Button cancel_but;
    public TextField id_chu_ho_text;
    public TextField add_text;
    public TextField ghi_chu_text;
    public ListView<List_nhan_khau> listView_to_chon;
    public ListView<thanh_vien_cua_ho_cell> listView_to_them;
    public Button chuyen_lai;
    public Button chuyen_sang;
    public TextField search_text;
    public Button xac_nhan_but;
    public TextField quan_he_textField;
    //*****************************************************************************
    private List_nhan_khau nhan_khau_dc_chon;
    private thanh_vien_cua_ho_cell thanh_vien_cua_ho_dc_them;
    private String ma_ho_khau_moi;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cap_nhat_list_view_nhan_khau();

        Model.getInstance().getDatabaseConnection().addHoKhau("41","null","");
        ResultSet resultSet = Model.getInstance().getDatabaseConnection().lay_ho_khau("41");
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                ma_ho_khau_moi=resultSet.getString(1);
            }
            else {
                System.out.println("khong coooo");
            }
        }catch (Exception e){
            e.printStackTrace();
        }



        listView_to_chon.setOnMouseClicked(mouseEvent -> {
            nhan_khau_dc_chon = listView_to_chon.getSelectionModel().getSelectedItem();
        });

        listView_to_them.setOnMouseClicked(mouseEvent -> {
            thanh_vien_cua_ho_dc_them = listView_to_them.getSelectionModel().getSelectedItem();
        });



        search_text.textProperty().addListener((observableValue, oldval, newval) -> {
            if(newval.isEmpty()){
                cap_nhat_list_view_nhan_khau();
            }
            else {
                ResultSet resultSet1 = Model.getInstance().getDatabaseConnection().nhanKhau_timkiem_chua_co_nha(search_text.getText());
                listView_to_chon.getItems().clear();
                try {
                    if(resultSet1.isBeforeFirst()) {
                        while(resultSet1.next()) {
                            String ma_nhan_khau=resultSet1.getString(1);
                            String cccd = resultSet1.getString(2);
                            String hoTen = resultSet1.getString(3);
                            String gioitinh = resultSet1.getString(4);
                            String namsinh = resultSet1.getString(5);
                            String diachi = resultSet1.getString(6);

                            listView_to_chon.getItems().add(new List_nhan_khau(ma_nhan_khau,cccd, hoTen, gioitinh, namsinh, diachi));
                        }
                        listView_to_chon.setCellFactory(param ->new List_nhan_khau_factory());
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        chuyen_sang.setOnAction(actionEvent -> {
            if(!quan_he_textField.getText().isEmpty()){
                if(nhan_khau_dc_chon!=null)
                {
                    int ketqua=0;
                    ketqua=Model.getInstance().getDatabaseConnection().add_thanh_vien_cua_ho(nhan_khau_dc_chon.getCccd(),ma_ho_khau_moi,quan_he_textField.getText());

                        listView_to_chon.getItems().clear();
                        listView_to_them.getItems().clear();
                        cap_nhat_list_view_nhan_khau();
                        cap_nhat_list_view_thanh_vien();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo lỗi");
                    alert.setContentText("Bạn chưa chọn người được thêm");
                    alert.setHeaderText(null);
                    alert.showAndWait();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Bạn chưa điền quan hệ với chủ hộ");
                alert.setTitle("Thông báo lỗi");
                alert.setHeaderText(null);
                alert.showAndWait();
            }
            nhan_khau_dc_chon=null;
            quan_he_textField.setText("");
        });



        chuyen_lai.setOnAction(actionEvent -> {
            if(thanh_vien_cua_ho_dc_them!=null){
                    Model.getInstance().getDatabaseConnection().xoa_thanh_vien_cua_ho(thanh_vien_cua_ho_dc_them.getCccd());
                    listView_to_chon.getItems().clear();
                    listView_to_them.getItems().clear();
                    cap_nhat_list_view_nhan_khau();
                    cap_nhat_list_view_thanh_vien();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Thông báo lỗi");
                alert.setContentText("Bạn chưa chọn người để chuyển đi");
                alert.showAndWait();
            }
            thanh_vien_cua_ho_dc_them=null;
        });


        cancel_but.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Bạn chắc chắn muốn hủy?");
            alert.setHeaderText(null);
            alert.setTitle("Xác nhận");
            ButtonType ketqua=alert.showAndWait().orElse(ButtonType.CANCEL);
            if(ketqua==ButtonType.OK) {
                ObservableList<thanh_vien_cua_ho_cell> danh_sach=listView_to_them.getItems();
                for(int i=0; i<danh_sach.size();i++){
                    thanh_vien_cua_ho_cell tam = danh_sach.get(i);
                    Model.getInstance().getDatabaseConnection().xoa_thanh_vien_cua_ho(tam.getCccd());
                }
                Model.getInstance().getDatabaseConnection().xoaHoKhau(ma_ho_khau_moi);
                Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.HO_KHAU);
            }
        });



        xac_nhan_but.setOnAction(event -> {
            if(kiem_tra_chu_ho()) {
                if(!add_text.getText().isEmpty()) {
                    int ketqua = 0;
                    ketqua = Model.getInstance().getDatabaseConnection().capNhatHoKhau(ma_ho_khau_moi, id_chu_ho_text.getText(), add_text.getText(), ghi_chu_text.getText());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Thông báo");
                    alert.setContentText("Đã thêm thành công");
                    alert.showAndWait();
                    Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.HO_KHAU);

                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Thông báo lỗi");
                    alert.setContentText("Bạn chưa nhập địa chỉ");
                    alert.showAndWait();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Thông báo lỗi");
                alert.setContentText("Thông tin chủ hộ bị sai");
                alert.showAndWait();
            }
        });
    }




    public boolean kiem_tra_chu_ho(){
        ResultSet resultSet = Model.getInstance().getDatabaseConnection().lay_cac_thanh_vien(ma_ho_khau_moi);
        try {
            if(resultSet.isBeforeFirst()){
                while (resultSet.next()){
                    if(id_chu_ho_text.getText().equals(resultSet.getString(1)))
                        return true;
                }
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }



    public void cap_nhat_list_view_thanh_vien(){
        String gioi_tinh;
        try {
            ResultSet resultSet = Model.getInstance().getDatabaseConnection().lay_cac_thanh_vien(ma_ho_khau_moi);
            listView_to_them.getItems().clear();
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
                            listView_to_them.getItems().add(new thanh_vien_cua_ho_cell(resultSet.getString(1),cccd, hoTen, quanHe,ngaySinh,gioi_tinh));
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

        listView_to_them.setCellFactory(param-> new thanh_vien_cua_ho_cell_factory());
    }


    public void cap_nhat_list_view_nhan_khau() {
        ResultSet resultSet = Model.getInstance().getDatabaseConnection().truyvan_chua_co_nha();
        listView_to_chon.getItems().clear();
        try{
            if(resultSet.isBeforeFirst()){
                while (resultSet.next()) {
                    String ma_nhan_khau=resultSet.getString(1);
                    String id = resultSet.getString(2);
                    String hoten = resultSet.getNString(3);
                    String gioitinh = resultSet.getString(4);
                    String namsinh = resultSet.getString(5);
                    String thuongtru;
                    if(resultSet.getNString(6)==null) thuongtru="không có";
                    else thuongtru = resultSet.getNString(6);
                    listView_to_chon.getItems().add(new List_nhan_khau(ma_nhan_khau,id, hoten, gioitinh, namsinh, thuongtru));
                }
            }
        }
        catch(Exception e) {
            System.out.println("Concac");
            e.printStackTrace();
        }
        listView_to_chon.setCellFactory(param-> new List_nhan_khau_factory());
    }

}
