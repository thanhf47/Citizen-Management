package com.example.citizenmanagement.views;

import com.example.citizenmanagement.models.FeeMenuOptions;
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

    //Main Citizen Views
    private final ObjectProperty<MainMenuOptions> selectedMenuItem;
    private AnchorPane trangChuView;
    private AnchorPane nhanKhauView;
    private AnchorPane hoKhauView;

    // Fee Views
    private final ObjectProperty<FeeMenuOptions> feeSelectedMenuItem;
    private AnchorPane feeTrangChuView;
    private AnchorPane feeVeSinhView;
    private AnchorPane feeDongGopView;
    /********************************************************************************************/
    public ViewFactory() {
        this.selectedMenuItem = new SimpleObjectProperty<>();
        this.feeSelectedMenuItem = new SimpleObjectProperty<>();
    }
    /********************************************************************************************/
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
    /********************************************************************************************/
    public ObjectProperty<FeeMenuOptions> getFeeSelectedMenuItem(){
        return feeSelectedMenuItem;
    }
    public AnchorPane getFeeTrangChuView() {
        if (feeTrangChuView == null) {
            try {
                feeTrangChuView = new FXMLLoader(getClass().getResource("/fxml/fee/FeeTrangChu.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return feeTrangChuView;
    }
    public AnchorPane getFeeVeSinhView() {
        if (feeVeSinhView == null) {
            try {
                feeVeSinhView = new FXMLLoader(getClass().getResource("/fxml/fee/FeeVeSinh.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return feeVeSinhView;
    }
    public AnchorPane getFeeDongGopView() {
        if (feeDongGopView == null) {
            try {
                feeDongGopView = new FXMLLoader(getClass().getResource("/fxml/fee/FeeDongGop.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return feeDongGopView;
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

