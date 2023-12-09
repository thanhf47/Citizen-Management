package com.example.citizenmanagement.controllers.maincontrollers;

import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;

import javafx.fxml.Initializable;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ThongKeHoKhauController implements Initializable {
    public StackedBarChart thongKeHoKhau;

    public Button QuayLai2;
    public Text tongsohokhau_text;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showBieuDoNhanKhau();

        QuayLai2.setOnAction(event -> onQuayLai1());
        tongsohokhau_text.setText(Integer.toString(Model.getInstance().getNumberOfHoKhau()));

    }

    void showBieuDoNhanKhau(){
        int namhientai = Model.getInstance().getNamHienTai();
        final XYChart.Series<String,Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>(Integer.toString(namhientai-4),Model.getInstance().getHoKhauOfNam(namhientai-4)));
        series.getData().add(new XYChart.Data<>(Integer.toString(namhientai-3),Model.getInstance().getHoKhauOfNam(namhientai-3)));
        series.getData().add(new XYChart.Data<>(Integer.toString(namhientai-2),Model.getInstance().getHoKhauOfNam(namhientai-2)));
        series.getData().add(new XYChart.Data<>(Integer.toString(namhientai-1),Model.getInstance().getHoKhauOfNam(namhientai-1)));
        series.getData().add(new XYChart.Data<>(Integer.toString(namhientai),Model.getInstance().getHoKhauOfNamHienTai()));
        thongKeHoKhau.getData().add(series);

    }

    public  void onQuayLai1(){
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.TRANG_CHU);
    }
}
