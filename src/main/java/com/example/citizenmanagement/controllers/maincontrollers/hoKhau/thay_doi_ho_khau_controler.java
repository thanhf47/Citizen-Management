package com.example.citizenmanagement.controllers.maincontrollers.hoKhau;

import com.example.citizenmanagement.models.*;
import com.example.citizenmanagement.views.List_nhan_khau_factory;
import com.example.citizenmanagement.views.thanh_vien_cua_ho_cell_factory;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class thay_doi_ho_khau_controler implements Initializable {
    public ListView<List_nhan_khau> listView_nhan_khau;
    public ListView<thanh_vien_cua_ho_cell> listView_thanh_vien;
    public TextField thay_doi_ma_chu_ho_textFiled;
    public TextField thay_doi_dia_chi_textField;
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
    //dung de backup lai
    ObservableList<thanh_vien_cua_ho_cell> danh_sach_thanh_vien_ban_dau = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //***************************************************
        listView_thanh_vien.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tam= Model.getHoKhauDuocChon();
        cap_nhat_list_view_nhan_khau();
        cap_nhat_list_view_thanh_vien();
        cap_nhat_danh_sach_ban_dau();
        String ma_chu_ho=Model.getInstance().getDatabaseConnection().lay_chu_ho(tam.getId().get());
        thay_doi_ma_chu_ho_textFiled.setText(ma_chu_ho);
        thay_doi_dia_chi_textField.setText(String.valueOf(tam.getAddress().get()));
        ghi_chu_textField.setText(String.valueOf(tam.getGhi_chu().get()));
        //**********************************************************

        listView_nhan_khau.setOnMouseClicked(mouseEvent -> {
            nhan_khau_dc_chon = listView_nhan_khau.getSelectionModel().getSelectedItem();
        });

//
        selectedItem();


        tim_kiem_nhan_khau.textProperty().addListener((observableValue, oldval, newval) -> {
            if(newval.isEmpty()){
                cap_nhat_list_view_nhan_khau();
            }
            else {
                ResultSet resultSet = Model.getInstance().getDatabaseConnection().nhanKhau_timkiem_chua_co_nha(tim_kiem_nhan_khau.getText());
                listView_nhan_khau.getItems().clear();
                try {
                    if(resultSet.isBeforeFirst()) {
                        while(resultSet.next()) {
                            String ma_nhan_khau = resultSet.getString(1);
                            boolean chuaChet = Model.getInstance().getDatabaseConnection().checkKhaiTu(ma_nhan_khau);
                            if (!chuaChet) continue;

                            String cccd = resultSet.getString(2);
                            String hoTen = resultSet.getString(3);
                            String gioitinh = resultSet.getString(4);
                            String ngaysinh = resultSet.getString(5);
                            String diachi = resultSet.getString(6);

                            listView_nhan_khau.getItems().add(new List_nhan_khau(ma_nhan_khau,cccd, hoTen, gioitinh, ngaysinh, diachi));
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
                    Model.getInstance().getDatabaseConnection().add_thanh_vien_cua_ho(nhan_khau_dc_chon.getSo_nhan_khau(),String.valueOf(tam.getId().get()),quan_he_textField.getText());
                    listView_nhan_khau.getItems().clear();
                    listView_thanh_vien.getItems().clear();
                    cap_nhat_list_view_nhan_khau();
                    cap_nhat_list_view_thanh_vien();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Thông báo lỗi");
                    alert.setHeaderText(null);
                    alert.setContentText("Bạn chưa chọn người được thêm!");
                    alert.showAndWait();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Bạn chưa điền quan hệ với chủ hộ!");
                alert.showAndWait();
            }
            nhan_khau_dc_chon=null;
            quan_he_textField.setText("");
        });



        xoa_thanh_vien_but.setOnAction(actionEvent -> {
            if(!thanh_vien_cua_ho_dc_chon.isEmpty()){
                for(thanh_vien_cua_ho_cell item:thanh_vien_cua_ho_dc_chon) {
                    Model.getInstance().getDatabaseConnection().xoa_thanh_vien_cua_ho(item.getmaNhanKhau());
                }

//                listView_thanh_vien.getItems().removeAll(thanh_vien_cua_ho_dc_chon);
                listView_thanh_vien.getItems().clear();
                cap_nhat_list_view_thanh_vien();
                listView_nhan_khau.getItems().clear();
                cap_nhat_list_view_nhan_khau();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông báo lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Bạn chưa chọn thành viên để xóa!");
                alert.showAndWait();
            }
            thanh_vien_cua_ho_dc_chon.clear();
        });




        huy_but.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Bạn chắc chắn muốn hủy những gì vừa thay đổi?");
            alert.setHeaderText(null);
            alert.setTitle("Xác nhận");

            Optional<ButtonType> resutl = alert.showAndWait();

            if(resutl.get() == ButtonType.OK) {
                ObservableList<thanh_vien_cua_ho_cell> danh_sach_thanh_vien_cua_ho_hien_tai = listView_thanh_vien.getItems();

                for (int i = 0; i < danh_sach_thanh_vien_cua_ho_hien_tai.size(); i++) {
                    thanh_vien_cua_ho_cell tam1 = danh_sach_thanh_vien_cua_ho_hien_tai.get(i);
                        Model.getInstance().getDatabaseConnection().xoa_thanh_vien_cua_ho(tam1.getmaNhanKhau());
                }

                for (int i = 0; i < danh_sach_thanh_vien_ban_dau.size(); i++) {
                    thanh_vien_cua_ho_cell tam1 = danh_sach_thanh_vien_ban_dau.get(i);
                    Model.getInstance().getDatabaseConnection().add_thanh_vien_cua_ho(tam1.getmaNhanKhau(), String.valueOf(tam.getId().get()), tam1.getQuan_he());
                }
                Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.XEM_CHI_TIET_HO_KHAU);
            }
        });



        xac_nhan_but.setOnAction(actionEvent -> {
            if(kiem_tra_chu_ho()) {
                if(!thay_doi_dia_chi_textField.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Bạn xác nhận những gì vừa thay đổi?");
                    alert.setHeaderText(null);
                    alert.setTitle("Xác nhận");

                    ButtonType resutl = alert.showAndWait().orElse(ButtonType.CANCEL);
                    if (resutl == ButtonType.OK) {
                        int ketqua = 0;
                        ketqua = Model.getInstance().getDatabaseConnection().capNhatHoKhau(
                                String.valueOf(tam.getId().get()), thay_doi_ma_chu_ho_textFiled.getText(),
                                thay_doi_dia_chi_textField.getText(), ghi_chu_textField.getText());
                        if (ketqua == 1) {
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.setContentText("Bạn đã thay đổi thành công!");
                            alert1.setTitle("Thông báo");
                            alert1.setHeaderText(null);
                            alert1.showAndWait();
                            Model.setHoKhauDuocChon(new MainHoKhauCell(String.valueOf(tam.getId().get()), thay_doi_ma_chu_ho_textFiled.getText(),
                                    thay_doi_dia_chi_textField.getText(), tam.getDate_tao().get(), ghi_chu_textField.getText()));
                            Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.XEM_CHI_TIET_HO_KHAU);
                        } else {
                            Alert alert1 = new Alert(Alert.AlertType.ERROR);
                            alert1.setContentText("Thay đổi không thành công");
                            alert1.setTitle("Thông báo lỗi");
                            alert1.setHeaderText(null);
                            alert1.showAndWait();
                        }
                    }
                }
                else {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setContentText("Bạn chưa nhập địa chỉ");
                    alert1.setTitle("Thông báo lỗi");
                    alert1.setHeaderText(null);
                    alert1.showAndWait();
                }
            }
            else {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setContentText("Chủ hộ không tồn tại trong hộ");
                alert1.setTitle("Thông báo lỗi");
                alert1.setHeaderText(null);
                alert1.showAndWait();
            }
        });
    }

    private void selectedItem() {
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
                    if(thay_doi_ma_chu_ho_textFiled.getText().equals(resultSet.getString(1))) {
                        boolean chuaChet = Model.getInstance().getDatabaseConnection().checkKhaiTu(thay_doi_ma_chu_ho_textFiled.getText());
                        if (!chuaChet) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setContentText("Người em chọn chết rồi, chọn thằng khác đi.");
                            alert.setTitle("Cảnh báo");
                            alert.setHeaderText(null);
                            alert.showAndWait();
                        }
                        else return true;
                    }

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
            ResultSet resultSet = Model.getInstance().getDatabaseConnection().lay_cac_thanh_vien(String.valueOf(tam.getId().get()));
            listView_thanh_vien.getItems().clear();
            try {
                if(resultSet.isBeforeFirst()){
                    while (resultSet.next()){
                        ResultSet resultSet1 = Model.getInstance().getDatabaseConnection().lay_nhan_khau(resultSet.getString(1));
                        if(resultSet1.isBeforeFirst()){
                            resultSet1.next();
                            String cccd = resultSet1.getString(1);
                            String hoTen = resultSet1.getNString(2);
                            String quanHe = resultSet.getNString(3);
                            String ngaySinh = resultSet1.getString(4);
                            int gioiTinh = resultSet1.getInt(3);
                            if(gioiTinh==1)
                                gioi_tinh="Nam";
                            else
                                gioi_tinh="Nu";
                            listView_thanh_vien.getItems().add(new thanh_vien_cua_ho_cell(resultSet.getString(1), cccd, hoTen, quanHe,ngaySinh,gioi_tinh));
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
        ResultSet resultSet = Model.getInstance().getDatabaseConnection().truyvan_chua_co_nha();
        listView_nhan_khau.getItems().clear();
        try{
            if(resultSet.isBeforeFirst()){
                while (resultSet.next()) {
                    String maNhanKhau=resultSet.getString(1);
                    boolean chuaChet = Model.getInstance().getDatabaseConnection().checkKhaiTu(maNhanKhau);
                    if (!chuaChet) continue;
                    String soCanCuoc = resultSet.getString(2);
                    String hoTen = resultSet.getNString(3);
                    String gioiTinh = resultSet.getString(4);
                    String ngaySinh = resultSet.getString(5);
                    String thuongTru = resultSet.getNString(6);
                    listView_nhan_khau.getItems().add(new List_nhan_khau(maNhanKhau, soCanCuoc, hoTen, gioiTinh, ngaySinh, thuongTru));
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        listView_nhan_khau.setCellFactory(param-> new List_nhan_khau_factory());
    }


//    public Boolean kiem_tra(ObservableList<thanh_vien_cua_ho_cell> danh_sach, thanh_vien_cua_ho_cell doi_tuong){
//        for(int i=0;i<danh_sach.size();i++){
//            thanh_vien_cua_ho_cell tam = danh_sach.get(i);
//            if(tam.getmaNhanKhau().equals(doi_tuong.getmaNhanKhau()))
//                return true;
//        }
//        return false;
//    }

    public void cap_nhat_danh_sach_ban_dau(){
        String gioi_tinh;
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
                            int gioiTinh = resultSet1.getInt(3);
                            if(gioiTinh==1)
                                gioi_tinh="Nam";
                            else
                                gioi_tinh="Nu";
                            danh_sach_thanh_vien_ban_dau.add(new thanh_vien_cua_ho_cell(resultSet.getString(1),cccd, hoTen, quanHe,ngaySinh,gioi_tinh));
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
