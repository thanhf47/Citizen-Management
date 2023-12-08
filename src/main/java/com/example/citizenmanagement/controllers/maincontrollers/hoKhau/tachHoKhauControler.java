package com.example.citizenmanagement.controllers.maincontrollers.hoKhau;

import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
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
        cancel_but.setOnAction(event -> {
            Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.HO_KHAU);

        });
        xacNhan_but.setOnAction(event -> {
            int ketqua=0;
            ketqua=Model.getInstance().getDataBCHK().addHoKhau(id_chu_moi.getText(),ngay_tao.getText(),dia_chi.getText(),ghi_chu.getText());
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
}
