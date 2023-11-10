package com.example.citizenmanagement.controllers.logincontroller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private TranslateTransition transition;
    private Alert alert;

    @FXML
    private Button login_btn;

    @FXML
    private Label login_errorPasswordAlert;

    @FXML
    private Label login_errorUsernameAlert;

    @FXML
    private Hyperlink login_forgotPassword;

    @FXML
    private AnchorPane login_form;

    @FXML
    private PasswordField login_password_hidden;

    @FXML
    private TextField login_password_show;

    @FXML
    private FontAwesomeIconView login_showPassword;

    @FXML
    private Label login_switchRegister;

    @FXML
    private TextField login_username;

    @FXML
    private Button register_btn;

    @FXML
    private TextField register_confirm;

    @FXML
    private Label register_errorAlert;

    @FXML
    private TextField register_firstname;

    @FXML
    private AnchorPane register_form;

    @FXML
    private TextField register_lastname;

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
    void onLoginBtnClicked(MouseEvent event) {
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
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
                stage.setTitle("Citizen Management");
                stage.setScene(new Scene(root));
                System.out.println("successfully.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

    @FXML
    void showPassword() {
        if (!login_password_hidden.getText().isBlank()) {
            login_password_show.setText(login_password_hidden.getText());
            login_password_show.setVisible(true);
            login_password_hidden.setVisible(false);
        }
    }
    @FXML
    void hidePassword() {
        if (!login_password_show.getText().isBlank()) {
            login_password_hidden.setVisible(true);
            login_password_show.setVisible(false);
        }
    }
    @FXML
    void switchRegisterForm() {
        register_form.setVisible(true);
        transition = new TranslateTransition();
        transition.setNode(slider);
        transition.setToX(400);
        transition.setDuration(Duration.seconds(1));
        transition.setOnFinished(e->{
            //hide login form
            login_username.setText("");
            login_password_show.setText("");
            login_password_hidden.setText("");
            login_form.setVisible(false);
        });
        transition.play();
    }

    @FXML
    void switchLoginForm() {
        login_form.setVisible(true);
        transition = new TranslateTransition();
        transition.setNode(slider);
        transition.setToX(0);
        transition.setDuration(Duration.seconds(1));
        transition.setOnFinished(e->{
            //reset register form ans hide.
            register_firstname.setText("");
            register_lastname.setText("");
            register_username.setText("");
            register_password.setText("");
            register_confirm.setText("");
            register_errorAlert.setVisible(false);
            register_form.setVisible(false);
        });
        transition.play();
    }

    //kiểm tra register form có oke chưa
    Boolean checkRegister() {
        if (register_firstname.getText().isBlank()) {
            register_errorAlert.setText("First name is required field.");
            return false;
        }
        else if (register_lastname.getText().isBlank()) {
            register_errorAlert.setText("Last name is required field.");
            return false;
        }
        else if (register_username.getText().isBlank()) {
            register_errorAlert.setText("Username is required field.");
            return false;
        }
        else if (register_password.getText().isBlank()) {
            register_errorAlert.setText("Password is required field.");
            return false;
        }
        else if (register_confirm.getText().isBlank()) {
            register_errorAlert.setText("Firstname is required field.");
            return false;
        }
        else if (!register_confirm.getText().equals(register_password.getText())) {
            register_errorAlert.setText("Your confirm password is incorrect.");
            return false;
        }
        return true;
    }
    @FXML
    void onRegisterBtnClicked() {
        if (!checkRegister()) {
            register_errorAlert.setVisible(true);
        }
        else{
            register_errorAlert.setVisible(true);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Register successfully!");
            alert.showAndWait();
            //switch to login form.
            switchLoginForm();
        }
    }
    @FXML
    void onForgotClicked() {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_errorUsernameAlert.setVisible(false);
        login_errorPasswordAlert.setVisible(false);
        login_form.setVisible(true);
        register_form.setVisible(false);
        register_errorAlert.setVisible(false);
        slider.setVisible(true);

    }
}
