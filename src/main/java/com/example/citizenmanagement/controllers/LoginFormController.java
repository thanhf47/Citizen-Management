package com.example.citizenmanagement.controllers;

import com.example.citizenmanagement.models.DatabaseConnection;
import com.example.citizenmanagement.models.DigitalClock;
import com.example.citizenmanagement.models.Model;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.TranslateTransition;
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
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class LoginFormController implements Initializable {

    private TranslateTransition transition;
    private Alert alert;

//    private DatabaseConnection connectNow;
//    private Connection connectDB;

    @FXML
    private Button login_btn;

    @FXML
    private Label login_errorAlert;

    @FXML
    private Hyperlink login_forgotPassword;

    @FXML
    private AnchorPane login_form;

    @FXML
    private PasswordField login_password_hidden;

    @FXML
    private TextField login_password_show;

    @FXML
    private ImageView login_showedPassIcon;

    @FXML
    private Label login_switchRegister;

    @FXML
    private TextField login_username;

    @FXML
    private ImageView login_hiddenPassIcon;

    @FXML
    private Button register_btn;

    @FXML
    private PasswordField register_confirm;

    @FXML
    private Label register_errorAlert;

    @FXML
    private TextField register_fullname;

    @FXML
    private AnchorPane register_form;

    @FXML
    private PasswordField register_password;

    @FXML
    private Label register_switchLogin;

    @FXML
    private TextField register_username;

    @FXML
    private Label timeLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private AnchorPane slider;

    @FXML
    private TextField register_phoneNumber;


    //login form
    @FXML
    void onLoginBtnClicked(MouseEvent event) {
        // complete later

        // Trong trường hợp người dùng nhập mật khẩu ở chế độ nhìn thấy,
        // phải gán lại cho login_password_hidden để so sánh
        if (!login_password_hidden.isVisible()) {
            login_password_hidden.setText(login_password_show.getText());
        }

        if (login_username.getText().isBlank()) {
            login_errorAlert.setText("Vui lòng điển đầy đủ tên đăng nhập.");
            login_errorAlert.setVisible(true);
        }
        else if (login_password_hidden.getText().isBlank()){
            login_errorAlert.setText("Vui lòng điền đầy đủ mật khẩu.");
            login_errorAlert.setVisible(true);
        }

        // login successfully!

        if (login_username.getText().isBlank() == false && login_password_hidden.getText().isBlank() == false){
//            if (verifyAccount()) {
                Stage stage = (Stage) login_btn.getScene().getWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
                Model.getInstance().getViewFactory().showMainWindow();
                //Model.getInstance().getViewFactory().showFeeWindow();
//            }
//            else {
//                login_errorAlert.setText("Tên đăng nhập hoặc mật khẩu không đúng.");
//                login_errorAlert.setVisible(true);
//            }
        }

    }
    @FXML
    void switchRegisterForm() {
        register_form.setVisible(true);
        transition = new TranslateTransition();
        transition.setNode(slider);
        transition.setToX(400);
        transition.setDuration(Duration.seconds(.6));
        transition.setOnFinished(e->{
            //hide login form
            login_username.setText("");
            login_password_show.setText("");
            login_password_hidden.setText("");
            login_errorAlert.setVisible(false);
            login_form.setVisible(false);

            register_btn.requestFocus(); //con trỏ chuột ban đầu đặt ở đây.
        });
        transition.play();
    }

    @FXML
    void switchLoginForm() {
        login_form.setVisible(true);
        transition = new TranslateTransition();
        transition.setNode(slider);
        transition.setToX(0);
        transition.setDuration(Duration.seconds(.6));
        transition.setOnFinished(e->{
            //reset register form ans hide.
            register_fullname.setText("");
            register_username.setText("");
            register_password.setText("");
            register_confirm.setText("");
            register_phoneNumber.setText("");
            register_errorAlert.setVisible(false);
            register_form.setVisible(false);
            login_btn.requestFocus(); //con trỏ chuột ban đầu đặt ở đây.
        });
        transition.play();
    }

//    @FXML
//    void checkPhoneNumber(KeyEvent keyEvent) {
//        String character = keyEvent.getCharacter();
//        if (!character.matches("[0-9]")) {
//            keyEvent.consume();
//        }
//    }

    //kiểm tra register form có oke chưa
    Boolean checkRegister() {

        if (register_fullname.getText().isBlank()) {
            register_errorAlert.setText("Vui lòng điền đầy đủ họ và tên.");
            return false;
        }
        else if (register_phoneNumber.getText().isBlank()) {
            register_errorAlert.setText("Vui lòng điền đầy đủ số điện thoại.");
            return false;
        }
        else if (register_username.getText().isBlank()) {
            register_errorAlert.setText("Vui lòng điền đầy đủ tên đăng nhập.");
            return false;
        }
        else if (register_password.getText().isBlank()) {
            register_errorAlert.setText("Vui lòng điền đầy đủ mật khẩu.");
            return false;
        }
        else if (register_confirm.getText().isBlank()) {
            register_errorAlert.setText("Vui lòng xác nhận mật khẩu.");
            return false;
        }
        else if (!register_confirm.getText().equals(register_password.getText())) {
            register_errorAlert.setText("Mật khẩu của bạn chưa xác nhận đúng.");
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
            register_errorAlert.setVisible(false);
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Đăng ký thành công!");
            alert.showAndWait();
            //switch to login form.
            switchLoginForm();
        }
    }

    @FXML
    void onShowedPasswordIconClicked() {
        login_showedPassIcon.setVisible(false);
        login_hiddenPassIcon.setVisible(true);
        login_password_hidden.setText(login_password_show.getText());
        login_password_hidden.setVisible(true);
        login_password_show.setVisible(false);
    }
    @FXML
    void onHiddenPasswordIconClicked() {
        login_hiddenPassIcon.setVisible(false);
        login_showedPassIcon.setVisible(true);
        login_password_show.setText(login_password_hidden.getText());
        login_password_show.setVisible(true);
        login_password_hidden.setVisible(false);
    }
    @FXML
    void onForgotClicked() {

    }

//    private boolean verifyAccount() {
//        String query = "SELECT COUNT(*)\n" +
//                "FROM UserAccount\n" +
//                "WHERE USERNAME = '" + login_username.getText() + "' AND PASSWORD = '" + login_password_hidden.getText() + "';";
//
//        try {
//            Statement statement = connectDB.createStatement();
//            ResultSet queryResult = statement.executeQuery(query);
//
//            while(queryResult.next()) {
//                if (queryResult.getInt(1) == 1) {
//                    return true;
//                }
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return false;
//    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_errorAlert.setVisible(false);
        login_form.setVisible(true);
        register_form.setVisible(false);
        register_errorAlert.setVisible(false);
        slider.setVisible(true);

        login_showedPassIcon.setVisible(false);
        login_hiddenPassIcon.setVisible(true);

        DigitalClock.TimeRunning(timeLabel, dateLabel);

//        connectNow = new DatabaseConnection();
//        connectDB = connectNow.getConnection();

    }
}
