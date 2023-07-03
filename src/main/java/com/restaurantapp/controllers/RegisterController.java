package com.restaurantapp.controllers;

import com.restaurantapp.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

public class RegisterController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private UserService userService = UserService.getInstance();
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        root = loader.load();
        setScene(root, event);
    }
    private void setScene(Parent root, ActionEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
