package com.example.citizenmanagement.models;

import com.example.citizenmanagement.views.NhankhauFactoryView;
import com.example.citizenmanagement.views.ViewFactory;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;

    private final NhankhauFactoryView nhankhauFactoryView;
    private Model() {
        this.viewFactory = new ViewFactory();
        this.nhankhauFactoryView = new NhankhauFactoryView();
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {return viewFactory;}

    public NhankhauFactoryView getNhankhauFactoryView() {
        return nhankhauFactoryView;
    }
}
