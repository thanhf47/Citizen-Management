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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ThongKeTamVangNam2023Controller implements Initializable {

    public StackedBarChart bieuDoThongKeTamVangTheoThang;
    public PieChart thongKeTamVangTheoLyDo;
    public TableView<ThongKe> tableThongKeLyDoTamVang;
    public TableColumn<ThongKe,String> lyDoTamVang;
    public TableColumn<ThongKe,Integer> soLuongTamVang;
    public Button thoatTamVang;
    public TextField timkiemnamtamvang_tfld;
    public Button timkiemnamtamvang_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //tongSoNhanKhau_text.setText(Integer.toString(Model.getInstance().getNumberOfTamVang()));
        thoatTamVang.setOnAction(event -> onThoatTamVang());
        timkiemnamtamvang_btn.setOnAction(event -> {
            showBieuDoTamVangViLyDo();

            showTableThongKeTheoLyDo();
            showBieuDoThongKeTamVangTheoThang();
        });


    }

    public void onThoatTamVang(){
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.TRANG_CHU);
    }

    public void showBieuDoThongKeTamVangTheoThang(){
        final XYChart.Series<String,Number> series = new XYChart.Series<>();
        bieuDoThongKeTamVangTheoThang.getData().clear();
        series.getData().add(new XYChart.Data<>("Tháng 1",Model.getInstance().getTamVangOfThangVaNam(1,Integer.parseInt(timkiemnamtamvang_tfld.getText()))));
        series.getData().add(new XYChart.Data<>("Tháng 2",Model.getInstance().getTamVangOfThangVaNam(2,Integer.parseInt(timkiemnamtamvang_tfld.getText()))));
        series.getData().add(new XYChart.Data<>("Tháng 3",Model.getInstance().getTamVangOfThangVaNam(3,Integer.parseInt(timkiemnamtamvang_tfld.getText()))));
        series.getData().add(new XYChart.Data<>("Tháng 4",Model.getInstance().getTamVangOfThangVaNam(4,Integer.parseInt(timkiemnamtamvang_tfld.getText()))));
        series.getData().add(new XYChart.Data<>("Tháng 5",Model.getInstance().getTamVangOfThangVaNam(5,Integer.parseInt(timkiemnamtamvang_tfld.getText()))));
        series.getData().add(new XYChart.Data<>("Tháng 6",Model.getInstance().getTamVangOfThangVaNam(6,Integer.parseInt(timkiemnamtamvang_tfld.getText()))));
        series.getData().add(new XYChart.Data<>("Tháng 7",Model.getInstance().getTamVangOfThangVaNam(7,Integer.parseInt(timkiemnamtamvang_tfld.getText()))));
        series.getData().add(new XYChart.Data<>("Tháng 8",Model.getInstance().getTamVangOfThangVaNam(8,Integer.parseInt(timkiemnamtamvang_tfld.getText()))));
        series.getData().add(new XYChart.Data<>("Tháng 9",Model.getInstance().getTamVangOfThangVaNam(9,Integer.parseInt(timkiemnamtamvang_tfld.getText()))));
        series.getData().add(new XYChart.Data<>("Tháng 10",Model.getInstance().getTamVangOfThangVaNam(10,Integer.parseInt(timkiemnamtamvang_tfld.getText()))));
        series.getData().add(new XYChart.Data<>("Tháng 11",Model.getInstance().getTamVangOfThangVaNam(11,Integer.parseInt(timkiemnamtamvang_tfld.getText()))));
        series.getData().add(new XYChart.Data<>("Tháng 12",Model.getInstance().getTamVangOfThangVaNam(12,Integer.parseInt(timkiemnamtamvang_tfld.getText()))));
        bieuDoThongKeTamVangTheoThang.getData().add(series);

    }

    public void showBieuDoTamVangViLyDo(){
        ObservableList<PieChart.Data> piechartdata = FXCollections.observableArrayList(
                new PieChart.Data("Học tập",Model.getInstance().getTamVangViLyDoHocTap(Integer.parseInt(timkiemnamtamvang_tfld.getText()))),
                new PieChart.Data("Làm việc",Model.getInstance().getTamVangViLyDoLamViec(Integer.parseInt(timkiemnamtamvang_tfld.getText()))),
                new PieChart.Data("Sức khoẻ",Model.getInstance().getTamVangViLyDoSucKhoe(Integer.parseInt(timkiemnamtamvang_tfld.getText()))),
                new PieChart.Data("Khác",Model.getInstance().getTamVangViLyDoKhac(Integer.parseInt(timkiemnamtamvang_tfld.getText())))
        );
        thongKeTamVangTheoLyDo.setData(piechartdata);
    }

    public void showTableThongKeTheoLyDo(){

        ObservableList<ThongKe> list_tklydo =  FXCollections.observableArrayList(
                new ThongKe("Học tập",Model.getInstance().getTamVangViLyDoHocTap(Integer.parseInt(timkiemnamtamvang_tfld.getText()))),
                new ThongKe("Làm việc", Model.getInstance().getTamVangViLyDoLamViec(Integer.parseInt(timkiemnamtamvang_tfld.getText()))),
                new ThongKe("Sức khoẻ", Model.getInstance().getTamVangViLyDoSucKhoe(Integer.parseInt(timkiemnamtamvang_tfld.getText()))),
                new ThongKe("Khác", Model.getInstance().getTamVangViLyDoKhac(Integer.parseInt(timkiemnamtamvang_tfld.getText())))
        );

        lyDoTamVang.setCellValueFactory(new PropertyValueFactory<ThongKe,String>("gioitinh"));
        soLuongTamVang.setCellValueFactory(new PropertyValueFactory<ThongKe,Integer>("soluonggioitinh"));
        tableThongKeLyDoTamVang.setItems(list_tklydo);
    }






}
