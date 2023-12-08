package com.example.citizenmanagement.views;

import com.example.citizenmanagement.models.hoKhauOptions;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class viewHoKhauFactory {
    private AnchorPane themHoKhau;
    private AnchorPane hoKhauShow;
    private AnchorPane tachHoKhau;
    private AnchorPane xemChiTietHoKhau;
    private final ObjectProperty<String> quaylai;
    private final ObjectProperty<hoKhauOptions> selectedHoKhauItem;
    public viewHoKhauFactory(){
        selectedHoKhauItem = new SimpleObjectProperty<>();
        this.quaylai = new SimpleObjectProperty<>("showBang");
    }
    public AnchorPane getThemHoKhau(){
        if(themHoKhau==null){
            try {
                themHoKhau = new FXMLLoader(getClass().getResource("/fxml/main_citizen/hoKhau/themMoiHoKhau.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return themHoKhau;
    }

    public AnchorPane getHoKhauShow(){
            try {
                hoKhauShow = new FXMLLoader(getClass().getResource("/fxml/main_citizen/hoKhau/hoKhauShow.fxml")).load();
            }catch (Exception e) {
                e.printStackTrace();
            }

        return hoKhauShow;
    }

    public AnchorPane getTachHoKhau(){
        if(tachHoKhau==null){
            try {
                tachHoKhau = new FXMLLoader(getClass().getResource("/fxml/main_citizen/hoKhau/tachHoKhau.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return tachHoKhau;
    }
    public AnchorPane getXemChiTietHoKhau(){
            try {
                xemChiTietHoKhau = new FXMLLoader(getClass().getResource("/fxml/main_citizen/hoKhau/xemChiTietHoKhau.fxml")).load();
            }catch (Exception e){
                System.out.println("loi o getXemChiTietHoKhau()");
                e.printStackTrace();
            }
        return xemChiTietHoKhau;
    }
    public ObjectProperty<String> getQuaylai(){
        return quaylai;
    }
    public ObjectProperty<hoKhauOptions> getSelectedHoKhauItem(){
        return selectedHoKhauItem;
    }
}
