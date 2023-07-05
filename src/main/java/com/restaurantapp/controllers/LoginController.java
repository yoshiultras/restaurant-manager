package com.restaurantapp.controllers;

import com.restaurantapp.models.User;
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

public class LoginController implements Controller {
    private Parent root;
    private final UserService userService = UserService.getInstance();
    private final ControllerService controllerService = ControllerService.getInstance();
    @FXML
    private TextField usernameText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Label label;
    public void login(ActionEvent event) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        String username = usernameText.getText();
        String password = passwordText.getText();
        User user = userService.userLogin(username, password);
        if(user == null) {
            passwordText.clear();
            label.setText("Неправильный пароль или логин");
            return;
        }
        User.login(user);
        String role = user.getRole();
        if (role.equals("Нет роли")) label.setText("Вам пока не выдана роль в системе");
        else controllerService.changeScene(root, event, "admin.fxml");
    }
    public void toRegistration(ActionEvent event) throws IOException {
        controllerService.changeScene(root, event, "registration.fxml");
    }

}
