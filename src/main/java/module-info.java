module com.restaurantapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires java.sql;
    requires mysql.connector.java;

    opens com.restaurantapp.controllers to javafx.fxml;
    opens com.restaurantapp.models to javafx.fxml;
    exports com.restaurantapp;
    exports com.restaurantapp.controllers;
    exports com.restaurantapp.models;
    exports com.restaurantapp.services;
    opens com.restaurantapp.services to javafx.fxml;
    opens com.restaurantapp to javafx.fxml;
}