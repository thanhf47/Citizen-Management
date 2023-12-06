package com.example.citizenmanagement.controllers.maincontrollers.hoKhau;

import com.example.citizenmanagement.models.Model;

import com.example.citizenmanagement.models.hoKhauCell;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
    public Button search_but;
    public Button xac_nhan_but;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cancel_but.setOnAction(event -> {
            Stage stage = (Stage)cancel_but.getScene().getWindow();
            BorderPane tam = Model.getInstance().getViewFactory().getMain();
            tam.setCenter(Model.getInstance().getViewHK().getHoKhauShow());
            Scene scene = new Scene(tam);
            stage.setScene(scene);
        });
        xac_nhan_but.setOnAction(event -> {
            Stage stage = (Stage)xac_nhan_but.getScene().getWindow();
            BorderPane tam = Model.getInstance().getViewFactory().getMain();
            tam.setCenter(Model.getInstance().getViewHK().getHoKhauShow());
            Scene scene = new Scene(tam);
            stage.setScene(scene);
        });
    }
}
