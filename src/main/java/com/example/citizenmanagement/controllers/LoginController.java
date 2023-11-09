package com.example.citizenmanagement.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button login_btn;

    @FXML
    private Label login_errorPasswordAlert;

    @FXML
    private Label login_errorUsernameAlert;

    @FXML
    private Hyperlink login_forgot_password;

    @FXML
    private AnchorPane login_form;

    @FXML
    private PasswordField login_password_hidden;

    @FXML
    private TextField login_password_show;

    @FXML
    private Label login_switchRegister;

    @FXML
    private TextField login_username;

    @FXML
    private Button register_btn;

    @FXML
    private TextField register_confirm;

    @FXML
    private AnchorPane register_form;

    @FXML
    private TextField register_password;

    @FXML
    private Label register_switchLogin;

    @FXML
    private TextField register_username;

    @FXML
    private AnchorPane slider;


    //login form
    @FXML
    void checkLogin(MouseEvent event) {
        // complete later

        //username field
        if (login_username.getText().isBlank()) {
            login_errorUsernameAlert.setVisible(true);
        }
        else {
            login_errorUsernameAlert.setVisible(false);
        }

        //password field
        if (login_password_hidden.getText().isBlank()) {
            login_errorPasswordAlert.setVisible(true);
        }
        else {
            login_errorPasswordAlert.setVisible(false);
        }

        // login successfully!

        if (login_username.getText().isBlank() == false && login_password_hidden.getText().isBlank() == false){
            Stage stage = (Stage) login_btn.getScene().getWindow();

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
                stage.setTitle("Citizen Management");
                stage.setScene(new Scene(root));
                System.out.println("successfully.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_errorUsernameAlert.setVisible(false);
        login_errorPasswordAlert.setVisible(false);

    }
}
