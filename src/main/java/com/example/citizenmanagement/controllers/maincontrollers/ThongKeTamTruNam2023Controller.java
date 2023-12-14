package com.example.citizenmanagement.controllers.maincontrollers;

import com.example.citizenmanagement.models.ThongKe;
import com.example.citizenmanagement.models.MainMenuOptions;
import com.example.citizenmanagement.models.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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

public class ThongKeTamTruNam2023Controller implements Initializable {

    @FXML
    private TableColumn<ThongKe, String> Lydo;

    @FXML
    private PieChart bieudolidotamtru;



    @FXML
    private TableColumn<ThongKe, Integer> soluongtamtru_lydo;

    @FXML
    private TableView<ThongKe> tableLyDoTamTru;

    @FXML
    private Button thoatTamTru;

    @FXML
    private StackedBarChart thongKeTamTruNam2023;

    @FXML
    private Text tongsonam2023_text;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        hienThiTamTru();
        thoatTamTru.setOnAction(event -> onThoatTamTru());
        showBieuDoTamTruTheoThang();
        showBieuDoTamTruTheoLyDo();
        showTableTamTruTheoLyDo();

    }

    public void onThoatTamTru(){
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.TRANG_CHU);
    }

    public void hienThiTamTru(){
        tongsonam2023_text.setText(Integer.toString(Model.getInstance().getNumberOfTamTru()));

    }

    public void showBieuDoTamTruTheoThang(){
        final XYChart.Series<String,Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Tháng 1",Model.getInstance().getTamTruOfThangVaNam(1,2023)));
        series.getData().add(new XYChart.Data<>("Tháng 2",Model.getInstance().getTamTruOfThangVaNam(2,2023)));
        series.getData().add(new XYChart.Data<>("Tháng 3",Model.getInstance().getTamTruOfThangVaNam(3,2023)));
        series.getData().add(new XYChart.Data<>("Tháng 4",Model.getInstance().getTamTruOfThangVaNam(4,2023)));
        series.getData().add(new XYChart.Data<>("Tháng 5",Model.getInstance().getTamTruOfThangVaNam(5,2023)));
        series.getData().add(new XYChart.Data<>("Tháng 6",Model.getInstance().getTamTruOfThangVaNam(6,2023)));
        series.getData().add(new XYChart.Data<>("Tháng 7",Model.getInstance().getTamTruOfThangVaNam(7,2023)));
        series.getData().add(new XYChart.Data<>("Tháng 8",Model.getInstance().getTamTruOfThangVaNam(8,2023)));
        series.getData().add(new XYChart.Data<>("Tháng 9",Model.getInstance().getTamTruOfThangVaNam(9,2023)));
        series.getData().add(new XYChart.Data<>("Tháng 10",Model.getInstance().getTamTruOfThangVaNam(10,2023)));
        series.getData().add(new XYChart.Data<>("Tháng 11",Model.getInstance().getTamTruOfThangVaNam(11,2023)));
        series.getData().add(new XYChart.Data<>("Tháng 12",Model.getInstance().getTamTruOfThangVaNam(12,2023)));
        thongKeTamTruNam2023.getData().add(series);
    }

    public void showBieuDoTamTruTheoLyDo(){
        ObservableList<PieChart.Data> piechartdata = FXCollections.observableArrayList(
                new PieChart.Data("Học tập",Model.getInstance().getTamTruViLyDoHocTap()),
                new PieChart.Data("Làm việc",Model.getInstance().getTamTruViLyDoLamViec()),
                new PieChart.Data("Chăm sóc sức khoẻ",Model.getInstance().getTamTruViLyDoSucKhoe()),
                new PieChart.Data("Khác",Model.getInstance().getTamTruViLyDoKhac())

        );
        bieudolidotamtru.setData(piechartdata);
    }

    public void showTableTamTruTheoLyDo(){

        ObservableList<ThongKe> list1 = FXCollections.observableArrayList(
                new ThongKe("Học tập",Model.getInstance().getTamTruViLyDoHocTap()),
                new ThongKe("Làm việc", Model.getInstance().getTamTruViLyDoLamViec()),
                new ThongKe("Sức khoẻ", Model.getInstance().getTamTruViLyDoSucKhoe()),
                new ThongKe("Khác", Model.getInstance().getTamTruViLyDoKhac())
        );

        Lydo.setCellValueFactory(new PropertyValueFactory<ThongKe,String>("gioitinh"));
        soluongtamtru_lydo.setCellValueFactory(new PropertyValueFactory<ThongKe,Integer>("soluonggioitinh"));
        tableLyDoTamTru.setItems(list1);
    }
}
