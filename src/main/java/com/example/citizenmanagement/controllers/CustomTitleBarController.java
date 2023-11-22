package com.example.citizenmanagement.controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomTitleBarController implements Initializable {
    private double xOffset; // lưu vị trí con trỏ chuột ban đầu theo Ox
    private double yOffset; // lưu vị trí con trỏ chuột ban đầu theo Oy
    @FXML
    private FontAwesomeIconView closeBtn;

    @FXML
    private FontAwesomeIconView windowMinimizeBtn;
    @FXML
    private AnchorPane customBar;
    @FXML
    void onCloseBtnClicked() {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }
    @FXML
    void onWindowMinimizeBtnClicked() {
        Stage stage = (Stage) windowMinimizeBtn.getScene().getWindow();

        stage.setIconified(true);
    }
    @FXML
    void pressCustomBar(MouseEvent mouseEvent) {

        xOffset = mouseEvent.getSceneX();
        yOffset = mouseEvent.getSceneY();
    }
    @FXML
    void dragCustomBar(MouseEvent mouseEvent) {
        Stage stage = (Stage) customBar.getScene().getWindow();
        stage.setX(mouseEvent.getScreenX() - xOffset);
        stage.setY(mouseEvent.getScreenY() - yOffset);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
