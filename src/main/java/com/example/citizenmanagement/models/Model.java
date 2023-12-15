package com.example.citizenmanagement.models;

import com.example.citizenmanagement.views.ViewFactory;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseConnection databaseConnection;

    //citizen manager section
    private final CitizenManager citizenManager;
    private boolean citizenManagerLoginSuccessFlag;
    private ObjectProperty<Image> imageObjectProperty;
    // nhan khau

    //ho khau section
    private static MainHoKhauCell hoKhauDuocChon;

    //thu phi
    private final FeeKhoanThuModel feeKhoanThuModel;

    private Model() {
        this.viewFactory = new ViewFactory();
        this.databaseConnection = new DatabaseConnection();

        this.citizenManager = new CitizenManager();
        this.feeKhoanThuModel = new FeeKhoanThuModel();
        imageObjectProperty = new SimpleObjectProperty<>();

        citizenManagerLoginSuccessFlag = false;
    }

    public static synchronized Model getInstance() {
        return model;
    }

    /*****************************************************************************/
    //Citizen Manager Method
    public void setCitizenManagerLoginSuccessFlag(boolean flag) {
        this.citizenManagerLoginSuccessFlag = flag;
    }
    public boolean getCitizenManagerLoginSuccessFlag() {return citizenManagerLoginSuccessFlag;}
    public CitizenManager getCitizenManager() {return citizenManager;}
    public void verifyManagerAccount(String tenDangNhap, String matKhau) {
        ResultSet resultSet = databaseConnection.getCitizenManagerData(tenDangNhap, matKhau);

        try {
            if(resultSet.isBeforeFirst()) {
                resultSet.next();
                this.citizenManager.setHoTen(resultSet.getString(1));
                this.citizenManager.setTenDangNhap(resultSet.getString(2));
                this.citizenManager.setMatKhau(resultSet.getString(3));
                this.citizenManager.setSoDienThoai(resultSet.getString(4));
                this.citizenManager.setVaiTro(resultSet.getInt(5));
                this.citizenManagerLoginSuccessFlag = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkManagerUsernameExisted(String tenDangNhap) {
        ResultSet resultSet = databaseConnection.checkCitizenManagerUsernameExisted(tenDangNhap);

        try {
            if (resultSet.isBeforeFirst()){
                return true;
            }
            else return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean checkManagerAccountExisted(String hoTen, String tenDangNhap, String soDienThoai, int vaiTro) {
        ResultSet resultSet = databaseConnection.checkCitizenManagerAccountExisted(hoTen, tenDangNhap, soDienThoai, vaiTro);

        try {
            if (resultSet.isBeforeFirst()) return true;
            else return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateManagerAccountPassword(String hoTen, String tenDangNhap, String soDienThoai, int vaiTro, String matKhau) {
        databaseConnection.updateCitizenManagerAccountPassword(hoTen, tenDangNhap, soDienThoai, vaiTro, matKhau);
    }
    public void registerManagerAccount(String hoTen, String tenDangNhap, String matKhau, String soDienThoai, int vaiTro) {
        databaseConnection.setCitizenManagerData(hoTen, tenDangNhap, matKhau, soDienThoai, vaiTro);
    }

    // su dung de tao moi 1 Model khi dang xuat va khoi tao lan dau tien.
    public static void createNewInstance() {
        model = new Model();
    }
    public ViewFactory getViewFactory() {return viewFactory;}
    public DatabaseConnection getDatabaseConnection() {return databaseConnection;}

    public ObjectProperty<Image> getImageObjectProperty() {return imageObjectProperty;}
    /*************************************************************************************************/

    public int getNumberOfNhanKhau() {
        ResultSet resultSet = databaseConnection.getNumberOfNhanhKhau();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()) {
                resultSet.next();
                res =  resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public int getNumberOfHoKhau(){
        ResultSet resultSet = databaseConnection.getNumberOfHoKhau();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public int getNumberOfTamTru(){
        ResultSet resultSet = databaseConnection.getNumberOfTamTru();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public int getNumberOfTamVang(){
        ResultSet resultSet = databaseConnection.getNumberOfTamVang();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public int getNumberOfNhanKhauNam(){
        ResultSet resultSet = databaseConnection.getNumberOfNhanKhauNam();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public int getNumberOfNhanKhauNu(){
        ResultSet resultSet = databaseConnection.getNumberOfNhanKhauNu();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int getNumberOfNhanKhauDuoi3Tuoi(){
        ResultSet resultSet = databaseConnection.getNumberOfNhanKhauDuoi3Tuoi();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public int getNumberOfNhanKhauTu3Den10Tuoi(){
        ResultSet resultSet = databaseConnection.getNumberOfNhanKhauTu3Den10Tuoi();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public int getNumberOfNhanKhauTu10Den18Tuoi(){
        ResultSet resultSet = databaseConnection.getNumberOfNhanKhauTu10Den18Tuoi();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public int getNumberOfNhanKhauTu18Den60Tuoi(){
        ResultSet resultSet = databaseConnection.getNumberOfNhanKhauTu18Den60Tuoi();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int getNumberOfNhanKhauTren60Tuoi(){
        ResultSet resultSet = databaseConnection.getNumberOfNhanKhauTren60Tuoi();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int getNamHienTai(){
        ResultSet resultSet = databaseConnection.getNamHienTai();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public int getHoKhauOfNamHienTai(){
        ResultSet resultSet = databaseConnection.getHoKhauOfNamHienTai();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public int getHoKhauOfNam(int r){
        ResultSet resultSet = databaseConnection.getHoKhauOfNam(r);
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int getTamTruOfThangVaNam(int thang, int nam){
        ResultSet resultSet = databaseConnection.getTamTruOfThangVaNam(thang,nam);
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public int getTamTruViLyDoHocTap(){
        ResultSet resultSet = databaseConnection.getTamTruViLyDoHocTap();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public int getTamTruViLyDoLamViec(){
        ResultSet resultSet = databaseConnection.getTamTruViLyDoLamViec();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int getTamTruViLyDoSucKhoe(){
        ResultSet resultSet = databaseConnection.getTamTruViLyDoSucKhoe();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int getTamTruViLyDoKhac(){
        return  getNumberOfTamTru() - getTamTruViLyDoHocTap() - getTamTruViLyDoSucKhoe() - getTamTruViLyDoLamViec();

    }
    public int getTamVangOfThangVaNam(int thang, int nam){
        ResultSet resultSet = databaseConnection.getTamVangOfThangVaNam(thang, nam);
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int getTamVangViLyDoSucKhoe(){
        ResultSet resultSet = databaseConnection.getTamVangViLyDoSucKhoe();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int getTamVangViLyDoHocTap(){
        ResultSet resultSet = databaseConnection.getTamVangViLyDoHocTap();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int getTamVangViLyDoLamViec(){
        ResultSet resultSet = databaseConnection.getTamVangViLyDoLamViec();
        int res = 0;
        try {
            if(resultSet.isBeforeFirst()){
                resultSet.next();
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public int getTamVangViLyDoKhac(){
        return  getNumberOfTamVang() - getTamVangViLyDoHocTap() - getTamVangViLyDoLamViec() - getTamVangViLyDoSucKhoe();
    }

    // ho khau

    public static MainHoKhauCell getHoKhauDuocChon() {
        return hoKhauDuocChon;
    }

    public static void setHoKhauDuocChon(MainHoKhauCell hoKhauDuocChon) {
        Model.hoKhauDuocChon = hoKhauDuocChon;
    }




    /*************************************************************************************************/
    //Thu phí


    public FeeKhoanThuModel getFeeKhoanThuModel() {
        return feeKhoanThuModel;
    }
}
