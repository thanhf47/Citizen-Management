package com.example.citizenmanagement.controllers.maincontrollers.hoKhau;

import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;

import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class themMoiHoKhauControler implements Initializable {

    public Button cancel_but;
    public TextField id_chu_ho_text;
    public TextField date_them_text;
    public TextField add_text;
    public TextField ghi_chu_text;
    public ListView listView_to_chon;
    public ListView listView_to_them;
    public Button chuyen_lai;
    public Button chuyen_sang;
    public TextField search_text;
    public Button xac_nhan_but;
    public Label error_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cancel_but.setOnAction(event -> {
            Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.HO_KHAU);

        });
        xac_nhan_but.setOnAction(event -> {
            int ketqua=0;
            ketqua = Model.getInstance().getDatabaseConnection().addHoKhau(id_chu_ho_text.getText(),date_them_text.getText(),add_text.getText(),ghi_chu_text.getText());
            id_chu_ho_text.setText("");
            date_them_text.setText("");
            add_text.setText("");
            ghi_chu_text.setText("");
            if(ketqua==0){
                error_lbl.setText("Ban da khong them thanh cong!");
            }
            else
                Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.HO_KHAU);
        });
    }
}
