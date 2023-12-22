package com.example.citizenmanagement.controllers;

import com.example.citizenmanagement.models.DigitalClock;
import com.example.citizenmanagement.models.Model;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginFormController implements Initializable {

    private TranslateTransition transition;
    private Alert alert;
    private int forgot_vaiTro;

    private int register_vaiTro;

    @FXML
    private Label dateLabel;

    @FXML
    private Label forgot_backBtn1;

    @FXML
    private Label forgot_backBtn2;

    @FXML
    private PasswordField forgot_confirm;

    @FXML
    private Button forgot_confirmBtn1;

    @FXML
    private Button forgot_confirmBtn2;

    @FXML
    private Label forgot_errorLbl1;

    @FXML
    private Label forgot_errorLbl2;

    @FXML
    private TextField forgot_fullname;

    @FXML
    private PasswordField forgot_password;

    @FXML
    private TextField forgot_phoneNumber;

    @FXML
    private ToggleGroup forgot_role;

    @FXML
    private RadioButton forgot_role0;

    @FXML
    private RadioButton forgot_role1;

    @FXML
    private TextField forgot_username;

    @FXML
    private Button login_btn;

    @FXML
    private Label login_errorAlert;

    @FXML
    private Hyperlink login_forgotPassword;

    @FXML
    private AnchorPane login_form;

    @FXML
    private ImageView login_hiddenPassIcon;

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
    private Button register_btn;

    @FXML
    private PasswordField register_confirm;

    @FXML
    private Label register_errorAlert;

    @FXML
    private AnchorPane register_form;

    @FXML
    private AnchorPane forgot_form1;

    @FXML
    private AnchorPane forgot_form2;

    @FXML
    private TextField register_fullname;

    @FXML
    private PasswordField register_password;

    @FXML
    private TextField register_phoneNumber;

    @FXML
    private TextField register_username;

    @FXML
    private ToggleGroup role;

    @FXML
    private Label timeLabel;

    @FXML
    private RadioButton role0;

    @FXML
    private RadioButton role1;

    @FXML
    private AnchorPane slider;




    //login form
    @FXML
    void onLoginBtnClicked() {
        slider.requestFocus();
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

        if (login_username.getText().isBlank() == false && login_password_hidden.getText().isBlank() == false){

            Model.getInstance().verifyManagerAccount(login_username.getText(), login_password_hidden.getText());

            if (Model.getInstance().getCitizenManagerLoginSuccessFlag()) {
                Stage stage = (Stage) login_btn.getScene().getWindow();
                Model.getInstance().getViewFactory().closeStage(stage);

                if (Model.getInstance().getCitizenManager().getVaiTro() == 1) {
                    Model.getInstance().getViewFactory().showMainWindow();
                }
                else if (Model.getInstance().getCitizenManager().getVaiTro() == 0) {
                    Model.getInstance().getViewFactory().showFeeWindow();
                }
            }
            else {
                login_errorAlert.setText("Tên đăng nhập hoặc mật khẩu không đúng.");
                login_errorAlert.setVisible(true);
            }
        }

    }
    @FXML
    void switchRegisterForm() {
        slider.requestFocus();
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

        });
        transition.play();
    }

    @FXML
    void switchLoginForm() {
        slider.requestFocus();
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
            role0.setSelected(false);
            role1.setSelected(false);
            register_errorAlert.setVisible(false);
            register_form.setVisible(false);
        });
        transition.play();
    }


    //kiểm tra register form có oke chưa
    Boolean checkRegister() {

        if (register_fullname.getText().isEmpty()) {
            register_errorAlert.setText("Vui lòng điền đầy đủ họ và tên.");
            return false;
        }
        else if (register_phoneNumber.getText().isEmpty()) {
            register_errorAlert.setText("Vui lòng điền đầy đủ số điện thoại.");
            return false;
        }
        else if (register_username.getText().isEmpty()) {
            register_errorAlert.setText("Vui lòng điền đầy đủ tên đăng nhập.");
            return false;
        }
        else if (register_password.getText().isEmpty()) {
            register_errorAlert.setText("Vui lòng điền đầy đủ mật khẩu.");
            return false;
        }
        else if (register_confirm.getText().isEmpty()) {
            register_errorAlert.setText("Vui lòng xác nhận mật khẩu.");
            return false;
        }
        else if (!register_confirm.getText().equals(register_password.getText())) {
            register_errorAlert.setText("Xác nhận mật khẩu sai");
            return false;
        }
        else if (role.getSelectedToggle() == null) {
            register_errorAlert.setText("Vui lòng chọn Vai trò");
            return false;
        }
        return true;
    }
    @FXML
    void onRegisterBtnClicked() {
        slider.requestFocus();
        if (!checkRegister()) {
            register_errorAlert.setVisible(true);
        }
        else{
            if(Model.getInstance().checkManagerUsernameExisted(register_username.getText())){
                register_errorAlert.setText("Tên đăng nhập đã tồn tại.");
                register_errorAlert.setVisible(true);
            }
            else {
                if (role.getSelectedToggle().equals(role0)) register_vaiTro = 0;
                else if (role.getSelectedToggle().equals(role1)) register_vaiTro = 1;
                Model.getInstance().registerManagerAccount(
                        register_fullname.getText(),
                        register_username.getText(),
                        register_password.getText(),
                        register_phoneNumber.getText(),
                        register_vaiTro
                        );
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
        slider.requestFocus();

        login_form.setVisible(false);
        login_username.setText("");
        login_password_show.setText("");
        login_password_hidden.setText("");
        login_errorAlert.setVisible(false);

        forgot_form1.setVisible(true);
    }

    @FXML
    void onForgotConfirmBtn1() {
        slider.requestFocus();

        if(checkForgotForm1()) {

            if (forgot_role.getSelectedToggle().equals(forgot_role0)) forgot_vaiTro = 0;
            else if (forgot_role.getSelectedToggle().equals(forgot_role1)) forgot_vaiTro = 1;

            if (Model.getInstance().checkManagerAccountExisted(
                    forgot_fullname.getText(),
                    forgot_username.getText(),
                    forgot_phoneNumber.getText(),
                    forgot_vaiTro
            )) {
                forgot_form1.setVisible(false);
                forgot_form2.setVisible(true);
            } else {
                forgot_errorLbl1.setText("Không tồn tại tài khoản.");
                forgot_errorLbl1.setVisible(true);
            }
        }
        else {
            forgot_errorLbl1.setVisible(true);
        }
    }

    private boolean checkForgotForm1() {
        if (forgot_fullname.getText().isBlank()) forgot_errorLbl1.setText("Bạn chưa điền Họ và tên");
        else if (forgot_username.getText().isBlank()) forgot_errorLbl1.setText("Bạn chưa điền Tên đăng nhập");
        else if (forgot_phoneNumber.getText().isBlank()) forgot_errorLbl1.setText("Bạn chưa điền Số điện thoại");
        else if (forgot_role.getSelectedToggle() == null) forgot_errorLbl1.setText("Bạn chưa chọn Vai trò");
        else return true;

        return false;

    }

    @FXML
    void onForgotConfirmBtn2() {
        slider.requestFocus();
        if (checkForgotForm2()) {
            Model.getInstance().updateManagerAccountPassword(
                    forgot_fullname.getText(),
                    forgot_username.getText(),
                    forgot_phoneNumber.getText(),
                    forgot_vaiTro,
                    forgot_password.getText()
            );

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Đổi mật khẩu thành công!");
            alert.showAndWait();

            //switch to login
            forgot_fullname.setText("");
            forgot_username.setText("");
            forgot_phoneNumber.setText("");
            forgot_role0.setSelected(false);
            forgot_role1.setSelected(false);
            forgot_password.setText("");
            forgot_confirm.setText("");

            forgot_form1.setVisible(false);
            forgot_form2.setVisible(false);
            login_form.setVisible(true);
        }
        else {
            forgot_errorLbl2.setVisible(true);
        }
    }

    private boolean checkForgotForm2() {
        if (forgot_password.getText().isBlank()) forgot_errorLbl2.setText("Bạn chưa điền mật khẩu mới");
        else if (forgot_confirm.getText().isBlank()) forgot_errorLbl2.setText("Bạn chưa xác nhận mật khẩu");
        else if (!forgot_confirm.getText().equals(forgot_password.getText())) forgot_errorLbl2.setText("Xác nhận mật khẩu không đúng");
        else return true;

        return false;
    }

    @FXML
    void onForgotBackBtn1Clicked() {

        slider.requestFocus();

        login_form.setVisible(true);
        forgot_form1.setVisible(false);

        login_username.setText("");
        login_password_hidden.setText("");
        login_password_show.setText("");

        forgot_errorLbl1.setVisible(false);

        forgot_fullname.setText("");
        forgot_username.setText("");
        forgot_phoneNumber.setText("");
        forgot_role0.setSelected(false);
        forgot_role1.setSelected(false);

    }

    @FXML
    void onForgotBackBtn2Clicked() {
        forgot_form1.setVisible(true);
        forgot_form2.setVisible(false);
        forgot_errorLbl1.setVisible(false);
        forgot_password.setText("");
        forgot_confirm.setText("");

        slider.requestFocus();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(Model.getInstance().getCitizenManager().getHoTen());

        login_errorAlert.setVisible(false);
        login_form.setVisible(true);
        register_form.setVisible(false);
        register_errorAlert.setVisible(false);
        forgot_errorLbl1.setVisible(false);
        forgot_errorLbl2.setVisible(false);
        forgot_form1.setVisible(false);
        forgot_form2.setVisible(false);
        slider.setVisible(true);

        login_showedPassIcon.setVisible(false);
        login_hiddenPassIcon.setVisible(true);

        DigitalClock.TimeRunning(timeLabel, dateLabel);


        forgot_vaiTro = -1;
        register_vaiTro = -1;


        slider.requestFocus();
    }
}
