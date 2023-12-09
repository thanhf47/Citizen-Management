package com.example.citizenmanagement.controllers.maincontrollers;

import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


import java.net.URL;
import java.util.ResourceBundle;


public class TrangChuController implements Initializable {

    public Pane nhankhau;

    public Pane hokhau;
    public Pane tamtru;
    public Pane tamvang;
    public Text text_nhankhau;
    public Text text_hokhau;
    public Text text_tamtru;
    public Text text_tamvang;

    @FXML
    private Label name;

    @FXML
    void click_nhankhau(MouseEvent event) {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.THONG_KE_NHAN_KHAU);

    }


    @FXML
    void click_tamvang(MouseEvent event) {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.THONG_KE_TAM_VANG);
    }

    @FXML
    void click_hokhau(MouseEvent event) {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.THONG_KE_HO_KHAU);

    }

    @FXML
    void click_tamtru(MouseEvent event) {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.THONG_KE_TAM_TRU);
    }

    public void showNhanKhau(){
        text_nhankhau.setText(Integer.toString(Model.getInstance().getNumberOfNhanKhau()));
    }



    public void showHoKhau() {
        text_hokhau.setText(Integer.toString(Model.getInstance().getNumberOfHoKhau()));
    }

    public void showTamTru() {
        text_tamtru.setText(Integer.toString(Model.getInstance().getNumberOfTamTru()));
    }

    public void showTamVang() {
        text_tamvang.setText(Integer.toString(Model.getInstance().getNumberOfTamVang()));
    }



    public void initialize(URL url, ResourceBundle resourceBundle) {

        displayName();

        showNhanKhau();
        showHoKhau();
        showTamVang();
        showTamTru();
    }

    private void displayName() {
        String[] parts = Model.getInstance().getCitizenManager().getHoTen().trim().split(" ");

        if (parts.length > 0) {
            name.setText(parts[parts.length - 1]);
        }
    }


}

