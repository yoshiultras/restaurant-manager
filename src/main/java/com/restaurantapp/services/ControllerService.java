package com.restaurantapp.services;

import com.restaurantapp.View;
import com.restaurantapp.controllers.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public final class ControllerService {
    private final static ControllerService INSTANCE = new ControllerService();
    public static ControllerService getInstance() {
        return INSTANCE;
    }
    public Controller changeScene(Parent root, ActionEvent event, String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        root = loader.load();
        Controller controller = loader.getController();
        View.setView(event, root);
        return controller;
    }
}
