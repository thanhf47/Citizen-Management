module com.example.citizenmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires java.desktop;


    opens com.example.citizenmanagement to javafx.fxml;
    opens com.example.citizenmanagement.controllers to javafx.fxml;
    opens com.example.citizenmanagement.controllers.maincontrollers to javafx.fxml;
    opens com.example.citizenmanagement.models to javafx.fxml;
    opens com.example.citizenmanagement.views to javafx.fxml;
    opens com.example.citizenmanagement.controllers.feecontrollers to javafx.fxml;
    opens com.example.citizenmanagement.controllers.maincontrollers.hoKhau to javafx.fxml;
    opens com.example.citizenmanagement.controllers.maincontrollers.NhankhauController to javafx.fxml;

    exports com.example.citizenmanagement;
    exports com.example.citizenmanagement.controllers;
    exports com.example.citizenmanagement.controllers.maincontrollers;
    exports com.example.citizenmanagement.controllers.feecontrollers;
    exports com.example.citizenmanagement.models;
    exports com.example.citizenmanagement.views;
    exports com.example.citizenmanagement.controllers.maincontrollers.hoKhau;
    exports com.example.citizenmanagement.controllers.maincontrollers.NhankhauController;

}