package com.example.citizenmanagement;

import com.example.citizenmanagement.models.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Model.getInstance().getViewFactory().showLoginWindow();
//        Model.getInstance().getViewFactory().showFeeWindow();
//        Model.getInstance().getViewFactory().showMainWindow();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
