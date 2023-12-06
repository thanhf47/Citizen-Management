package com.example.citizenmanagement.controllers.maincontrollers.hoKhau;

import com.example.citizenmanagement.controllers.maincontrollers.MainController;
import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import com.example.citizenmanagement.models.hoKhauCell;
import com.example.citizenmanagement.views.hoKhauCellFactory;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class hoKhauShowControler implements Initializable {
    public TextField search_textfield;
    public ListView<hoKhauCell> listView;
    public Button search_but;
    public Button tach_but;
    public Button them_but;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResultSet resultSet = Model.getInstance().getDataBCHK().getResultSet();
        listView.getItems().clear();
        try {
            if(resultSet.isBeforeFirst()){
                while (resultSet.next()){
                    String id = resultSet.getString(1);
                    String Owner = resultSet.getString(2);
                    String add = resultSet.getString(3);
                    String date_tao = resultSet.getString(4);
                    String date_chuyen = resultSet.getString(5);
                    String ghi_chu = resultSet.getString(6);
                    listView.getItems().add(new hoKhauCell(id, Owner, add,date_tao,date_chuyen,ghi_chu));
                }
            }
        } catch (Exception e) {
            System.out.println("loi o hokhauShow");
        }
        listView.setCellFactory(param-> new hoKhauCellFactory());
        //**************************************************************
        search_but.setOnAction(event -> {
            if(search_textfield.getText()!=null){
                ResultSet resultSet1=null;
                try {
                    resultSet1 = Model.getInstance().getDataBCHK().timKiem(search_textfield.getText());
                    if (resultSet1.isBeforeFirst()) {
                        resultSet1.next();
                        String id = resultSet1.getString(1);
                        String owner = resultSet1.getString(2);
                        String add = resultSet1.getString(3);
                        String date_tao = resultSet1.getString(4);
                        String date_chuyen = resultSet1.getString(5);
                        String ghi_chu = resultSet1.getString(6);
                        listView.getItems().clear();
                        listView.getItems().add(new hoKhauCell(id,owner,add,date_tao,date_chuyen,ghi_chu));
                        search_textfield.setText("");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                listView.setCellFactory(param-> new hoKhauCellFactory());

            }
        });
        //****************************************************

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            if(newValue!=null){
                Stage stage = (Stage)tach_but.getScene().getWindow();
                BorderPane tam = Model.getInstance().getViewFactory().getMain();
                tam.setCenter(Model.getInstance().getViewHK().getXemChiTietHoKhau());
                Scene scene = new Scene(tam);
                stage.setScene(scene);
            }
        });
        tach_but.setOnAction(event -> {
            /*Stage stage = (Stage) tach_but.getScene().getWindow();
            try {
                BorderPane tam1 = Model.getInstance().getViewFactory().getMain();
                tam1.setCenter(Model.getInstance().getViewHK().getTachHoKhau());
                stage.setScene(new Scene(tam1));
            }catch (Exception e){
                e.printStackTrace();
            }*/
            Model.getInstance().getViewFactory().getMain().setCenter(Model.getInstance().getViewHK().getTachHoKhau());
        });
        them_but.setOnAction(event -> {
            Stage stage = (Stage)tach_but.getScene().getWindow();
            BorderPane tam = Model.getInstance().getViewFactory().getMain();
            tam.setCenter(Model.getInstance().getViewHK().getThemHoKhau());
            Scene scene = new Scene(tam);
            stage.setScene(scene);
        });
    }
}
