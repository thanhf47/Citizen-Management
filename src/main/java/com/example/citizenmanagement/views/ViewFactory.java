package com.example.citizenmanagement.views;

import com.example.citizenmanagement.controllers.maincontrollers.MainController;
import com.example.citizenmanagement.models.FeeMenuOptions;
import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.hoKhauOptions;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

// THIS CLASS IS USED TO MANAGE UI COMPONENT.
public class ViewFactory {

    //Main Citizen Views
    private final ObjectProperty<MainMenuOptions>    selectedMenuItem;
    private AnchorPane trangChuView;
    private AnchorPane nhanKhauView;
    private AnchorPane hoKhauView;
    private  BorderPane Main;
    //Ho Khau

    // Fee Views
    private final ObjectProperty<FeeMenuOptions> feeSelectedMenuItem;
    private AnchorPane feeTrangChuView;
    private AnchorPane feeDanhSachView;
    private AnchorPane feeThemKhoanThuView;
    private AnchorPane feeThemHoKhauView;
    /********************************************************************************************/
    public ViewFactory(){
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
    /*public AnchorPane getHoKhauView() {
        if (hoKhauView == null) {
            try {
                hoKhauView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/hoKhau/hoKhauShow.fxml")).load();
            } catch (IOException e) {
              e.printStackTrace();
            }
        }
        return hoKhauView;
    }*/
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
    public BorderPane getMain(){
        /*if(Main==null){
            try {
                Main = new FXMLLoader(getClass().getResource("/fxml/main_citizen/Main.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }*/
        return Main;
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
    public AnchorPane getFeeDanhSachView() {
        if (feeDanhSachView == null) {
            try {
                feeDanhSachView = new FXMLLoader(getClass().getResource("/fxml/fee/FeeDanhSach.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return feeDanhSachView;
    }
    public AnchorPane getFeeThemKhoanThuView() {
        if (feeThemKhoanThuView == null) {
            try {
                feeThemKhoanThuView = new FXMLLoader(getClass().getResource("/fxml/fee/FeeThemKhoanThu.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return feeThemKhoanThuView;
    }
    public AnchorPane getFeeThemHoKhauView() {
        if (feeThemHoKhauView == null) {
            try {
                feeThemHoKhauView = new FXMLLoader(getClass().getResource("/fxml/fee/FeeThemHoKhau.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return feeThemHoKhauView;
    }


    /********************************************************************************************/
    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login/Login.fxml"));
        createStage(loader);
    }

    public void showMainWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main_citizen/Main.fxml"));
        try {
            Main = loader.load();
        }catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        Scene scene=new Scene(Main);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
    public void showFeeWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fee/Fee.fxml"));
        createStage(loader);
    }
    public void showHoKhau(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main_citizen/hoKhau/hoKhauConCac.fxml"));
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

