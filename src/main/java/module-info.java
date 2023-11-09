module com.example.citizenmanagement {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.citizenmanagement to javafx.fxml;
    opens com.example.citizenmanagement.controllers to javafx.fxml;
    exports com.example.citizenmanagement;
    exports com.example.citizenmanagement.controllers;
}