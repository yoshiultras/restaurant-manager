package com.restaurantapp.controllers;

import com.restaurantapp.models.User;
import com.restaurantapp.services.ControllerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable, Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button profileButton, accessButton, stockButton, orderButton;
    private final User user = User.getUser();
    private boolean passwordChange;
    private final ControllerService controllerService = ControllerService.getInstance();

    public void showProfile(ActionEvent event) throws IOException {
        ProfileController profileController = (ProfileController) controllerService.changeScene(stage, scene, root, event, "profile.fxml");
        profileController.showInfo();
        profileController.changeSuccessLabel.setVisible(false);
    }

    public void showAccess(ActionEvent event) throws IOException {
        controllerService.changeScene(stage, scene, root, event, "access.fxml");
    }
    public void showStock(ActionEvent event) throws IOException {
        controllerService.changeScene(stage, scene, root, event, "stock.fxml");
    }
    public void showOrders(ActionEvent event) throws IOException {
        controllerService.changeScene(stage, scene, root, event, "orders.fxml");
    }
    public void logout(ActionEvent event) throws IOException {
        controllerService.changeScene(stage, scene, root, event, "login.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String role = user.getRole();
        switch (role) {
            case "1":
                accessButton.setDisable(true);
                orderButton.setDisable(true);
                break;
            case "2":
                stockButton.setDisable(true);
                accessButton.setDisable(true);
                break;
            default:
                stockButton.setDisable(false);
                accessButton.setDisable(false);
                orderButton.setDisable(false);
                break;
        }
    }
}
