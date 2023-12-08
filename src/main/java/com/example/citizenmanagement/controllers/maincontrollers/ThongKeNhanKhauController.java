package com.example.citizenmanagement.controllers.maincontrollers;

import com.example.citizenmanagement.models.ThongKe;
import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ThongKeNhanKhauController implements Initializable {
    public TableView table_gioitinh;
    public TableColumn <ThongKe,String>gioitinh;

    public StackedBarChart bieudogioitinh;
    public PieChart bieudonhomtuoi;
    public TableView table_nhomtuoi;
    public TableColumn nhomtuoi;
    public TableColumn soluongnhomtuoi;
    public Button quaylai;
    public TableColumn soluonggioitinh;



    public void showThongKeGioiTinh(){
        int a1 = Model.getInstance().getNumberOfNhanKhauNam();
        int a2 = Model.getInstance().getNumberOfNhanKhauNu();
        final XYChart.Series<String,Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Nam", a1));
        series.getData().add(new XYChart.Data<>("Nữ",a2));
        bieudogioitinh.getData().add(series);

    }

    public void showTableGioiTinh(){
        int a1 = Model.getInstance().getNumberOfNhanKhauNam();
        int a2 = Model.getInstance().getNumberOfNhanKhauNu();
        ObservableList<ThongKe> list_gt = FXCollections.observableArrayList(
                new ThongKe("Nam",a1),
                new ThongKe("Nữ",a2)
        );

        gioitinh.setCellValueFactory(new PropertyValueFactory<ThongKe,String>("gioitinh"));
        soluonggioitinh.setCellValueFactory(new PropertyValueFactory<ThongKe,Integer>("soluonggioitinh"));
        table_gioitinh.setItems(list_gt);

    }
    public void showThongKeNhomTuoi(){
        ObservableList<PieChart.Data> piechartdata = FXCollections.observableArrayList(
                new PieChart.Data("Dưới 3 Tuổi",Model.getInstance().getNumberOfNhanKhauDuoi3Tuoi()),
                new PieChart.Data("Từ 3-10 Tuổi",Model.getInstance().getNumberOfNhanKhauTu3Den10Tuoi()),
                new PieChart.Data("Từ 10-18 Tuổi",Model.getInstance().getNumberOfNhanKhauTu10Den18Tuoi()),
                new PieChart.Data("Từ 18-60 Tuổi",Model.getInstance().getNumberOfNhanKhauTu18Den60Tuoi()),
                new PieChart.Data("Trên 60 Tuổi",Model.getInstance().getNumberOfNhanKhauTren60Tuoi())
        );
        bieudonhomtuoi.setData(piechartdata);
    }
    public void showTableNhomTuoi(){
        ObservableList<ThongKe> list_gt1 = FXCollections.observableArrayList(
                new ThongKe("Dưới 3 Tuổi",Model.getInstance().getNumberOfNhanKhauDuoi3Tuoi()),
                new ThongKe("Từ 3-10 Tuổi",Model.getInstance().getNumberOfNhanKhauTu3Den10Tuoi()),
                new ThongKe("Từ 10-18 Tuổi",Model.getInstance().getNumberOfNhanKhauTu10Den18Tuoi()),
                new ThongKe("Từ 18-60 Tuổi",Model.getInstance().getNumberOfNhanKhauTu18Den60Tuoi()),
                new ThongKe("Trên 60 Tuổi",Model.getInstance().getNumberOfNhanKhauTren60Tuoi())
        );

        nhomtuoi.setCellValueFactory(new PropertyValueFactory<ThongKe,String>("gioitinh"));
        soluongnhomtuoi.setCellValueFactory(new PropertyValueFactory<ThongKe,Integer>("soluonggioitinh"));
        table_nhomtuoi.setItems(list_gt1);

    }






    public void quaylaitrangchu() {
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.TRANG_CHU);
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showThongKeGioiTinh();
        showTableGioiTinh();
        quaylai.setOnAction(event -> quaylaitrangchu());
        showThongKeNhomTuoi();
        showTableNhomTuoi();

    }
}
