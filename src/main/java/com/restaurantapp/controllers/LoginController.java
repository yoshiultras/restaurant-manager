package com.restaurantapp.controllers;

import com.restaurantapp.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class LoginController {
    private UserService userService = new UserService();
    private String username;
    private String password;
    @FXML
    private TextField usernameText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Button loginButton;

    public void login(ActionEvent e) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        username = usernameText.getText();
        password = passwordText.getText();
        System.out.println(userService.userLogin(username, password));

    }
}
