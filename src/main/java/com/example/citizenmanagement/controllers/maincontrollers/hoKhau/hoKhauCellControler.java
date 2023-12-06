package com.example.citizenmanagement.controllers.maincontrollers.hoKhau;

import com.example.citizenmanagement.models.hoKhauCell;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class hoKhauCellControler implements Initializable {
    public Text id_text;
    public Text name_owner_text;
    public Text add_text;
    public Text ngaytao;
    public Text ngaychuyen;
    public Text ghichu;
    private final hoKhauCell khauCell;
    public hoKhauCellControler(hoKhauCell khauCell){
        this.khauCell = khauCell;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id_text.setText(String.valueOf(khauCell.getId().get()));
        name_owner_text.setText(String.valueOf(khauCell.getOwner().get()));
        add_text.setText(String.valueOf(khauCell.getAddress().get()));
        ngaytao.setText(String.valueOf(khauCell.getDate_tao().get()));
        ngaychuyen.setText(String.valueOf(khauCell.getDate_chuyen().get()));
        ghichu.setText(String.valueOf(khauCell.getGhi_chu().get()));
    }
}
