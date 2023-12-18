package com.example.citizenmanagement.controllers.maincontrollers.hoKhau;

import com.example.citizenmanagement.models.*;
import com.example.citizenmanagement.views.List_nhan_khau_factory;
import com.example.citizenmanagement.views.thanh_vien_cua_ho_cell_factory;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class thay_doi_ho_khau_controler implements Initializable {
    public ListView<List_nhan_khau> listView_nhan_khau;
    public ListView<thanh_vien_cua_ho_cell> listView_thanh_vien;
    public TextField thay_doi_ma_chu_ho_textFiled;
    public TextField thay_doi_dia_chi_textField;
    public TextField thay_doi_ngay_tao_textField;
    public TextField ghi_chu_textField;
    public Button them_thanh_vien_but;
    public Button xoa_thanh_vien_but;
    public Button xac_nhan_but;
    public Button huy_but;
    public TextField tim_kiem_nhan_khau;
    public TextField quan_he_textField;
    //**************************************
    private List_nhan_khau nhan_khau_dc_chon=null;
    private List<thanh_vien_cua_ho_cell> thanh_vien_cua_ho_dc_chon = new ArrayList<>();
    private MainHoKhauCell tam;
    ObservableList<thanh_vien_cua_ho_cell> danh_sach_thanh_vien_ban_dau = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //***************************************************
        listView_thanh_vien.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tam= Model.getHoKhauDuocChon();
        cap_nhat_list_view_nhan_khau();
        cap_nhat_list_view_thanh_vien();
        cap_nhat_danh_sach_ban_dau();
        thay_doi_ma_chu_ho_textFiled.setText(String.valueOf(tam.getOwner().get()));
        thay_doi_dia_chi_textField.setText(String.valueOf(tam.getAddress().get()));
        thay_doi_ngay_tao_textField.setText(String.valueOf(tam.getDate_tao().get()));
        ghi_chu_textField.setText(String.valueOf(tam.getGhi_chu().get()));
        //**********************************************************

        listView_nhan_khau.setOnMouseClicked(mouseEvent -> {
            nhan_khau_dc_chon = listView_nhan_khau.getSelectionModel().getSelectedItem();
        });

//
        selectetItem();



        tim_kiem_nhan_khau.textProperty().addListener((observableValue, oldval, newval) -> {
            if(newval.isEmpty()){
                cap_nhat_list_view_nhan_khau();
            }
            else {
                ResultSet resultSet = Model.getInstance().getDatabaseConnection().nhanKhau_timkiem(tim_kiem_nhan_khau.getText());
                listView_nhan_khau.getItems().clear();
                try {
                    if(resultSet.isBeforeFirst()) {
                        while(resultSet.next()) {
                            String cccd = resultSet.getString(1);
                            String hoTen = resultSet.getString(2);
                            String gioitinh = resultSet.getString(3);
                            String namsinh = resultSet.getString(4);
                            String diachi = resultSet.getString(5);

                            listView_nhan_khau.getItems().add(new List_nhan_khau(cccd, hoTen, gioitinh, namsinh, diachi));
                        }
                        listView_nhan_khau.setCellFactory(param ->new List_nhan_khau_factory());
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



        them_thanh_vien_but.setOnAction(actionEvent -> {
            if(!quan_he_textField.getText().isEmpty()){
                if(nhan_khau_dc_chon!=null)
                {
                    int ketqua=0;
                    ketqua=Model.getInstance().getDatabaseConnection().add_thanh_vien_cua_ho(nhan_khau_dc_chon.getCccd(),String.valueOf(tam.getId().get()),quan_he_textField.getText());
                    if(ketqua==1) {
                        listView_nhan_khau.getItems().clear();
                        listView_thanh_vien.getItems().clear();
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
                alert.setTitle(null);
                alert.setHeaderText("Canh bao");
                alert.setContentText("Ban chua dien thong tin quan he giua chu ho va nguoi duoc them!");
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStylesheets().add(getClass().getResource("/styles/thongbao.css").toExternalForm());
                ImageView icon = new ImageView(getClass().getResource("/images/main/thong_bao_warning.png").toExternalForm()); // Thay icon.png bằng tên của file biểu tượng của bạn
                icon.setFitWidth(50);
                icon.setFitHeight(50);

                dialogPane.setGraphic(icon);
                alert.showAndWait();
            }
            nhan_khau_dc_chon=null;
            quan_he_textField.setText("");
        });



        xoa_thanh_vien_but.setOnAction(actionEvent -> {
            if(!thanh_vien_cua_ho_dc_chon.isEmpty()){
                for(thanh_vien_cua_ho_cell item:thanh_vien_cua_ho_dc_chon) {
                    if (!item.getmaNhanKhau().equals(tam.getOwner().get())) {
                        Model.getInstance().getDatabaseConnection().xoa_thanh_vien_cua_ho(item.getCccd());
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Ban phai thay doi thong tin chu ho truoc khi xoa!");
                        alert.showAndWait();
                    }
                }

//                listView_thanh_vien.getItems().removeAll(thanh_vien_cua_ho_dc_chon);
                listView_thanh_vien.getItems().clear();
                cap_nhat_list_view_thanh_vien();
                listView_nhan_khau.getItems().clear();
                cap_nhat_list_view_nhan_khau();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Ban chua chon thanh vien de xoa!");
                alert.showAndWait();
            }
            thanh_vien_cua_ho_dc_chon.clear();
        });




        huy_but.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Ban chac chan muon huy nhung gi vua thay doi?");
            alert.setHeaderText(null);
            alert.setTitle("Xac nhan");

            ButtonType resutl = alert.showAndWait().orElse(ButtonType.CANCEL);

            if(resutl==ButtonType.OK) {
                ObservableList<thanh_vien_cua_ho_cell> danh_sach_thanh_vien_cua_ho_hien_tai = listView_thanh_vien.getItems();
                for (int i = 0; i < danh_sach_thanh_vien_ban_dau.size(); i++) {
                    thanh_vien_cua_ho_cell tam1 = danh_sach_thanh_vien_ban_dau.get(i);
                    if (!kiem_tra(danh_sach_thanh_vien_cua_ho_hien_tai, tam1)) {
                        Model.getInstance().getDatabaseConnection().add_thanh_vien_cua_ho(tam1.getCccd(), String.valueOf(tam.getId().get()), tam1.getQuan_he());
                    }
                }

                for (int i = 0; i < danh_sach_thanh_vien_cua_ho_hien_tai.size(); i++) {
                    thanh_vien_cua_ho_cell tam1 = danh_sach_thanh_vien_cua_ho_hien_tai.get(i);
                    if (!kiem_tra(danh_sach_thanh_vien_ban_dau, tam1)) {
                        Model.getInstance().getDatabaseConnection().xoa_thanh_vien_cua_ho(tam1.getCccd());
                    }
                }
                Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.XEM_CHI_TIET_HO_KHAU);
            }
        });



        xac_nhan_but.setOnAction(actionEvent -> {
            if(kiem_tra_chu_ho()) {
                if(!thay_doi_dia_chi_textField.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Ban xac nhan nhung gi vua thay doi?");
                    alert.setHeaderText(null);
                    alert.setTitle("Xac nhan");

                    ButtonType resutl = alert.showAndWait().orElse(ButtonType.CANCEL);
                    if (resutl == ButtonType.OK) {
                        int ketqua = 0;
                        ketqua = Model.getInstance().getDatabaseConnection().capNhatHoKhau(String.valueOf(tam.getId().get()), thay_doi_ma_chu_ho_textFiled.getText(),
                                thay_doi_dia_chi_textField.getText(), thay_doi_ngay_tao_textField.getText(), ghi_chu_textField.getText());
                        if (ketqua == 1) {
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.setContentText("Ban da thay doi thanh cong!");
                            alert1.setHeaderText(null);
                            alert1.showAndWait();
                            Model.setHoKhauDuocChon(new MainHoKhauCell(String.valueOf(tam.getId().get()), thay_doi_ma_chu_ho_textFiled.getText(),
                                    thay_doi_dia_chi_textField.getText(), thay_doi_ngay_tao_textField.getText(), ghi_chu_textField.getText()));
                            Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.XEM_CHI_TIET_HO_KHAU);
                        } else {
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.setContentText("Ban da nhap sai ngay");
                            alert1.setHeaderText(null);
                            alert1.showAndWait();
                        }
                    }
                }
                else {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setContentText("Ban chua nhap dia chi");
                    alert1.setHeaderText(null);
                    alert1.showAndWait();
                }
            }
            else {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setContentText("Chu ho khong ton tai trong ho");
                alert1.setHeaderText(null);
                alert1.showAndWait();
            }
        });
    }

    private void selectetItem() {
        listView_thanh_vien.getSelectionModel().getSelectedItems().addListener((ListChangeListener<? super thanh_vien_cua_ho_cell>) change-> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (thanh_vien_cua_ho_cell item : change.getAddedSubList()) {
                        item.setCheck_box(true);
                        thanh_vien_cua_ho_dc_chon.add(item);
                    }
                }

                if (change.wasRemoved()) {
                    for(thanh_vien_cua_ho_cell item : change.getRemoved()) {
                        item.setCheck_box(false);
                        thanh_vien_cua_ho_dc_chon.remove(item);
                    }
                }
            }
        });
    }


    public boolean kiem_tra_chu_ho(){
        ResultSet resultSet = Model.getInstance().getDatabaseConnection().lay_cac_thanh_vien(String.valueOf(tam.getId().get()));
        try {
            if(resultSet.isBeforeFirst()){
                while (resultSet.next()){
                    if(thay_doi_ma_chu_ho_textFiled.getText().equals(resultSet.getString(1)))
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
            ResultSet resultSet = Model.getInstance().getDatabaseConnection().lay_cac_thanh_vien(String.valueOf(tam.getId().get()));
            listView_thanh_vien.getItems().clear();
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
                            listView_thanh_vien.getItems().add(new thanh_vien_cua_ho_cell(resultSet.getString(1), cccd, hoTen, quanHe,ngaySinh,gioiTinh));
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

        listView_thanh_vien.setCellFactory(param-> new thanh_vien_cua_ho_cell_factory());
    }



    public void cap_nhat_list_view_nhan_khau() {
        ResultSet resultSet = Model.getInstance().getDatabaseConnection().truyvan();
        listView_nhan_khau.getItems().clear();
        try{
            if(resultSet.isBeforeFirst()){
                while (resultSet.next()) {
                    String id = resultSet.getString(1);
                    String hoten = resultSet.getNString(2);
                    String gioitinh = resultSet.getString(3);
                    String namsinh = resultSet.getString(4);
                    String thuongtru = resultSet.getNString(5);
                    listView_nhan_khau.getItems().add(new List_nhan_khau(id, hoten, gioitinh, namsinh, thuongtru));
                }
            }
        }
        catch(Exception e) {
            System.out.println("Concac");
            e.printStackTrace();
        }
        listView_nhan_khau.setCellFactory(param-> new List_nhan_khau_factory());
    }


    public Boolean kiem_tra(ObservableList<thanh_vien_cua_ho_cell> danh_sach, thanh_vien_cua_ho_cell doi_tuong){
        for(int i=0;i<danh_sach.size();i++){
            thanh_vien_cua_ho_cell tam = danh_sach.get(i);
            if(tam.getCccd().equals(doi_tuong.getCccd()))
                return true;
        }
        return false;
    }



    public void cap_nhat_danh_sach_ban_dau(){
        try {
            ResultSet resultSet = Model.getInstance().getDatabaseConnection().lay_cac_thanh_vien(String.valueOf(tam.getId().get()));
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
                            danh_sach_thanh_vien_ban_dau.add(new thanh_vien_cua_ho_cell(cccd, hoTen, quanHe,ngaySinh,gioiTinh));
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
    }
}
