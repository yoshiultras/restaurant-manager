package com.restaurantapp.controllers;

import com.restaurantapp.models.User;
import com.restaurantapp.services.ControllerService;
import com.restaurantapp.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class ProfileController implements Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Pane changePane;
    @FXML
    private Label nameLabel, roleLabel, errorLabel, changeLabel;
    @FXML
    public Label changeSuccessLabel;
    @FXML
    private TextField passwordText, changeText;
    private final UserService userService = UserService.getInstance();
    private final ControllerService controllerService = ControllerService.getInstance();
    private final User user = User.getUser();
    private boolean passwordChange;

    public void showInfo() {
        nameLabel.setText(user.getUsername());
        roleLabel.setText(user.getRole() + "");
    }
    public void back(ActionEvent event) throws IOException {
        controllerService.changeScene(stage, scene, root, event, "admin.fxml");
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
        errorLabel.setText("");
        changeSuccessLabel.setVisible(true);
        showInfo();
    }
}
