module com.restaurantapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires java.sql;
    requires mysql.connector.java;

    opens com.restaurantapp to javafx.fxml;
    exports com.restaurantapp;
}