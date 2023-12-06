package com.example.citizenmanagement.controllers.maincontrollers.hoKhau;

import com.example.citizenmanagement.models.Model;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cancel_but.setOnAction(event -> {
            Stage stage = (Stage)cancel_but.getScene().getWindow();
            BorderPane tam = Model.getInstance().getViewFactory().getMain();
            tam.setCenter(Model.getInstance().getViewHK().getHoKhauShow());
            Scene scene = new Scene(tam);
            stage.setScene(scene);
        });
        xacNhan_but.setOnAction(event -> {
            Stage stage = (Stage)xacNhan_but.getScene().getWindow();
            BorderPane tam = Model.getInstance().getViewFactory().getMain();
            tam.setCenter(Model.getInstance().getViewHK().getHoKhauShow());
            Scene scene = new Scene(tam);
            stage.setScene(scene);
        });
    }
}
