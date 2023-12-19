package com.example.citizenmanagement.controllers.feecontrollers;

import com.example.citizenmanagement.models.FeeMenuOptions;
import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class FeeTrangChuController implements Initializable {


    public Label tongSoHoKhau;
    public Label tongSoNhanKhau;
    public Label tongSoTienDaThu;
    public Label tongSoLoaiPhi;
    @FXML
    private Circle profile_thuphi;
    @FXML
    private Label name_thuphi;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        displayName();

        Model.getInstance().getImageObjectProperty().addListener((observable, oldValue, newValue) -> {
            profile_thuphi.setFill(new ImagePattern(newValue));
            System.out.println("change");
        });

        try {
            profile_thuphi.setFill(new ImagePattern(new Image(getClass().getResource("/images/login_form/profile.png").toURI().toString(), 60, 60, false, true)));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        //----------------------------------------------------//
        // hien thi cac thong so tren bang tom tat

        tongSoLoaiPhi.setText(Integer.toString(Model.getInstance().getNumberOfCacLoaiPhi()));
        tongSoTienDaThu.setText(Integer.toString(Model.getInstance().getTongSoTienDaThuPhi()) + " VND");
        tongSoNhanKhau.setText(Integer.toString(Model.getInstance().getNumberOfNhanKhau()));
        tongSoHoKhau.setText(Integer.toString(Model.getInstance().getNumberOfHoKhau()));


    }

    private void displayName() {
        String[] parts = Model.getInstance().getCitizenManager().getHoTen().trim().split(" ");

        if (parts.length > 0) {
            name_thuphi.setText(parts[parts.length - 1]);
        }
    }
    public void onProFile_Fee(MouseEvent mouseEvent) {
        Model.getInstance().getViewFactory().getFeeSelectedMenuItem().set(FeeMenuOptions.FEE_PROFILE);
    }




}
