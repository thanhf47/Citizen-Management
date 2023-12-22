package com.example.citizenmanagement.controllers.maincontrollers.hoKhau;

import com.example.citizenmanagement.models.MainHoKhauCell;
import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import com.example.citizenmanagement.models.thanh_vien_cua_ho_cell;
import com.example.citizenmanagement.views.thanh_vien_cua_ho_cell_factory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.lang.annotation.Inherited;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class thay_doi_chu_ho_controler implements Initializable {
    public ListView<thanh_vien_cua_ho_cell> nguoi_in_listView_now;
    public ListView<thanh_vien_cua_ho_cell> nguoi_in_listView_new;
    public TextField quan_he_textField;
    public Button chuyen_sang;
    public Button chuyen_lai;
    public ChoiceBox<String> choice_box_chu_ho_moi;
    public Button xac_nhan_but;
    public Button huy_but;
    //*********************************
    private MainHoKhauCell tam;
    private ObservableList<thanh_vien_cua_ho_cell> danh_sach_duoi= FXCollections.observableArrayList();
    private ObservableList<thanh_vien_cua_ho_cell> danh_sach_tren=FXCollections.observableArrayList();

    private thanh_vien_cua_ho_cell thanh_vien_tren;
    private thanh_vien_cua_ho_cell thanh_vien_duoi;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nguoi_in_listView_new.setItems(danh_sach_duoi);
        nguoi_in_listView_now.setItems(danh_sach_tren);

        tam=Model.getHoKhauDuocChon();
        cap_nhat_tren();
        cap_nhat_choice_box();

        //**********************************************
        choice_box_chu_ho_moi.setOnAction(actionEvent -> {
            cap_nhat_list_view_moi_khi_bam_chon_chu_ho_moi();
        });

        /*nguoi_in_listView_new.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Lấy phần tử được chọn
                thanh_vien_duoi=nguoi_in_listView_new.getSelectionModel().getSelectedItem();
            }
        });


        nguoi_in_listView_now.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Lấy phần tử được chọn
                thanh_vien_tren=nguoi_in_listView_now.getSelectionModel().getSelectedItem();
            }
        });*/


        chuyen_sang.setOnAction(actionEvent -> {
            if(!quan_he_textField.getText().isEmpty()){
                thanh_vien_tren=nguoi_in_listView_now.getSelectionModel().getSelectedItem();
                if(thanh_vien_tren!=null){
                        Model.getInstance().getDatabaseConnection().xoa_thanh_vien_cua_ho(thanh_vien_tren.getmaNhanKhau());
                        Model.getInstance().getDatabaseConnection().add_thanh_vien_cua_ho(thanh_vien_tren.getmaNhanKhau(), tam.getId().get(), quan_he_textField.getText());

                        danh_sach_tren.remove(thanh_vien_tren);

                        thanh_vien_tren.setQuan_he(quan_he_textField.getText());
                        danh_sach_duoi.add(thanh_vien_tren);

                        nguoi_in_listView_now.setCellFactory(param-> new thanh_vien_cua_ho_cell_factory());
                        nguoi_in_listView_new.setCellFactory(param-> new thanh_vien_cua_ho_cell_factory());


                        quan_he_textField.setText("");

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

            thanh_vien_tren=null;
        });



        chuyen_lai.setOnAction(actionEvent -> {
            if(!quan_he_textField.getText().isEmpty()){
                thanh_vien_duoi=nguoi_in_listView_new.getSelectionModel().getSelectedItem();
                if(thanh_vien_duoi!=null){
                    Model.getInstance().getDatabaseConnection().xoa_thanh_vien_cua_ho(thanh_vien_duoi.getmaNhanKhau());
                    Model.getInstance().getDatabaseConnection().add_thanh_vien_cua_ho(thanh_vien_duoi.getmaNhanKhau(),tam.getId().get(),quan_he_textField.getText());


                    danh_sach_duoi.remove(thanh_vien_duoi);

                    thanh_vien_duoi.setQuan_he(quan_he_textField.getText());
                    danh_sach_tren.add(thanh_vien_duoi);

                    nguoi_in_listView_now.setCellFactory(param-> new thanh_vien_cua_ho_cell_factory());
                    nguoi_in_listView_new.setCellFactory(param-> new thanh_vien_cua_ho_cell_factory());

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
            thanh_vien_duoi=null;
        });

        xac_nhan_but.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Bạn chắc chắn muốn lưu những gì vừa thay đổi?");
            alert.setHeaderText(null);
            alert.setTitle("Xác nhận");

            ButtonType resutl = alert.showAndWait().orElse(ButtonType.CANCEL);

            if(resutl==ButtonType.OK) {
                Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.XEM_CHI_TIET_HO_KHAU);
            }
        });

        huy_but.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Bạn chắc chắn muốn hủy những gì vừa thay đổi?");
            alert.setHeaderText(null);
            alert.setTitle("Xác nhận");

            ButtonType resutl = alert.showAndWait().orElse(ButtonType.CANCEL);

            if(resutl==ButtonType.OK) {
                Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.XEM_CHI_TIET_HO_KHAU);
            }
        });
    }




    public void cap_nhat_choice_box(){
        ResultSet resultSet = Model.getInstance().getDatabaseConnection().lay_cac_thanh_vien(tam.getId().get());

        try {
            if(resultSet.isBeforeFirst()){
                while (resultSet.next()){
                    choice_box_chu_ho_moi.getItems().add(resultSet.getString(1));
                }
            }
        } catch (Exception e) {
            System.out.println("loi o xem chi tiet lay_nhan_khau");
            e.printStackTrace();
        }
    }




    public void cap_nhat_tren(){
        String gioi_tinh;
        try {
            ResultSet resultSet = Model.getInstance().getDatabaseConnection().lay_cac_thanh_vien(tam.getId().get());
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
                            //danh_sach_tren.add(new thanh_vien_cua_ho_cell(resultSet.getString(1),cccd, hoTen, quanHe,ngaySinh,gioi_tinh));
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

        nguoi_in_listView_now.setCellFactory(param-> new thanh_vien_cua_ho_cell_factory());
    }






    public void cap_nhat_list_view_moi_khi_bam_chon_chu_ho_moi(){
        Model.getInstance().getDatabaseConnection().xoa_thanh_vien_cua_ho(choice_box_chu_ho_moi.getValue());
        Model.getInstance().getDatabaseConnection().add_thanh_vien_cua_ho(choice_box_chu_ho_moi.getValue(),tam.getId().get(),"Chủ hộ");
        Model.getInstance().getDatabaseConnection().capNhatHoKhau(tam.getId().get(),choice_box_chu_ho_moi.getValue(),tam.getAddress().get(),tam.getGhi_chu().get());
        ResultSet resultSet = Model.getInstance().getDatabaseConnection().lay_nhan_khau(choice_box_chu_ho_moi.getValue());
        try {
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
                String gioi;
                if(resultSet.getInt(3)==1)
                    gioi="Nam";
                else
                    gioi="Nữ";
               // nguoi_in_listView_new.getItems().add(new thanh_vien_cua_ho_cell(choice_box_chu_ho_moi.getValue(),resultSet.getString(1),resultSet.getNString(2),"Chủ hộ",resultSet.getString(4),gioi));
                danh_sach_duoi.add(new thanh_vien_cua_ho_cell(choice_box_chu_ho_moi.getValue(),resultSet.getString(1),resultSet.getNString(2),"Chủ hộ",resultSet.getString(4),gioi));
                for(thanh_vien_cua_ho_cell it:danh_sach_tren){
                    if(it.getmaNhanKhau().equals(choice_box_chu_ho_moi.getValue())) {
                        danh_sach_tren.remove(it);
                        break;
                    }
                }
                nguoi_in_listView_new.setCellFactory(param-> new thanh_vien_cua_ho_cell_factory());
            }
        }catch (Exception e){
            System.out.println("savhcfbfasvb");
            e.printStackTrace();
        }
    }




}
