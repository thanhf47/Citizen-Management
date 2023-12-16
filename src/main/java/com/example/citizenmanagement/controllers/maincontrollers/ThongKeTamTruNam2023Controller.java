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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ThongKeTamTruNam2023Controller implements Initializable {

    public TextField timkiemnam_textfield;
    public Button timkiemnam_btn;
    public Text nam_text;
    public Text tongso_text;
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

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        thoatTamTru.setOnAction(event -> onThoatTamTru());
        showBieuDoTamTruTheoThangMacDinh();
        showBieuDoTamTruTheoLyDoMacDinh();
        showTableTamTruTheoLyDoMacDinh();
        nam_text.setText(Integer.toString(Model.getInstance().getNamHienTai()));
        tongso_text.setText(Integer.toString(Model.getInstance().getNumberOfTamTru(Model.getInstance().getNamHienTai())));

        timkiemnam_btn.setOnAction(event ->{

            String input = timkiemnam_textfield.getText();
            if(input.isEmpty()){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText("Bạn chưa nhập vào năm");
                alert.setContentText("Xin mời bạn nhập năm");
                alert.showAndWait();
            }

            else if(!isInteger(input)){

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText("Bạn nhập sai năm");
                alert.setContentText("Xin mời nhập năm theo đúng định dạng:'YYYY' với Y từ 1 đến 9");
                alert.showAndWait();
            }

            else {

                nam_text.setText(input);
                tongso_text.setText(Integer.toString(Model.getInstance().getNumberOfTamTru(Integer.parseInt(input))));
                thongKeTamTruNam2023.getData().clear();
                showBieuDoTamTruTheoThang();
                showBieuDoTamTruTheoLyDo();
                showTableTamTruTheoLyDo();
            }

        });

    }

    public void onThoatTamTru(){
        Model.getInstance().getViewFactory().getSelectedMenuItem().set(MainMenuOptions.TRANG_CHU);
    }


    public void showBieuDoTamTruTheoThang(){
        //thongKeTamTruNam2023.getData().clear();
        XYChart.Series<String,Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Tháng 1",Model.getInstance().getTamTruOfThangVaNam(1,Integer.parseInt(timkiemnam_textfield.getText()))));
        series.getData().add(new XYChart.Data<>("Tháng 2",Model.getInstance().getTamTruOfThangVaNam(2,Integer.parseInt(timkiemnam_textfield.getText()))));
        series.getData().add(new XYChart.Data<>("Tháng 3",Model.getInstance().getTamTruOfThangVaNam(3,Integer.parseInt(timkiemnam_textfield.getText()))));
        series.getData().add(new XYChart.Data<>("Tháng 4",Model.getInstance().getTamTruOfThangVaNam(4,Integer.parseInt(timkiemnam_textfield.getText()))));
        series.getData().add(new XYChart.Data<>("Tháng 5",Model.getInstance().getTamTruOfThangVaNam(5,Integer.parseInt(timkiemnam_textfield.getText()))));
        series.getData().add(new XYChart.Data<>("Tháng 6",Model.getInstance().getTamTruOfThangVaNam(6,Integer.parseInt(timkiemnam_textfield.getText()))));
        series.getData().add(new XYChart.Data<>("Tháng 7",Model.getInstance().getTamTruOfThangVaNam(7,Integer.parseInt(timkiemnam_textfield.getText()))));
        series.getData().add(new XYChart.Data<>("Tháng 8",Model.getInstance().getTamTruOfThangVaNam(8,Integer.parseInt(timkiemnam_textfield.getText()))));
        series.getData().add(new XYChart.Data<>("Tháng 9",Model.getInstance().getTamTruOfThangVaNam(9,Integer.parseInt(timkiemnam_textfield.getText()))));
        series.getData().add(new XYChart.Data<>("Tháng 10",Model.getInstance().getTamTruOfThangVaNam(10,Integer.parseInt(timkiemnam_textfield.getText()))));
        series.getData().add(new XYChart.Data<>("Tháng 11",Model.getInstance().getTamTruOfThangVaNam(11,Integer.parseInt(timkiemnam_textfield.getText()))));
        series.getData().add(new XYChart.Data<>("Tháng 12",Model.getInstance().getTamTruOfThangVaNam(12,Integer.parseInt(timkiemnam_textfield.getText()))));
        thongKeTamTruNam2023.getData().add(series);
    }

    public void showBieuDoTamTruTheoLyDo(){
        ObservableList<PieChart.Data> piechartdata = FXCollections.observableArrayList(
                new PieChart.Data("Học tập",Model.getInstance().getTamTruViLyDoHocTap(Integer.parseInt(timkiemnam_textfield.getText()))),
                new PieChart.Data("Làm việc",Model.getInstance().getTamTruViLyDoLamViec(Integer.parseInt(timkiemnam_textfield.getText()))),
                new PieChart.Data("Chăm sóc sức khoẻ",Model.getInstance().getTamTruViLyDoSucKhoe(Integer.parseInt(timkiemnam_textfield.getText()))),
                new PieChart.Data("Khác",Model.getInstance().getTamTruViLyDoKhac(Integer.parseInt(timkiemnam_textfield.getText())))

        );
        bieudolidotamtru.setData(piechartdata);
    }

    public void showTableTamTruTheoLyDo(){

        ObservableList<ThongKe> list1 = FXCollections.observableArrayList(
                new ThongKe("Học tập",Model.getInstance().getTamTruViLyDoHocTap(Integer.parseInt(timkiemnam_textfield.getText()))),
                new ThongKe("Làm việc", Model.getInstance().getTamTruViLyDoLamViec(Integer.parseInt(timkiemnam_textfield.getText()))),
                new ThongKe("Sức khoẻ", Model.getInstance().getTamTruViLyDoSucKhoe(Integer.parseInt(timkiemnam_textfield.getText()))),
                new ThongKe("Khác", Model.getInstance().getTamTruViLyDoKhac(Integer.parseInt(timkiemnam_textfield.getText())))
        );

        Lydo.setCellValueFactory(new PropertyValueFactory<ThongKe,String>("gioitinh"));
        soluongtamtru_lydo.setCellValueFactory(new PropertyValueFactory<ThongKe,Integer>("soluonggioitinh"));
        tableLyDoTamTru.setItems(list1);
    }

    public void showBieuDoTamTruTheoThangMacDinh(){
        XYChart.Series<String,Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Tháng 1",Model.getInstance().getTamTruOfThangVaNam(1,Model.getInstance().getNamHienTai())));
        series.getData().add(new XYChart.Data<>("Tháng 2",Model.getInstance().getTamTruOfThangVaNam(2,Model.getInstance().getNamHienTai())));
        series.getData().add(new XYChart.Data<>("Tháng 3",Model.getInstance().getTamTruOfThangVaNam(3,Model.getInstance().getNamHienTai())));
        series.getData().add(new XYChart.Data<>("Tháng 4",Model.getInstance().getTamTruOfThangVaNam(4,Model.getInstance().getNamHienTai())));
        series.getData().add(new XYChart.Data<>("Tháng 5",Model.getInstance().getTamTruOfThangVaNam(5,Model.getInstance().getNamHienTai())));
        series.getData().add(new XYChart.Data<>("Tháng 6",Model.getInstance().getTamTruOfThangVaNam(6,Model.getInstance().getNamHienTai())));
        series.getData().add(new XYChart.Data<>("Tháng 7",Model.getInstance().getTamTruOfThangVaNam(7,Model.getInstance().getNamHienTai())));
        series.getData().add(new XYChart.Data<>("Tháng 8",Model.getInstance().getTamTruOfThangVaNam(8,Model.getInstance().getNamHienTai())));
        series.getData().add(new XYChart.Data<>("Tháng 9",Model.getInstance().getTamTruOfThangVaNam(9,Model.getInstance().getNamHienTai())));
        series.getData().add(new XYChart.Data<>("Tháng 10",Model.getInstance().getTamTruOfThangVaNam(10,Model.getInstance().getNamHienTai())));
        series.getData().add(new XYChart.Data<>("Tháng 11",Model.getInstance().getTamTruOfThangVaNam(11,Model.getInstance().getNamHienTai())));
        series.getData().add(new XYChart.Data<>("Tháng 12",Model.getInstance().getTamTruOfThangVaNam(12,Model.getInstance().getNamHienTai())));
        thongKeTamTruNam2023.getData().add(series);
    }

    public void showBieuDoTamTruTheoLyDoMacDinh(){
        ObservableList<PieChart.Data> piechartdata = FXCollections.observableArrayList(
                new PieChart.Data("Học tập",Model.getInstance().getTamTruViLyDoHocTap(Model.getInstance().getNamHienTai())),
                new PieChart.Data("Làm việc",Model.getInstance().getTamTruViLyDoLamViec(Model.getInstance().getNamHienTai())),
                new PieChart.Data("Chăm sóc sức khoẻ",Model.getInstance().getTamTruViLyDoSucKhoe(Model.getInstance().getNamHienTai())),
                new PieChart.Data("Khác",Model.getInstance().getTamTruViLyDoKhac(Model.getInstance().getNamHienTai()))

        );
        bieudolidotamtru.setData(piechartdata);
    }

    public void showTableTamTruTheoLyDoMacDinh(){

        ObservableList<ThongKe> list1 = FXCollections.observableArrayList(
                new ThongKe("Học tập",Model.getInstance().getTamTruViLyDoHocTap(Model.getInstance().getNamHienTai())),
                new ThongKe("Làm việc", Model.getInstance().getTamTruViLyDoLamViec(Model.getInstance().getNamHienTai())),
                new ThongKe("Sức khoẻ", Model.getInstance().getTamTruViLyDoSucKhoe(Model.getInstance().getNamHienTai())),
                new ThongKe("Khác", Model.getInstance().getTamTruViLyDoKhac(Model.getInstance().getNamHienTai()))
        );

        Lydo.setCellValueFactory(new PropertyValueFactory<ThongKe,String>("gioitinh"));
        soluongtamtru_lydo.setCellValueFactory(new PropertyValueFactory<ThongKe,Integer>("soluonggioitinh"));
        tableLyDoTamTru.setItems(list1);
    }
}
