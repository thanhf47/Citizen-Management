package com.example.citizenmanagement.views;

import com.example.citizenmanagement.models.MainMenuOptions;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

// THIS CLASS IS USED TO MANAGE UI COMPONENT.
public class ViewFactory {

    //Main Views
    private final ObjectProperty<MainMenuOptions> selectedMenuItem;
    private AnchorPane trangChuView;
    private AnchorPane nhanKhauView;
    private AnchorPane hoKhauView;

    public ObjectProperty<MainMenuOptions> getSelectedMenuItem() {return selectedMenuItem;}
    public AnchorPane getTrangChuView() {
        if (trangChuView == null) {
            try {
                trangChuView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/TrangChu.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return trangChuView;
    }
    public AnchorPane getHoKhauView() {
        if (hoKhauView == null) {
            try {
                hoKhauView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/HoKhau.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return hoKhauView;
    }
    public AnchorPane getNhanKhauView() {
        if (nhanKhauView == null) {
            try {
                nhanKhauView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/NhanKhau.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return nhanKhauView;
    }
    public ViewFactory() {
        this.selectedMenuItem = new SimpleObjectProperty<>();
    }
    /********************************************************************************************/
    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login/Login.fxml"));
        createStage(loader);
    }

    public void showMainWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main_citizen/Main.fxml"));
        createStage(loader);
    }
    public void showFeeWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fee/Fee.fxml"));
        createStage(loader);
    }

    private void createStage(FXMLLoader loader) {
        Stage stage = new Stage();
        Scene scene;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
    public void closeStage(Stage stage) { stage.close(); }
}

