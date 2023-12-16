package com.example.citizenmanagement.views;

import com.example.citizenmanagement.models.FeeMenuOptions;
import com.example.citizenmanagement.models.MainMenuOptions;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ViewFactory {

    //Main Citizen Views
    private final ObjectProperty<MainMenuOptions>    selectedMenuItem;
    private AnchorPane trangChuView;
    private AnchorPane nhanKhauView;
    private AnchorPane hoKhauView;
    // Trang chu thong ke
    private AnchorPane thongKeNhanKhauView;
    private AnchorPane thongKeHoKhauView;

    private AnchorPane thongKeTamTruView;
    private AnchorPane thongKeTamVangView;

    private AnchorPane profile;

    //Ho Khau

    private AnchorPane themHoKhau;
    private AnchorPane tachHoKhau;
    private AnchorPane xemChiTietHoKhau;
    private final ObjectProperty<String> quaylai;

    // Nhan Khau View

    private AnchorPane tamTruView;
    private AnchorPane tamVangView;
    private AnchorPane themMoiView;
    private AnchorPane khaiTuView;
    private AnchorPane tamTru2View;

    private AnchorPane tamVang2View;

    // Fee Views
    private final ObjectProperty<FeeMenuOptions> feeSelectedMenuItem;
    private AnchorPane feeTrangChuView;
    private AnchorPane feeDanhSachView;
    private AnchorPane feeThemKhoanThuView;
    private AnchorPane feeThemHoKhauView;
    /********************************************************************************************/
    public ViewFactory(){
        this.selectedMenuItem = new SimpleObjectProperty<>();
        this.feeSelectedMenuItem = new SimpleObjectProperty<>();

        this.quaylai = new SimpleObjectProperty<>("showBang");
    }

    /********************************************************************************************/
    public ObjectProperty<MainMenuOptions> getSelectedMenuItem() {
        return selectedMenuItem;
    }

    public AnchorPane getTrangChuView() {
        if (trangChuView == null) {
            try {
                trangChuView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/TrangChu.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return trangChuView;
    }
    public AnchorPane getHoKhauView() {
        if (hoKhauView == null) {
            try {
                hoKhauView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/hoKhau/hoKhauShow.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return hoKhauView;
    }

    public AnchorPane getNhanKhauView() {
        if (nhanKhauView == null) {
            try {
                nhanKhauView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/NhanKhau.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return nhanKhauView;
    }
    /********************************************************************************************/
    // thong ke phan trang chu
    public AnchorPane getThongKeNhanKhauView() {
        if (thongKeHoKhauView == null) {
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
        if (thongKeTamTruView == null) {
            try {
                thongKeTamTruView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/ThongKeTamTruNam2023.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return thongKeTamTruView;
    }
    public AnchorPane getThongKeTamVangView(){
        if (thongKeTamVangView == null) {
            try {
                thongKeTamVangView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/ThongKeTamVangNam2023.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return thongKeTamVangView;
    }

    public AnchorPane getProfile() {
        if (profile == null) {
            try {
                profile = new FXMLLoader(getClass().getResource("/fxml/main_citizen/Profile.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return profile;
    }

    /********************************************************************************************/
    public ObjectProperty<FeeMenuOptions> getFeeSelectedMenuItem() {
        return feeSelectedMenuItem;
    }

    public AnchorPane getFeeTrangChuView() {
        if (feeTrangChuView == null) {
            try {
                feeTrangChuView = new FXMLLoader(getClass().getResource("/fxml/fee/FeeTrangChu.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return feeTrangChuView;
    }
    public AnchorPane getFeeDanhSachView() {
        if (feeDanhSachView == null) {
            try {
                feeDanhSachView = new FXMLLoader(getClass().getResource("/fxml/fee/FeeDanhSach.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return feeDanhSachView;
    }
    public AnchorPane getFeeThemKhoanThuView() {
        if (feeThemKhoanThuView == null) {
            try {
                feeThemKhoanThuView = new FXMLLoader(getClass().getResource("/fxml/fee/FeeThemKhoanThu.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return feeThemKhoanThuView;
    }

    public AnchorPane getFeeThemHoKhauView() {
        if (feeThemHoKhauView == null) {
            try {
                feeThemHoKhauView = new FXMLLoader(getClass().getResource("/fxml/fee/FeeThemHoKhau.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return feeThemHoKhauView;
    }

    /********************************************************************************************/
    public AnchorPane getThemHoKhau(){
        if (themHoKhau == null) {
            try {
                themHoKhau = new FXMLLoader(getClass().getResource("/fxml/main_citizen/hoKhau/themMoiHoKhau.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return themHoKhau;
    }

    public AnchorPane getTachHoKhau(){
        if (tachHoKhau == null) {
            try {
                tachHoKhau = new FXMLLoader(getClass().getResource("/fxml/main_citizen/hoKhau/tachHoKhau.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tachHoKhau;
    }
    public AnchorPane getXemChiTietHoKhau(){
        if (xemChiTietHoKhau == null) {
            try {
                xemChiTietHoKhau = new FXMLLoader(getClass().getResource("/fxml/main_citizen/hoKhau/xemChiTietHoKhau.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return xemChiTietHoKhau;
    }
    public ObjectProperty<String> getQuaylai(){
        return quaylai;
    }
    /********************************************************************************************/
    // nhan khau
    public AnchorPane gettamVangView() {
        if (tamVangView == null) {
            try {
                tamVangView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/HoKhau/DkTamVang.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tamVangView;
    }

    public AnchorPane getthemMoiView() {
        if (themMoiView == null) {
            try {
                themMoiView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/HoKhau/ThemMoi.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return themMoiView;
    }

    public AnchorPane getkhaiTuView() {
        if (khaiTuView == null) {
            try {
                khaiTuView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/HoKhau/KhaiTu.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return khaiTuView;
    }

    public AnchorPane gettamTruView() {
        if (tamTruView == null) {
            try {
                tamTruView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/HoKhau/DkTamTru.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tamTruView;
    }

    public AnchorPane gettamVang2View() {
        if (tamVang2View == null) {
            try {
                tamVang2View = new FXMLLoader(getClass().getResource("/fxml/main_citizen/HoKhau/DkTamVang2.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return tamVang2View;
    }

    public AnchorPane gettamTru2View() {
        if (tamTru2View == null) {
            try {
                tamTru2View = new FXMLLoader(getClass().getResource("/fxml/main_citizen/HoKhau/DkTamTru2.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tamTru2View;
    }
    /********************************************************************************************/
    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login/Login.fxml"));
        createStage(loader);
    }

    public void showMainWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main_citizen/Main.fxml"));
        createStage(loader);
    }

    public void showFeeWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fee/Fee.fxml"));
        createStage(loader);
    }

    private void createStage(FXMLLoader loader) {
        Stage stage = new Stage();
        Scene scene;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public void closeStage(Stage stage) {
        stage.close();
    }
}



