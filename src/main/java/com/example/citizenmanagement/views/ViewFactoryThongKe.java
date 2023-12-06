package com.example.citizenmanagement.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ViewFactoryThongKe {
    private AnchorPane thongKeNhanKhauView;
    private AnchorPane thongKeHoKhauView;
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


}
