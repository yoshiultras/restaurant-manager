package com.restaurantapp.controllers;

import com.restaurantapp.models.User;
import com.restaurantapp.services.ControllerService;
import com.restaurantapp.services.DataService;
import com.restaurantapp.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccessController implements Initializable, Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private final User user = User.getUser();
    @FXML
    private Label errorLabel, roleLabel;
    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> loginColumn, nameColumn;
    @FXML
    private TableColumn<User, String> roleColumn;
    private final UserService userService = UserService.getInstance();
    private final ControllerService controllerService = ControllerService.getInstance();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roleLabel.setText(user.getRole());
        errorLabel.setText("");
        loginColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("fullName"));
        roleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        roleColumn.setOnEditCommit(event -> {
            User user = event.getRowValue();
            user.setRole(event.getNewValue());
            String role = user.getRole();
            try {
                errorLabel.setTextFill(new Color(1, 0, 0, 1));
                if(acceptRole(role)) {
                    userService.updateRole(user, role);
                    errorLabel.setTextFill(new Color(0, 0.5, 0, 1));
                    errorLabel.setText("Пользователю " + user.getUsername() + " был выдан уровень доступа " + role);
                }
                else {
                    errorLabel.setText("Выдоваемый уровень доступа не должен превышать собственный");
                }
            } catch (Exception e) {
                errorLabel.setText("Доступны только данные уровни доступа: 0, 1, 2, 3");
            }
        });
        table.setEditable(true);
        try {
            table.setItems(DataService.getUsersLowerRole(user));
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void back(ActionEvent event) throws IOException {
        controllerService.changeScene(stage, scene, root, event, "admin.fxml");
    }
    private boolean acceptRole(String role) {
        return Integer.parseInt(user.getRole()) >= Integer.parseInt(role);
    }
}