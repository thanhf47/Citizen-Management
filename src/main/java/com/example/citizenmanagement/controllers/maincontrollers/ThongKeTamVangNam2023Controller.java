package com.example.citizenmanagement.controllers.maincontrollers;

import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import com.example.citizenmanagement.models.ThongKe;
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
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ThongKeTamVangNam2023Controller implements Initializable {
    public Text tongSoNhanKhau_text;
    public StackedBarChart bieuDoThongKeTamVangTheoThang;
    public PieChart thongKeTamVangTheoLyDo;
    public TableView<ThongKe> tableThongKeLyDoTamVang;
    public TableColumn<ThongKe,String> lyDoTamVang;
    public TableColumn<ThongKe,Integer> soLuongTamVang;
    public Button thoatTamVang;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tongSoNhanKhau_text.setText(Integer.toString(Model.getInstance().getNumberOfTamVang()));
        thoatTamVang.setOnAction(event -> onThoatTamVang());
        showBieuDoThongKeTamVangTheoThang();
        showBieuDoTamVangViLyDo();
        showTableThongKeTheoLyDo();

    }

    public void onThoatTamVang(){
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.TRANG_CHU);
    }

    public void showBieuDoThongKeTamVangTheoThang(){
        final XYChart.Series<String,Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Tháng 1",Model.getInstance().getTamVangOfThangVaNam(1,2023)));
        series.getData().add(new XYChart.Data<>("Tháng 2",Model.getInstance().getTamVangOfThangVaNam(2,2023)));
        series.getData().add(new XYChart.Data<>("Tháng 3",Model.getInstance().getTamVangOfThangVaNam(3,2023)));
        series.getData().add(new XYChart.Data<>("Tháng 4",Model.getInstance().getTamVangOfThangVaNam(4,2023)));
        series.getData().add(new XYChart.Data<>("Tháng 5",Model.getInstance().getTamVangOfThangVaNam(5,2023)));
        series.getData().add(new XYChart.Data<>("Tháng 6",Model.getInstance().getTamVangOfThangVaNam(6,2023)));
        series.getData().add(new XYChart.Data<>("Tháng 7",Model.getInstance().getTamVangOfThangVaNam(7,2023)));
        series.getData().add(new XYChart.Data<>("Tháng 8",Model.getInstance().getTamVangOfThangVaNam(8,2023)));
        series.getData().add(new XYChart.Data<>("Tháng 9",Model.getInstance().getTamVangOfThangVaNam(9,2023)));
        series.getData().add(new XYChart.Data<>("Tháng 10",Model.getInstance().getTamVangOfThangVaNam(10,2023)));
        series.getData().add(new XYChart.Data<>("Tháng 11",Model.getInstance().getTamVangOfThangVaNam(11,2023)));
        series.getData().add(new XYChart.Data<>("Tháng 12",Model.getInstance().getTamVangOfThangVaNam(12,2023)));
        bieuDoThongKeTamVangTheoThang.getData().add(series);

    }

    public void showBieuDoTamVangViLyDo(){
        ObservableList<PieChart.Data> piechartdata = FXCollections.observableArrayList(
                new PieChart.Data("Học tập",Model.getInstance().getTamVangViLyDoHocTap()),
                new PieChart.Data("Làm việc",Model.getInstance().getTamVangViLyDoLamViec()),
                new PieChart.Data("Sức khoẻ",Model.getInstance().getTamVangViLyDoSucKhoe()),
                new PieChart.Data("Khác",Model.getInstance().getTamVangViLyDoKhac())
        );
        thongKeTamVangTheoLyDo.setData(piechartdata);
    }

    public void showTableThongKeTheoLyDo(){

        ObservableList<ThongKe> list_tklydo =  FXCollections.observableArrayList(
                new ThongKe("Học tập",Model.getInstance().getTamVangViLyDoHocTap()),
                new ThongKe("Làm việc", Model.getInstance().getTamVangViLyDoLamViec()),
                new ThongKe("Sức khoẻ", Model.getInstance().getTamVangViLyDoSucKhoe()),
                new ThongKe("Khác", Model.getInstance().getTamVangViLyDoKhac())
        );

        lyDoTamVang.setCellValueFactory(new PropertyValueFactory<ThongKe,String>("gioitinh"));
        soLuongTamVang.setCellValueFactory(new PropertyValueFactory<ThongKe,Integer>("soluonggioitinh"));
        tableThongKeLyDoTamVang.setItems(list_tklydo);
    }






}
