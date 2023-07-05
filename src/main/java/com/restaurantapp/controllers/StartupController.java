package com.restaurantapp.controllers;

import com.restaurantapp.data.DatabaseConnector;
import com.restaurantapp.services.ControllerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartupController implements Initializable, Controller {
    private Parent root;
    private final ControllerService controllerService = ControllerService.getInstance();
    @FXML
    private Label messageLabel;
    @FXML
    private Button button;

    public void startup(ActionEvent event) throws IOException {
        controllerService.changeScene(root, event, "login.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(DatabaseConnector.getConnection() == null) {
            messageLabel.setText("Ошибка подключения");
            button.setDisable(true);
        } else {
            messageLabel.setText("Подключение успешно");
        }
    }
}
