package com.example.citizenmanagement.controllers.maincontrollers.hoKhau;

import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import com.example.citizenmanagement.models.MainHoKhauCell;
import com.example.citizenmanagement.models.thanh_vien_cua_ho_cell;
import com.example.citizenmanagement.views.thanh_vien_cua_ho_cell_factory;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

public class xemChiTietHokhauControler implements Initializable {
    public ListView<thanh_vien_cua_ho_cell> listView_thanhvien;
    public TextField ma_ho_khau;
    public TextField ma_chu_ho;
    public TextField ten_chu_ho;
    public TextField dia_chi;
    public TextField ngay_tao;
    public TextField ghi_chu;
    public Button xoa_btn;
    public Button thaydoi_but;
    public Button quay_lai_but;
    public Button tach_btn;

    private MainHoKhauCell tam;

    private Alert alert;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //******************************************************
        tam=Model.getHoKhauDuocChon();

        cap_nhat();
        if(listView_thanhvien.getItems().size() == 1) {
            tach_btn.setVisible(false);
        }
        ma_ho_khau.setText(String.valueOf(tam.getId().get()));
        ten_chu_ho.setText(String.valueOf(tam.getOwner().get()));
        dia_chi.setText(String.valueOf(tam.getAddress().get()));
        ngay_tao.setText(String.valueOf(tam.getDate_tao().get()));
        ghi_chu.setText(String.valueOf(tam.getGhi_chu().get()));
        ma_chu_ho.setText(Model.getInstance().getDatabaseConnection().lay_chu_ho(tam.getId().get()));




        xoa_btn.setOnAction(event -> {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Alert");
            alert.setHeaderText("Ông chắc chưa?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK) {
                ObservableList<thanh_vien_cua_ho_cell> danh_sach = listView_thanhvien.getItems();
                for (int i = 0; i < danh_sach.size(); i++) {
                    thanh_vien_cua_ho_cell tam = danh_sach.get(i);
                    Model.getInstance().getDatabaseConnection().xoa_thanh_vien_cua_ho(tam.getmaNhanKhau());
                }
                int ketqua = Model.getInstance().getDatabaseConnection().xoaHoKhau(tam.getId().get());

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Thông báo");
                alert.setContentText("Đã xóa thành công!");
                alert.showAndWait();
                listView_thanhvien.getItems().clear();
                ma_ho_khau.setText(null);
                ma_chu_ho.setText(null);
                dia_chi.setText(null);
                ngay_tao.setText(null);
                ghi_chu.setText(null);
                ten_chu_ho.setText(null);

                Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.HO_KHAU);
            }
        });



        thaydoi_but.setOnAction(event -> {
            Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.THAY_DOI_HO_KHAU);
        });

        quay_lai_but.setOnAction(event->{
            Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.HO_KHAU);
        });
        tach_btn.setOnAction(event -> {
            Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.TACH_HO_KHAU);
        });
    }
    public void cap_nhat(){
        String gioi_tinh;
        try {
            ResultSet resultSet = Model.getInstance().getDatabaseConnection().lay_cac_thanh_vien(String.valueOf(tam.getId().get()));
            listView_thanhvien.getItems().clear();
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
                        listView_thanhvien.getItems().add(new thanh_vien_cua_ho_cell(resultSet.getString(1),cccd, hoTen, quanHe,ngaySinh,gioi_tinh));
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

        listView_thanhvien.setCellFactory(param-> new thanh_vien_cua_ho_cell_factory());
    }
}
