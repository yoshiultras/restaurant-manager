package com.restaurantapp.controllers;

import com.restaurantapp.models.User;
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

public class LoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private UserService userService;
    private String username;
    private String password;
    @FXML
    private TextField usernameText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Label label;
    public void init(UserService userService) {
        this.userService = userService;
    }
    public void login(ActionEvent event) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        if(userService == null) userService = new UserService();
        username = usernameText.getText();
        password = passwordText.getText();
        User user = userService.userLogin(username, password);
        if(user == null) {
            passwordText.clear();
            label.setText("Неправильный пароль или логин");
            return;
        }
        int role = user.getRole();
        switch (role){
            case 0:
                label.setText("Вам пока не выдана роль в системе");
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
                root = loader.load();
                AdminController adminController = loader.getController();
                adminController.init(userService, user);
                setScene(root, event);
                break;
        }
    }
    public void toRegistration(ActionEvent event) throws IOException {
        if(userService == null) userService = new UserService();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registration.fxml"));
        root = loader.load();
        RegisterController registerController = loader.getController();
        registerController.init(userService);
        setScene(root, event);
    }
    private void setScene(Parent root, ActionEvent event) {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
