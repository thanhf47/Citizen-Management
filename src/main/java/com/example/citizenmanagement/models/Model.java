package com.example.citizenmanagement.models;

import com.example.citizenmanagement.views.ViewFactory;
import com.example.citizenmanagement.views.viewHoKhauFactory;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final viewHoKhauFactory viewHK;
    private final DatabaseConnection dataBCHK;
    private static hoKhauCell hoKhauDuocChon;
    private Model() {
        this.viewFactory = new ViewFactory();
        this.viewHK = new viewHoKhauFactory();
        dataBCHK = new DatabaseConnection();
    }
    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {return viewFactory;}
    public viewHoKhauFactory getViewHK(){return viewHK;}

    public DatabaseConnection getDataBCHK() {
        return dataBCHK;
    }


    // ho khau

    public static hoKhauCell getHoKhauDuocChon() {
        return hoKhauDuocChon;
    }

    public static void setHoKhauDuocChon(hoKhauCell hoKhauDuocChon) {
        Model.hoKhauDuocChon = hoKhauDuocChon;
    }
}
