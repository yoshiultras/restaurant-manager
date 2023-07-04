package com.restaurantapp.controllers;

import com.restaurantapp.models.User;
import com.restaurantapp.services.ControllerService;
import com.restaurantapp.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class AdminController implements Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button profileButton;
    @FXML
    private Button accessButton;
    private User user = User.getUser();
    private boolean passwordChange;
    private ControllerService controllerService = ControllerService.getInstance();

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
}
