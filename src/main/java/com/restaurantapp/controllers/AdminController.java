package com.restaurantapp.controllers;

import com.restaurantapp.models.User;
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

public class AdminController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button profileButton;
    @FXML
    private Button accessButton;
    private User user = User.getUser();
    private boolean passwordChange;

    public void showProfile(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("profile.fxml"));
        root = loader.load();
        ProfileController profileController = loader.getController();
        profileController.showInfo();
        profileController.changeSuccessLabel.setVisible(false);
        setScene(root, e);
    }

    public void showAccess(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("access.fxml"));
        root = loader.load();
        AccessController accessController = loader.getController();
        setScene(root, e);
    }

    private void setScene(Parent root, ActionEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
