package com.example.citizenmanagement.controllers.maincontrollers;

import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private TextField ho_ten;

    @FXML
    private Circle info_image;

    @FXML
    private Button quay_lai;

    @FXML
    private TextField so_dien_thoai;

    @FXML
    private TextField ten_dang_nhap;

    @FXML
    private TextField vai_tro;

    private FileChooser fileChooser;
    @FXML
    private void onQuayLaiBtn() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.TRANG_CHU);
    }
    @FXML
    private void setInfo_image() {
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Model.getInstance().getImageObjectProperty().set(
                    new Image(selectedFile.toURI().toString(), 60, 60, false, true)
            );

            info_image.setFill(new ImagePattern(new Image(selectedFile.toURI().toString(),  300, 300, false, true)));
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fileChooser = new FileChooser();
        fileChooser.setTitle("Chọn bức ảnh của bạn");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));

        ten_dang_nhap.setDisable(true);
        ho_ten.setDisable(true);
        vai_tro.setDisable(true);
        so_dien_thoai.setDisable(true);

        ten_dang_nhap.setText(Model.getInstance().getCitizenManager().getTenDangNhap());
        ho_ten.setText(Model.getInstance().getCitizenManager().getHoTen());
        if (Model.getInstance().getCitizenManager().getVaiTro() == 1) {
            vai_tro.setText("Quản lý dân cư");
        }
        else if (Model.getInstance().getCitizenManager().getVaiTro() == 0) {
            vai_tro.setText("Quản lý thu phí");
        }
        so_dien_thoai.setText(Model.getInstance().getCitizenManager().getSoDienThoai_hide());

        try {
            info_image.setFill(new ImagePattern(new Image(getClass().getResource("/images/login_form/profile.png").toURI().toString(), 300, 300, false, true)));
            Model.getInstance().getImageObjectProperty().set(
                new Image(getClass().getResource("/images/login_form/profile.png").toURI().toString(), 60, 60, false, true)
            );
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
