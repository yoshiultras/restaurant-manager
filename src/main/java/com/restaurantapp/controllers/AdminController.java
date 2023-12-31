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
    private Parent root;
    @FXML
    private Button profileButton, accessButton, stockButton, orderButton, statsButton;
    private final User user = User.getUser();
    private boolean passwordChange;
    private final ControllerService controllerService = ControllerService.getInstance();

    public void showProfile(ActionEvent event) throws IOException {
        ProfileController profileController = (ProfileController) controllerService.changeScene(root, event, "profile.fxml");
        profileController.showInfo();
        profileController.changeSuccessLabel.setVisible(false);
    }

    public void showAccess(ActionEvent event) throws IOException {
        controllerService.changeScene(root, event, "access.fxml");
    }
    public void showStock(ActionEvent event) throws IOException {
        controllerService.changeScene(root, event, "stock.fxml");
    }
    public void showOrders(ActionEvent event) throws IOException {
        controllerService.changeScene(root, event, "orders.fxml");
    }
    public void showStats(ActionEvent event) throws IOException {
        controllerService.changeScene(root, event, "stats.fxml");
    }
    public void logout(ActionEvent event) throws IOException {
        controllerService.changeScene(root, event, "login.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String role = user.getRole();
        switch (role) {
            case "Складской менеджер":
                statsButton.setDisable(true);
                accessButton.setDisable(true);
                orderButton.setDisable(true);
                break;
            case "Менеджер ресторана":
                stockButton.setDisable(true);
                accessButton.setDisable(true);
                break;
            case "Директор":
                accessButton.setDisable(true);
                break;
            case "Админ":
                break;
        }
    }
}
