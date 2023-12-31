package com.restaurantapp;

import com.restaurantapp.data.DatabaseConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("startup.fxml"));
        final int WIDTH = 600;
        final int HEIGHT = 400;
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setTitle("Restaurant manager");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        launch();
        databaseConnector.closeConnection();
    }
}