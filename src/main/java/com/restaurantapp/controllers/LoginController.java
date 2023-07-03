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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class LoginController implements Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private UserService userService = UserService.getInstance();
    private ControllerService controllerService = ControllerService.getInstance();
    private String username;
    private String password;
    @FXML
    private TextField usernameText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Label label;
    public void login(ActionEvent event) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        username = usernameText.getText();
        password = passwordText.getText();
        User user = userService.userLogin(username, password);
        if(user == null) {
            passwordText.clear();
            label.setText("Неправильный пароль или логин");
            return;
        }
        User.login(user);
        String role = user.getRole();
        if (role.equals("0")) label.setText("Вам пока не выдана роль в системе");
        else controllerService.changeScene(stage, scene, root, event, "admin.fxml");
    }
    public void toRegistration(ActionEvent event) throws IOException {
        controllerService.changeScene(stage, scene, root, event, "registration.fxml");
    }

}
