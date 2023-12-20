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

    private AnchorPane listTamTruView;
    private AnchorPane trangChuView;
    private AnchorPane nhanKhauView;
    private AnchorPane hoKhauView;
    // Trang chu thong ke
    private AnchorPane thongKeNhanKhauView;
    private AnchorPane thongKeHoKhauView;

    private AnchorPane thongKeTamTruView;
    private AnchorPane thongKeTamVangView;

    private AnchorPane profile;

    //danh sach nguoi chet
    private AnchorPane listDeadView;
    private AnchorPane thongTinKhaiTuView;
    private AnchorPane trangChuTamVangView;


    //Ho Khau

    private AnchorPane themThanhVienHoKhauView;

    private AnchorPane themChuHoKhauView;
    private AnchorPane tachHoKhau;
    private AnchorPane xemChiTietHoKhau;
    private AnchorPane thay_doi_ho_khau;
    private final ObjectProperty<String> quaylai;

    // Nhan Khau View

    private AnchorPane tamTruView;
    private AnchorPane tamVangView;
    private AnchorPane themMoiView;
    private AnchorPane khaiTuView;
    private AnchorPane tamTru2View;

    private AnchorPane tamVang2View;
    private AnchorPane chiTietThongTinTamVangView;

    private AnchorPane xemChiTietNhanKhau;

    // Fee Views
    private final ObjectProperty<FeeMenuOptions> feeSelectedMenuItem;
    private AnchorPane feeTrangChuView;
    private AnchorPane feeDanhSachView;
    private AnchorPane feeThemKhoanThuView;
    private AnchorPane feeThemHoKhauView;
    private AnchorPane feeXemChiTietKhoanThuView;
    private AnchorPane feeDSHoanThanhPhiView;
    private AnchorPane feeDSChuaHoanThanhPhiView;
    private AnchorPane feeProfileView;
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
        try {
            trangChuView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/TrangChu.fxml")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return trangChuView;
    }
    public AnchorPane getHoKhauView() {
        try {
            hoKhauView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/hoKhau/hoKhauShow.fxml")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return hoKhauView;
    }

    public AnchorPane getNhanKhauView() {
            try {
                nhanKhauView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/NhanKhau.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        return nhanKhauView;
    }


    public AnchorPane getXemChiTietNhanKhau() {
            try {
                xemChiTietNhanKhau = new FXMLLoader(getClass().getResource("/fxml/main_citizen/HoKhau/NhanKhauShow.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        return xemChiTietNhanKhau;
    }

    public AnchorPane getXemChiTietTamTru() {
        try {
            xemChiTietNhanKhau = new FXMLLoader(getClass().getResource("/fxml/main_citizen/HoKhau/TamTruShow.fxml")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return xemChiTietNhanKhau;
    }
    /********************************************************************************************/
    // thong ke phan trang chu
    public AnchorPane getThongKeNhanKhauView() {
        try {
            thongKeNhanKhauView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/ThongKeNhanKhau.fxml")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return thongKeNhanKhauView;
    }
    public AnchorPane getThongKeHoKhauView() {
        try {
            thongKeHoKhauView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/ThongKeHoKhau.fxml")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return thongKeHoKhauView;
    }
    public AnchorPane getThongKeTamTruView(){
        try {
            thongKeTamTruView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/ThongKeTamTruNam2023.fxml")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return thongKeTamTruView;
    }
    public AnchorPane getThongKeTamVangView(){
        try {
            thongKeTamVangView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/ThongKeTamVangNam2023.fxml")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
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

    public AnchorPane getTrangChuTamVangView(){
        try {
            trangChuTamVangView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/TrangChuTamVang.fxml")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return trangChuTamVangView;
    }

    public AnchorPane getChiTietThongTinTamVangView(){
        try {
            chiTietThongTinTamVangView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/HoKhau/ChiTietThongTinTamVang.fxml")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return chiTietThongTinTamVangView;
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
        if(feeDanhSachView == null) {
            try {
                feeDanhSachView = new FXMLLoader(getClass().getResource("/fxml/fee/FeeDanhSach.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return feeDanhSachView;
    }
    public AnchorPane getFeeThemKhoanThuView() {
        if(feeThemKhoanThuView == null) {
            try {
                feeThemKhoanThuView = new FXMLLoader(getClass().getResource("/fxml/fee/FeeThemKhoanThu.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return feeThemKhoanThuView;
    }

    public AnchorPane getFeeThemHoKhauView() {
        try {
            feeThemHoKhauView = new FXMLLoader(getClass().getResource("/fxml/fee/FeeThemHoKhau.fxml")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return feeThemHoKhauView;
    }

    public AnchorPane getFeeXemChiTietKhoanThuView() {
        try {
            feeXemChiTietKhoanThuView = new FXMLLoader(getClass().getResource("/fxml/fee/FeeChiTietKhoanThu.fxml")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return feeXemChiTietKhoanThuView;
    }
    public AnchorPane getDSHoanThanhPhiView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fee/FeeDSHoanThanhPhi.fxml"));
        try {
            feeDSHoanThanhPhiView = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return feeDSHoanThanhPhiView;
    }
    public AnchorPane getDSChuaHoanThanhPhiView() {
        try {
            feeDSChuaHoanThanhPhiView = new FXMLLoader(getClass().getResource("/fxml/fee/FeeDSChuaHoanThanhPhi.fxml")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return feeDSChuaHoanThanhPhiView;
    }
    /********************************************************************************************/
    // hộ khẩu
    public AnchorPane getThemThanhVienHoKhau(){
            try {
                themThanhVienHoKhauView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/hoKhau/ThemThanhVienHoKhau.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        return themThanhVienHoKhauView;
    }

    public AnchorPane getTachHoKhau(){
            try {
                tachHoKhau = new FXMLLoader(getClass().getResource("/fxml/main_citizen/hoKhau/tachHoKhau.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return tachHoKhau;
    }
    public AnchorPane getXemChiTietHoKhau(){
            try {
                xemChiTietHoKhau = new FXMLLoader(getClass().getResource("/fxml/main_citizen/hoKhau/xemChiTietHoKhau.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        return xemChiTietHoKhau;
    }
    public AnchorPane get_thay_doi_ho_khau(){
        try {
            thay_doi_ho_khau = new FXMLLoader(getClass().getResource("/fxml/main_citizen/hoKhau/thay_doi_ho_khau.fxml")).load();
        }catch (Exception e){
            e.printStackTrace();
        }
        return thay_doi_ho_khau;
    }
    public ObjectProperty<String> getQuaylai(){
        return quaylai;
    }
    /********************************************************************************************/
    // nhan khau
    public AnchorPane gettamVangView() {
            try {
                tamVangView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/HoKhau/DkTamVang.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        return tamVangView;
    }

    public AnchorPane getthemMoiView() {
            try {
                themMoiView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/HoKhau/ThemMoi.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        return themMoiView;
    }

    public AnchorPane getkhaiTuView() {
            try {
                khaiTuView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/HoKhau/KhaiTu.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        return khaiTuView;
    }

    public AnchorPane gettamTruView() {

            try {
                tamTruView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/HoKhau/DkTamTru.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        return tamTruView;
    }
    public AnchorPane getTamTruListView() {
            try {
                listTamTruView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/HoKhau/ListTamTru.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
               return listTamTruView;
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
        try {
            tamTru2View = new FXMLLoader(getClass().getResource("/fxml/main_citizen/HoKhau/DkTamTru2.fxml")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
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

    public AnchorPane getListDeadView() {
        try {
            listDeadView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/ListDead.fxml")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listDeadView;
    }


    public AnchorPane getThongTinKhaiTu() {
        try {
            thongTinKhaiTuView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/ThongTinKhaiTu.fxml")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return thongTinKhaiTuView;
    }
    /********************************************************************************************/
    // thong ke thu phi


    public AnchorPane getFeeProfileView(){
        if(feeProfileView == null){
            try {
                feeProfileView = new FXMLLoader(getClass().getResource("/fxml/fee/FeeProfile.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return feeProfileView;
    }

    public AnchorPane getThemChuHoKhau() {
        try {
            themChuHoKhauView = new FXMLLoader(getClass().getResource("/fxml/main_citizen/hoKhau/ThemChuHoKhau.fxml")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return themChuHoKhauView;
    }
}



