package com.example.citizenmanagement.views;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class NhankhauFactoryView {



    private AnchorPane tamTruView;
    private AnchorPane tamVangView;
    private AnchorPane themMoiView;
    private AnchorPane khaiTuView;




    public AnchorPane gettamVangView() {
        if (tamVangView == null) {
            try {
                tamVangView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/HoKhau/DkTamVang.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tamVangView;
    }

    public AnchorPane getthemMoiView() {
        if (themMoiView == null) {
            try {
                themMoiView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/HoKhau/ThemMoi.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return themMoiView;
    }

    public AnchorPane getkhaiTuView() {
        if (khaiTuView == null) {
            try {
                khaiTuView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/HoKhau/KhaiTu.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return khaiTuView;
    }

    public AnchorPane gettamTruView() {
        if (tamTruView == null) {
            try {
                tamTruView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/HoKhau/DkTamTru.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tamTruView;
    }

//    public void showTamtru() {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main_citizen/HoKhau/DkTamTru.fxml"));
//        createStage(loader);
//    }
//
//    public void showTamvang() {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main_citizen/HoKhau/DkTamVang.fxml"));
//        createStage(loader);
//    }

//    private void createStage(FXMLLoader loader) {
//        Stage stage = new Stage();
//        Scene scene;
//        try {
//            scene = new Scene(loader.load());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.setScene(scene);
//        stage.show();
//    }

//    public void closeStage(Stage stage) {
//        stage.close();
//    }
}


