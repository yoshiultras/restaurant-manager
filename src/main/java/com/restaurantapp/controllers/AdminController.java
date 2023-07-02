package com.restaurantapp.controllers;

import com.restaurantapp.models.User;
import com.restaurantapp.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class AdminController {
    UserService userService;
    @FXML
    private Pane profilePane;
    @FXML
    private Pane changePane;
    @FXML
    private Label nameLabel;
    @FXML
    private Label roleLabel;
    @FXML
    private Label errorLabel;
    @FXML
    private Label changeLabel;
    @FXML
    private Label changeSuccessLabel;
    @FXML
    private TextField passwordText;
    @FXML
    private TextField changeText;
    private User user;
    private boolean passwordChange;

    public void init(UserService userService, User user) {
        this.userService = userService;
        this.user = user;
    }
    public void showProfile(ActionEvent e) {
        changeSuccessLabel.setVisible(false);
        profilePane.setVisible(true);
        nameLabel.setText(user.getUsername());
        roleLabel.setText(user.getRole() + "");
    }
    public void changeName() {
        errorLabel.setText("");
        changePane.setVisible(true);
        passwordChange = false;
        changeLabel.setText("Новый логин");
    }
    public void changePassword() {
        errorLabel.setText("");
        changePane.setVisible(true);
        passwordChange = true;
        changeLabel.setText("Новый пароль");
    }
    public void submitChange() throws NoSuchAlgorithmException, InvalidKeySpecException, SQLException {
        String password = passwordText.getText();
        String change = changeText.getText();
        passwordText.setText("");
        changeText.setText("");
        if (!change.matches("[A-Za-z0-9]+")) {
            errorLabel.setText("Только латинские букв или цифры");
            return;
        }
        if (!userService.passwordHashing(password).equals(user.getPassword())) {
            errorLabel.setText("Неправильный пароль");
            return;
        }
        if (passwordChange) {
            userService.changePassword(user, change);
            user.setPassword(userService.passwordHashing(change));
        } else {
            if (userService.exists(change)) {
                errorLabel.setText("Данный логин занят");
                return;
            }
            userService.changeName(user, change);
            user.setUsername(change);
        }
        changePane.setVisible(false);
        errorLabel.setText("");
        showProfile(new ActionEvent());
        changeSuccessLabel.setVisible(true);
    }
}
