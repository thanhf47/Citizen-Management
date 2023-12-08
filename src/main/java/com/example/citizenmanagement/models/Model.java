package com.example.citizenmanagement.models;

import com.example.citizenmanagement.views.ViewFactory;
import com.example.citizenmanagement.views.viewHoKhauFactory;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final viewHoKhauFactory viewHK;
    private final dataBaseConnectionHoKhau dataBCHK;
    private static hoKhauCell hoKhauDuocChon;
    private Model() {
        this.viewFactory = new ViewFactory();
        this.viewHK = new viewHoKhauFactory();
        dataBCHK = new dataBaseConnectionHoKhau();
    }
    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {return viewFactory;}
    public viewHoKhauFactory getViewHK(){return viewHK;}

    public dataBaseConnectionHoKhau getDataBCHK() {
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
