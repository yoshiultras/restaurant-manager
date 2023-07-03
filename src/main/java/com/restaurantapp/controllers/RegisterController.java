package com.restaurantapp.controllers;

import com.restaurantapp.services.ControllerService;
import com.restaurantapp.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class RegisterController implements Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private UserService userService = UserService.getInstance();
    private ControllerService controllerService = ControllerService.getInstance();
    @FXML
    private TextField usernameText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private PasswordField passwordConfirmText;
    @FXML
    private Label label;

    public void register(ActionEvent event) throws NoSuchAlgorithmException, InvalidKeySpecException, SQLException, IOException {
        String username = usernameText.getText();
        String password = passwordText.getText();
        if (!password.equals(passwordConfirmText.getText())) {
            label.setText("Пароли должны совпадать");
            return;
        }
        if (!username.matches("[A-Za-z0-9]+") || !password.matches("[A-Za-z0-9]+")) {
            label.setText("Пароль и логин должны содержать только цифры и латинские буквы");
            return;
        }
        if (userService.addUser(username, password) != null) {
            toLogin(event);
        } else {
            label.setText("Данный логин занят");
        }
    }
    public void toLogin(ActionEvent event) throws IOException {
        controllerService.changeScene(stage, scene, root, event, "login.fxml");
    }
}
