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
    public TextField date_them_text;
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


        Model.getInstance().getDatabaseConnection().addHoKhau("41","","null","");
        ResultSet resultSet = Model.getInstance().getDatabaseConnection().lay_ho_khau("41");
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                ma_ho_khau_moi=resultSet.getString(1);
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
                ResultSet resultSet1 = Model.getInstance().getDatabaseConnection().nhanKhau_timkiem(search_text.getText());
                listView_to_chon.getItems().clear();
                try {
                    if(resultSet1.isBeforeFirst()) {
                        while(resultSet1.next()) {
                            String cccd = resultSet1.getString(1);
                            String hoTen = resultSet1.getString(2);
                            String gioitinh = resultSet1.getString(3);
                            String namsinh = resultSet1.getString(4);
                            String diachi = resultSet1.getString(5);

                            listView_to_chon.getItems().add(new List_nhan_khau(cccd, hoTen, gioitinh, namsinh, diachi));
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
                    if(ketqua==1) {
                        listView_to_chon.getItems().clear();
                        listView_to_them.getItems().clear();
                        cap_nhat_list_view_nhan_khau();
                        cap_nhat_list_view_thanh_vien();
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Nguoi nay da o trong mot ho khau roi!");
                        alert.showAndWait();
                    }
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Ban chua chon nguoi duoc them!");
                    alert.showAndWait();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Ban chua dien thong tin quan he giua chu ho va nguoi duoc them!");
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
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Ban chua chon thanh vien de xoa!");
                alert.showAndWait();
            }
            thanh_vien_cua_ho_dc_them=null;
        });


        cancel_but.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Ban chac chan muon huy ?");
            alert.setHeaderText(null);
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
                    ketqua = Model.getInstance().getDatabaseConnection().capNhatHoKhau(ma_ho_khau_moi, id_chu_ho_text.getText(), add_text.getText(), date_them_text.getText(), ghi_chu_text.getText());
                    if (ketqua == 0) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setContentText("Ban nhap sai ngay");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Da them thanh cong");
                        alert.showAndWait();
                        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.HO_KHAU);
                    }
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Ban chua nhap dia chi!");
                    alert.showAndWait();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Thong tin chu ho bi sai!");
                alert.showAndWait();
            }
            id_chu_ho_text.setText("");
            date_them_text.setText("");
            add_text.setText("");
            ghi_chu_text.setText("");
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
                            String gioiTinh = resultSet1.getString(3);
                            listView_to_them.getItems().add(new thanh_vien_cua_ho_cell(cccd, hoTen, quanHe,ngaySinh,gioiTinh));
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
        ResultSet resultSet = Model.getInstance().getDatabaseConnection().truyvan();
        listView_to_chon.getItems().clear();
        try{
            if(resultSet.isBeforeFirst()){
                while (resultSet.next()) {
                    String id = resultSet.getString(1);
                    String hoten = resultSet.getNString(2);
                    String gioitinh = resultSet.getString(3);
                    String namsinh = resultSet.getString(4);
                    String thuongtru = resultSet.getNString(5);
                    listView_to_chon.getItems().add(new List_nhan_khau(id, hoten, gioitinh, namsinh, thuongtru));
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
