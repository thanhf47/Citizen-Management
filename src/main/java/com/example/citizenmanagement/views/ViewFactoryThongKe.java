package com.example.citizenmanagement.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.lang.reflect.AnnotatedArrayType;

public class ViewFactoryThongKe {
    private AnchorPane thongKeNhanKhauView;
    private AnchorPane thongKeHoKhauView;

    private AnchorPane thongKeTamTruView;
    private AnchorPane thongKeTamVangView;
    public AnchorPane getThongKeNhanKhauView() {
        if (thongKeNhanKhauView == null) {
            try {
                thongKeNhanKhauView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/ThongKeNhanKhau.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return thongKeNhanKhauView;
    }

    public AnchorPane getThongKeHoKhauView() {
        if (thongKeHoKhauView == null) {
            try {
                thongKeHoKhauView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/ThongKeHoKhau.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return thongKeHoKhauView;
    }

    public AnchorPane getThongKeTamTruView(){
        if(thongKeTamTruView == null){
            try{
                thongKeTamTruView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/ThongKeTamTruNam2023.fxml")).load();
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        return thongKeTamTruView;
    }

    public AnchorPane getThongKeTamVangView(){
        if(thongKeTamVangView == null){
            try{
                thongKeTamVangView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/ThongKeTamVangNam2023.fxml")).load();
            } catch(IOException e){
                throw new RuntimeException(e);
            }
        }
        return thongKeTamVangView;
    }







}
