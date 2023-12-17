package com.example.citizenmanagement.controllers.feecontrollers;

import com.example.citizenmanagement.models.*;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class FeeChiTietKhoanThu implements Initializable {

    @FXML
    private Button back_btn;

    @FXML
    private Text bat_buoc;

    @FXML
    private AnchorPane chua_hoan_thanh_btn;

    @FXML
    private Label chua_hoan_thanh_lbl;

    @FXML
    private AnchorPane da_hoan_thanh_btn;

    @FXML
    private Label da_hoan_thanh_lbl;

    @FXML
    private Button delete_btn;

    @FXML
    private Text ma_khoan_thu;

    @FXML
    private Text ngay_tao;

    @FXML
    private Text so_tien_can_dong;

    @FXML
    private Text ten_khoan_thu;

    @FXML
    private TextArea mo_ta;

    private int daDong;
    private int chuaDong;
    private int tongSo;

    private Alert alert;
    @FXML
    private void onBackBtn() {
        Model.getInstance().getViewFactory().getFeeSelectedMenuItem().set(FeeMenuOptions.DANH_SACH_KHOAN_THU);
    }

    @FXML
    private void onDeleteBtn() {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation dialog");
        alert.setHeaderText(null);
        alert.setContentText("Ông chắc chưa?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {

            Model.getInstance().getDatabaseConnection().deleteKhoanThuPhi(
                    Model.getInstance().getFeeKhoanThuModel().getMaKhoanThu().get());

            for (FeeKhoanThuCell item : Model.getInstance().getDanhSachKhoanThu()) {
                if (item.getMaKhoanThu() == Model.getInstance().getFeeKhoanThuModel().getMaKhoanThu().get()) {
                    Model.getInstance().getDanhSachKhoanThu().remove(item);
                    break;
                }
            }

            Model.getInstance().getViewFactory().getFeeSelectedMenuItem().set(FeeMenuOptions.DANH_SACH_KHOAN_THU);

        }

    }
    @FXML
    private void onDaHoanThanhBtn() {
        Model.getInstance().getViewFactory().getFeeSelectedMenuItem().set(FeeMenuOptions.DA_HOAN_THANH_DONG_PHI);
    }
    @FXML
    private void onChuaHoanThanhBtn() {
        Model.getInstance().getViewFactory().getFeeSelectedMenuItem().set(FeeMenuOptions.CHUA_HOAN_THANH_DONG_PHI);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mo_ta.setWrapText(true);

        chiTiet();

        Model.getInstance().getFeeKhoanThuModel().getMaKhoanThu().addListener((observable, oldValue, newValue) -> {
            chiTiet();
        });

        Model.getInstance().getDanhSachChuaDongPhi().addListener((ListChangeListener.Change<? extends FeeHoKhauCell> change) -> {
            while(change.next()) {
                if (change.wasRemoved()) {
//                    System.out.println("nop phi thanh cong");
                    chiTiet();
                }
            }
        });
    }

    private void chiTiet() {
        FeeKhoanThuModel selectedItem = Model.getInstance().getFeeKhoanThuModel();

        ma_khoan_thu.setText(String.valueOf(selectedItem.getMaKhoanThu().get()));
        ten_khoan_thu.setText(selectedItem.getTenKhoanThu().get());
        if(selectedItem.getBatBuoc().get() == 0) {
            bat_buoc.setText("Không");
            so_tien_can_dong.setText("0 đ");
        }
        else {
            bat_buoc.setText("Có");
            so_tien_can_dong.setText("" + selectedItem.getSoTienTrenMotNguoi().get() + " đ");
        }

        if(selectedItem.getNgayTao().get() != null)
            ngay_tao.setText(selectedItem.getNgayTao().get());
        else
            ngay_tao.setText("");


        if (selectedItem.getMoTa().get() != null)
            mo_ta.setText(selectedItem.getMoTa().get());
        else
            mo_ta.setText("");

        daDong = Model.getInstance().getDatabaseConnection().getSoLuongHoDaDongPhi(selectedItem.getMaKhoanThu().get());
        chuaDong = Model.getInstance().getDatabaseConnection().getSoLuongHoChuaDongPhi(selectedItem.getMaKhoanThu().get());
        tongSo = Model.getInstance().getDatabaseConnection().getSoLuongHoDongPhi(selectedItem.getMaKhoanThu().get());

        da_hoan_thanh_lbl.setText("" + daDong + "/" + tongSo);
        chua_hoan_thanh_lbl.setText("" + chuaDong + "/" + tongSo);
    }
}
