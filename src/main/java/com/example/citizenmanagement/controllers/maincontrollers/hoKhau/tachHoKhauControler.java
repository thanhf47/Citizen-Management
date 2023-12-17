package com.example.citizenmanagement.controllers.maincontrollers.hoKhau;

import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import com.example.citizenmanagement.models.thanh_vien_cua_ho_cell;
import com.example.citizenmanagement.views.thanh_vien_cua_ho_cell_factory;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class tachHoKhauControler implements Initializable {
    public TextField maHK_search_textField;
    public ListView nguoi_in_listView_now;
    public Button chuyen_sang_moi_but;
    public Button Chuyen_lai_but;
    public ListView nguoi_in_listView_new;

    public Button search_but;
    public Button cancel_but;
    public Button xacNhan_but;
    public TextField ghi_chu;
    public TextField ngay_tao;
    public TextField dia_chi;
    public TextField id_chu_moi;
    public Label error_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        search_but.setOnAction(actionEvent -> {
            cap_nhat();
        });

        chuyen_sang_moi_but.setOnAction(actionEvent -> {

        });
        cancel_but.setOnAction(event -> {
            Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.HO_KHAU);

        });
        xacNhan_but.setOnAction(event -> {
            int ketqua=0;
            ketqua=Model.getInstance().getDatabaseConnection().addHoKhau(id_chu_moi.getText(),ngay_tao.getText(),dia_chi.getText(),ghi_chu.getText());
            id_chu_moi.setText("");
            ngay_tao.setText("");
            dia_chi.setText("");
            ghi_chu.setText("");
            if(ketqua==0)
                error_lbl.setText("Khong tach thanh cong");
            else
                Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.HO_KHAU);

        });
    }

    public void cap_nhat(){
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
                            String gioiTinh = resultSet1.getString(3);
                            nguoi_in_listView_now.getItems().add(new thanh_vien_cua_ho_cell(cccd, hoTen, quanHe,ngaySinh,gioiTinh));
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
}
